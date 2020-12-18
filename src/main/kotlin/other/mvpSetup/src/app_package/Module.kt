package other.mvpSetup.src.app_package

fun module(
    packageName: String,
    presenterName: String,
    moduleName: String,
    interactorName: String,
    routerName: String,
)= """package $packageName

import ru.drinkit.ui.common.navigation.Navigator
import dagger.Module
import dagger.Provides

@Module
class $moduleName {

    @Provides
    fun provide${interactorName}(): $interactorName = ${interactorName}Impl()

    @Provides
    fun provide${routerName}(navigator: Navigator): $routerName = ${routerName}Impl(navigator)

    @Provides
    fun provide${presenterName}(
      interactor: ${interactorName}, 
      router: ${routerName}): $presenterName = ${presenterName}Impl(interactor, router)

}

"""
