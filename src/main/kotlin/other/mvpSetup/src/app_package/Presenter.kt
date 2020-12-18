package other.mvpSetup.src.app_package

fun presenter(
  packageName: String,
  presenterName: String,
  viewName: String,
  userInteractionName: String,
  interactorName: String,
  routerName: String,
) = """package $packageName

import ru.drinkit.ui.common.CommonInteractionPresenter
import ru.drinkit.ui.common.presentation.Presenter

interface $presenterName : Presenter<${viewName}>

class ${presenterName}Impl (
	private val interactor: ${interactorName},
	private val router: ${routerName} 
) : CommonInteractionPresenter<${viewName}, ${userInteractionName}>(), $presenterName {

}


"""