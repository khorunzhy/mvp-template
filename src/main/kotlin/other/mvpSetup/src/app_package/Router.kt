package other.mvpSetup.src.app_package

fun router(
  packageName: String,
  routerName: String,
) = """package $packageName

import ru.drinkit.ui.common.navigation.Navigator

interface $routerName 

class ${routerName}Impl(private val navigator: Navigator) : $routerName


"""