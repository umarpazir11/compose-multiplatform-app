import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.Constants.BASE_URL
import core.Constants.LAYOUT_COLUMN
import core.Constants.MORE_DATA
import data.repository.BirdRepository
import database.Birds
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.koinInject
import ui.BirdsViewModel

//private val koin = initKoin().koin
@Composable
fun BirdAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(primary = Color.Black),
        shapes = MaterialTheme.shapes.copy(
            small = AbsoluteCutCornerShape(0.dp),
            medium = AbsoluteCutCornerShape(0.dp),
            large = AbsoluteCutCornerShape(0.dp)
        )
    ) {
        content()
    }
}


@Composable
fun App() {
    val birdRepository = koinInject<BirdRepository>()
    val birdsViewModel = koinInject<BirdsViewModel>()
    //val birdsViewModel = getViewModel(Unit, viewModelFactory { BirdsViewModel(birdRepository) })
    BirdAppTheme {
        BirdsPage(birdsViewModel, birdRepository)
    }
}

@Composable
fun BirdsPage(viewModel: BirdsViewModel, birdRepository: BirdRepository) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            viewModel.selectedCategory(birdRepository, "PIGEON")
            LazyVerticalGrid(
                columns = GridCells.Fixed(LAYOUT_COLUMN),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
                content = {
                    item(0, span = { GridItemSpan(LAYOUT_COLUMN) }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth().padding(top = 12.dp)
                        ) {
                            Header("PIGEON")
                        }
                    }
                    items(uiState.allImages.filter { it.category == "PIGEON" }.take(16+MORE_DATA)) {
                        BirdImageCell(it)
                    }
                    item(17+MORE_DATA, span = { GridItemSpan(LAYOUT_COLUMN) }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth().padding(top = 12.dp)
                        ) {
                            Header("EAGLE")
                        }
                    }
                    items(uiState.allImages.filter { it.category == "EAGLE" }.take(9+MORE_DATA)) {
                        BirdImageCell(it)
                    }

                    item(27+MORE_DATA, span = { GridItemSpan(LAYOUT_COLUMN) }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth().padding(top = 12.dp)
                        ) {
                            Header("OWL")
                        }
                    }
                    items(uiState.allImages.filter { it.category == "OWL" }.take(8+MORE_DATA)) {
                        BirdImageCell(it)
                    }
                }
            )
        }
    }
}

@Composable
fun Header(categoryName: String) {
    Text(
        categoryName,
        fontSize = 20.sp, fontWeight = FontWeight.Bold
    )
}

@Composable
fun BirdImageCell(image: Birds) {
    Column {
        KamelImage(
            asyncPainterResource(BASE_URL+image.path),
            contentDescription = "${image.category} by ${image.author}",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().aspectRatio(1.4f).shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(8.dp)
            )
        )
        Text(image.author, Modifier.padding(8.dp), fontSize = 16.sp)
    }
}

expect fun getPlatformName(): String