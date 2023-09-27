import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
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
import androidx.compose.ui.unit.dp
import data.local.DriverFactory
import data.repository.BirdRepository
import database.BirdsQueries
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import di.coreModule
import di.initKoin
import di.sharedModules
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import model.BirdImage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.KoinApplication
import org.koin.compose.KoinContext
import org.koin.compose.KoinIsolatedContext
import org.koin.compose.koinInject
import org.koin.core.component.inject
import org.koin.dsl.module

private val koin = initKoin().koin
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

/*@InternalCoroutinesApi
suspend fun main() {
    val repo = koin.get<BirdRepository>()
    repo.syncLocalDatabaseWithServer()
}*/
@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
/*    KoinContext {
        val myService  = koinInject<BirdRepository>()
        GlobalScope.launch(Dispatchers.IO) {
            myService.syncLocalDatabaseWithServer()
        }
    }*/

    KoinApplication(application = {
        modules(
            *sharedModules
        )}) {
        //val abc = koin.get<BirdRepository>()
        //val abc = koin.get<BirdRepository>()

/*        val myService  = koinInject<BirdRepository>()
        GlobalScope.launch(Dispatchers.IO) {
            myService.syncLocalDatabaseWithServer()
        }*/
        BirdAppTheme {
            //val birdsViewModel = getViewModel(Unit, viewModelFactory { BirdsViewModel() })
            //BirdsPage(birdsViewModel)
        }
    }
}

@Composable
fun BirdsPage(viewModel: BirdsViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        Modifier.fillMaxWidth(),/**/
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            for (category in uiState.categories) {
                viewModel.selectedCategory(category)
                Text(text = category)

                /*               Button(
                                   onClick = {
                                       viewModel.selectedCategory(category)
                                   },
                                   modifier = Modifier.aspectRatio(1.0f).fillMaxWidth().weight(1.0f),
                                   elevation = ButtonDefaults.elevation(
                                       defaultElevation = 5.dp,
                                       focusedElevation = 5.dp
                                   )
                               ) {
                                   Text(category)
                               }*/
            }
        }
        AnimatedVisibility(uiState.categories.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(6),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                content = {
                    item {
                        Header("it")
                    }
                    items(uiState.selectedImage) {
                        BirdImageCell(it)
                    }
                }
            )
        }
    }
}

@Composable
fun Header(categoryName: String) {
    Text(categoryName)
}

@Composable
fun BirdImageCell(image: BirdImage) {
    KamelImage(
        asyncPainterResource("https://sebi.io/demo-image-api/${image.path}"),
        contentDescription = "${image.category} by ${image.author}",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth().aspectRatio(1.4f).shadow(
            elevation = 16.dp,
            shape = RoundedCornerShape(8.dp)
        )
    )
}

expect fun getPlatformName(): String