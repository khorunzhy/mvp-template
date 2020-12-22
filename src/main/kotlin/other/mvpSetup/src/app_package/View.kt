package other.mvpSetup.src.app_package

import com.android.tools.idea.wizard.template.ProjectTemplateData

fun fragment(
  packageName: String,
  entityName: String,
  viewName: String,
  interactionViewName: String,
  presenterName: String,
  layoutName: String,
  bindingName: String,
  moduleFunc: String,
  projectData: ProjectTemplateData
) = """package $packageName

import org.kodein.di.DI.Module
import org.kodein.di.direct
import org.kodein.di.instance
import ${projectData.applicationPackage}.R
import ${projectData.applicationPackage}.R.layout
import ${projectData.applicationPackage}.databinding.${bindingName}
import ru.drinkit.ui.view.CommonFragment
import ru.drinkit.ui.view.View
import ru.drinkit.ui.view.viewbinding.viewBinding

interface $viewName : View 

class ${entityName}Fragment : CommonFragment<${viewName}>(
    layout.${layoutName}
), ${viewName}, $interactionViewName {

  private val viewBinding by viewBinding<${bindingName}>()
  
  override fun provideModule(): Module = $moduleFunc()
  override fun providePresenter(): $presenterName = di.direct.instance()

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