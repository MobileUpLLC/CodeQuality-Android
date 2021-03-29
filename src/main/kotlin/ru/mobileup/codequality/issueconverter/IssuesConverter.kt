package ru.mobileup.codequality.issueconverter

import ru.mobileup.codequality.input.DetektParser
import ru.mobileup.codequality.input.LintParser
import ru.mobileup.codequality.output.IssuesWriter
import java.io.File
import java.io.FileNotFoundException

class IssuesConverter(
    private val detektParser: DetektParser,
    private val lintParser: LintParser,
    private val issuesWriter: IssuesWriter
) {

    fun convertIssues(inputDirectory: File, outputFile: File, collectInformationIssues: Boolean) {
        if (!inputDirectory.exists() || !inputDirectory.isDirectory) {
            throw FileNotFoundException("Directory '${inputDirectory.absolutePath}' is not exists!")
        }

        val issues = mutableListOf<Issue>()

        inputDirectory.walk()
            .filter { it.name.endsWith(".xml") }
            .forEach { file ->
                when {
                    file.name.startsWith("detekt") -> issues += detektParser.parse(file)
                    file.name.startsWith("lint") -> issues += lintParser.parse(file)
                }
            }

        val filteredIssues = if (collectInformationIssues) {
            issues
        } else {
            issues.filter { it.severity != Severity.INFO }
        }

        issuesWriter.write(filteredIssues, outputFile)
    }
}