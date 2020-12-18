package com.github.khorunzhy.mvpsetup.services

import com.intellij.openapi.project.Project
import com.github.khorunzhy.mvpsetup.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
