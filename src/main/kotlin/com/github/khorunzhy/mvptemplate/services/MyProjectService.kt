package com.github.khorunzhy.mvptemplate.services

import com.intellij.openapi.project.Project
import com.github.khorunzhy.mvptemplate.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
