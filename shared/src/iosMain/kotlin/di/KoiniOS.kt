package di
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.koin.dsl.module
import com.myapplication.Database
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoinIos(): KoinApplication = startKoin {
    modules(*sharedModules)
}

/*actual val platformModule = module {
    single<SqlDriver> { NativeSqliteDriver(Database.Schema, "birds") }
}*/

fun Koin.get(objCClass: ObjCClass): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, null, null)
}