package ru.mobileup.codequality

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import ru.mobileup.codequality.issueconverter.IssuesConverterFactory

open class CollectCodeQualityIssuesTask : DefaultTask() {

    @InputDirectory
    val inputDirectory: DirectoryProperty = project.objects.directoryProperty()

    @OutputFile
    val outputFile: RegularFileProperty = project.objects.fileProperty()

    @Input
    val collectInformationIssues: Property<Boolean> = project.objects.property(Boolean::class.java)

    @TaskAction
    fun run() {
        IssuesConverterFactory().createIssuesConverter()
            .convertIssues(
                inputDirectory.get().asFile,
                outputFile.get().asFile,
                collectInformationIssues.get()
            )
    }
}