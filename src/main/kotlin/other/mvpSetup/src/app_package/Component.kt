package other.mvpSetup.src.app_package

fun component(
  packageName: String,
  presenterName: String,
  moduleName: String,
  componentName: String,
) = """package $packageName

import dagger.Subcomponent

@Subcomponent(modules = [${moduleName}::class])
interface $componentName {

  fun presenter(): $presenterName

  @Subcomponent.Builder
  interface Builder {

    fun build(): $componentName
  }
}

"""