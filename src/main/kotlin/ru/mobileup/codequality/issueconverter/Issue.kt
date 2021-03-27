package ru.mobileup.codequality.issueconverter

data class Issue(
    val description: String,
    val severity: Severity,
    val filePath: String,
    val line: Int
)

enum class Severity(val severityName: String) {
    INFO("info"),
    MINOR("minor"),
    MAJOR("major"),
    CRITICAL("critical"),
    BLOCKER("blocker")
}