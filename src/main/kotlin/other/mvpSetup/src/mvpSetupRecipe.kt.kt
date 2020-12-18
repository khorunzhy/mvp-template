package other.mvpSetup.src

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.github.khorunzhy.mvpsetup.listeners.MyProjectManagerListener.Companion.projectInstance
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import org.jetbrains.kotlin.idea.KotlinLanguage
import other.mvpSetup.src.app_package.fragmentLayout
import other.mvpSetup.src.app_package.component
import other.mvpSetup.src.app_package.fragment
import other.mvpSetup.src.app_package.interactor
import other.mvpSetup.src.app_package.module
import other.mvpSetup.src.app_package.presenter
import other.mvpSetup.src.app_package.router
import other.mvpSetup.src.app_package.userInteraction

fun RecipeExecutor.mvpSetup(
  moduleData: ModuleTemplateData,
  packageName: String,
  entityName: String,
  layoutName: String
) {
  val (projectData) = moduleData
  val project = projectInstance ?: return

  addAllKotlinDependencies(moduleData)

  val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
  val virtSrc = virtualFiles.first { it.path.contains("src") }
  val virtRes = virtualFiles.first { it.path.contains("res") }
  val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)!!
  val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)!!
  val viewName = "${entityName}View"
  val presenterName = "${entityName}Presenter"
  val userInteractionName = "${entityName}UserInteraction"
  val moduleName = "${entityName}Module"
  val componentName = "${entityName}Component"
  val interactorName = "${entityName}Interactor"
  val routerName = "${entityName}Router"

  fragmentLayout()
      .save(directoryRes, "layout", "${layoutName}.xml")

  fragment(packageName, entityName, viewName, presenterName, userInteractionName, layoutName, projectData)
      .save(directorySrc, packageName, "${viewName}.kt")

  userInteraction(packageName, userInteractionName)
      .save(directorySrc, packageName, "${userInteractionName}.kt")

  component(packageName, presenterName, moduleName, componentName)
      .save(directorySrc, packageName, "${componentName}.kt")

  module(packageName, presenterName, moduleName, interactorName, routerName)
      .save(directorySrc, packageName, "${moduleName}.kt")

  interactor(packageName, interactorName)
      .save(directorySrc, packageName, "${interactorName}.kt")

  presenter(packageName, presenterName, viewName, userInteractionName, interactorName, routerName)
      .save(directorySrc, packageName, "${presenterName}.kt")

  router(packageName, routerName)
      .save(directorySrc, packageName, "${routerName}.kt")
}

fun String.save(srcDir: PsiDirectory, subDirPath: String, fileName: String) {
  try {
    val destDir = subDirPath.split(".").toDir(srcDir)
    val psiFile = PsiFileFactory
        .getInstance(srcDir.project)
        .createFileFromText(fileName, KotlinLanguage.INSTANCE, this)
    destDir.add(psiFile)
  }catch (exc: Exception) {
    exc.printStackTrace()
  }
}

fun List<String>.toDir(srcDir: PsiDirectory): PsiDirectory {
  var result = srcDir
  forEach {
    result = result.findSubdirectory(it) ?: result.createSubdirectory(it)
  }
  return result
}