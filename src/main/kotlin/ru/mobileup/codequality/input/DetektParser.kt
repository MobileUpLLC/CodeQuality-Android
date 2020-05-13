package ru.mobileup.codequality.input

import org.w3c.dom.Element
import ru.mobileup.codequality.issueconverter.Issue
import ru.mobileup.codequality.issueconverter.Severity
import java.io.File

class DetektParser {

    fun parse(file: File): List<Issue> {
        val document = parseDocument(file)
        val root = document.documentElement
        val issues = mutableListOf<Issue>()

        for (fileElement in root.childElements) {
            val filePath = fileElement.getAttribute("name")
            for (errorElement in fileElement.childElements) {
                issues += parseIssue(errorElement, filePath)
            }
        }
        return issues
    }

    private fun parseIssue(errorElement: Element, filePath: String): Issue {
        return Issue(
            description = errorElement.getAttribute("message").orEmpty(),
            severity = errorElement.getAttribute("severity").toSeverity(),
            filePath = filePath,
            line = errorElement.getAttribute("line").toIntOrNull() ?: 0
        )
    }

    private fun String?.toSeverity(): Severity = when (this) {
        "error" -> Severity.ERROR
        "warning" -> Severity.WARNING
        else -> Severity.INFORMATION
    }
}