package other.mvpSetup.src.app_package

fun interactor(
  packageName: String,
  interactorName: String
) = """package $packageName

interface $interactorName 

class ${interactorName}Impl : $interactorName

"""