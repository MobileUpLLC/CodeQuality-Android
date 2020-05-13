package ru.mobileup.codequality.issueconverter

data class Issue(
    val description: String,
    val severity: Severity,
    val filePath: String,
    val line: Int
)

enum class Severity {
    INFORMATION, WARNING, ERROR
}