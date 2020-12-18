package other.mvpSetup.src

import com.android.tools.idea.wizard.template.*

val mvpSetupTemplate
  get() = template {
    revision = 2
    name = "Setup with MVP Components"
    description = "Creates all components for new feature"
    minApi = 16
    minBuildApi = 16
    category = Category.Other // Check other categories
    formFactor = FormFactor.Mobile
    screens = listOf(WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
        WizardUiContext.NewProject, WizardUiContext.NewModule)

    val packageNameParam = defaultPackageNameParameter
    val entityName = stringParameter {
      name = "Entity Name"
      default = "Entity"
      help = "The name of the entity class to create and use in Fragment"
      constraints = listOf(Constraint.NONEMPTY)
    }

    val layoutName = stringParameter {
      name = "Layout Name"
      default = "entity"
      help = "The name of the layout to create for the fragment"
      constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
      suggest = { fragmentToLayout(entityName.value.toLowerCase()) }
    }

    widgets(
        TextFieldWidget(entityName),
        TextFieldWidget(layoutName),
        PackageNameWidget(packageNameParam)
    )

    recipe = { data: TemplateData ->
      mvpSetup(
          data as ModuleTemplateData,
          packageNameParam.value,
          entityName.value,
          layoutName.value
      )
    }
  }

val defaultPackageNameParameter get() = stringParameter {
  name = "Package name"
  visible = { !isNewModule }
  default = "ru.drinkit"
  constraints = listOf(Constraint.PACKAGE)
  suggest = { packageName }
}