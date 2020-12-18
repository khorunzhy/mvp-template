package other

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import other.mvpSetup.src.mvpSetupTemplate

class WizardTemplateProviderImpl  : WizardTemplateProvider() {

  override fun getTemplates(): List<Template> = listOf(mvpSetupTemplate)
}