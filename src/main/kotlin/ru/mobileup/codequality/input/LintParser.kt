package ru.mobileup.codequality.input

import org.w3c.dom.Element
import ru.mobileup.codequality.issueconverter.Issue
import ru.mobileup.codequality.issueconverter.Severity
import java.io.File

class LintParser {

    fun parse(file: File): List<Issue> {
        val document = parseDocument(file)
        val root = document.documentElement
        val issues = mutableListOf<Issue>()

        for (issueElement in root.childElements) {
            issues += parseIssue(issueElement)
        }
        return issues
    }

    private fun parseIssue(issueElement: Element): Issue {
        val locationElement = issueElement.childElements.firstOrNull { it.nodeName == "location" }
        return Issue(
            description = issueElement.getAttribute("message").orEmpty(),
            severity = issueElement.getAttribute("severity").toSeverity(),
            filePath = locationElement?.getAttribute("file").orEmpty(),
            line = locationElement?.getAttribute("line")?.toIntOrNull() ?: 0
        )
    }

    private fun String?.toSeverity(): Severity = when (this) {
        "Error" -> Severity.ERROR
        "Warning" -> Severity.WARNING
        else -> Severity.INFORMATION
    }
}