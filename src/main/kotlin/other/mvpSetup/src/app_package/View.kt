package other.mvpSetup.src.app_package

import com.android.tools.idea.wizard.template.ProjectTemplateData

fun fragment(
  packageName: String,
  entityName: String,
  viewName: String,
  presenterName: String,
  interactionViewName: String,
  layoutName: String,
  projectData: ProjectTemplateData
) = """package $packageName

import ru.drinkit.ui.common.presentation.View
import ru.drinkit.ui.common.CommonFragment
import ru.drinkit.app.AppComponent
import ${projectData.applicationPackage}.R
import ${projectData.applicationPackage}.R.layout

interface $viewName : View 

class ${entityName}Fragment : CommonFragment<${viewName}>(
    layout.${layoutName}
), ${viewName}, $interactionViewName {
	
  private lateinit var presenter: $presenterName

  override fun onInjectDependencies(injector: AppComponent) {
    presenter = injector
        .plus${entityName}Component()
        .build()
        .presenter()
  }

  override fun providePresenter() = presenter
}
"""
fun fragmentLayout() = """<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >
</androidx.constraintlayout.widget.ConstraintLayout>
"""