package other.mvpSetup.src.app_package

fun router(
  packageName: String,
  routerName: String,
) = """package $packageName

interface $routerName

"""