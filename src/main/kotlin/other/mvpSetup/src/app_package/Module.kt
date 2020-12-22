package other.mvpSetup.src.app_package

fun module(
    packageName: String,
    moduleFunc: String,
    presenterName: String,
    interactorName: String
)= """package $packageName

import org.kodein.di.DI.Module
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

fun $moduleFunc(): Module = Module("$moduleFunc") {
  bind<${interactorName}>() with provider { ${interactorName}Impl() }
  bind<${presenterName}>() with provider { ${presenterName}Impl(instance(), instance()) }
}

"""
