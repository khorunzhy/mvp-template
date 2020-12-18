package other.mvpSetup.src.app_package

fun userInteraction(
  packageName: String,
  userInteractionName: String,
) = """package $packageName

import ru.drinkit.ui.common.UserInteraction

interface $userInteractionName : UserInteraction


"""