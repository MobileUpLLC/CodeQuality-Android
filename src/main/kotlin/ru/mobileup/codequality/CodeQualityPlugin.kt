package ru.mobileup.codequality

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property

open class CodeQualityExtension(objects: ObjectFactory) {
    val reportsDirectory: DirectoryProperty = objects.directoryProperty()
    val issuesFile: RegularFileProperty = objects.fileProperty()
    val collectInformationIssues: Property<Boolean> = objects.property(Boolean::class.java).apply { set(false) }
}

/**
 * Creates task "collectCodeQualityIssues" that Converts Detekt and Lint reports to Gitlab compatible format.
 */
class CodeQualityPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val task = project.tasks.create("collectCodeQualityIssues", CollectCodeQualityIssuesTask::class.java)
        val extension = project.extensions.create("codeQuality", CodeQualityExtension::class.java)
        task.inputDirectory.set(extension.reportsDirectory)
        task.outputFile.set(extension.issuesFile)
        task.collectInformationIssues.set(extension.collectInformationIssues)
    }
}
