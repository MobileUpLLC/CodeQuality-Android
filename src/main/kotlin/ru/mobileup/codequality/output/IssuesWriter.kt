package ru.mobileup.codequality.output

import com.google.gson.Gson
import ru.mobileup.codequality.issueconverter.Issue
import java.io.File
import java.io.OutputStreamWriter
import java.security.MessageDigest

private class IssueJson(
    val description: String,
    val fingerprint: String,
    val severity: String,
    val location: LocationJson
)

private class LocationJson(
    val path: String,
    val lines: LinesJson
)

private class LinesJson(
    val begin: Int
)

class IssuesWriter {

    fun write(issues: List<Issue>, outputFile: File) {
        val issuesJson = issues.map { issue ->
            IssueJson(
                description = issue.description,
                fingerprint = calculateFingerprint(issue),
                severity = issue.severity.severityName,
                location = LocationJson(
                    path = getRelativePath(issue.filePath, outputFile.parentFile.absolutePath),
                    lines = LinesJson(begin = issue.line)
                )
            )
        }

        OutputStreamWriter(outputFile.outputStream()).use { writer ->
            Gson().toJson(issuesJson, writer)
        }
    }

    private fun getRelativePath(filePath: String, rootPath: String): String {
        val correctedFilePath = filePath.replace('\\', '/')
        val correctedRootPath = rootPath.replace('\\', '/')
        return correctedFilePath.replace("$correctedRootPath/", "")
    }

    private fun calculateFingerprint(issue: Issue): String {
        return md5Hash(issue.description + issue.filePath + issue.line)
    }

    private fun md5Hash(input: String): String {
        return MessageDigest.getInstance("MD5")
            .digest(input.toByteArray())
            .toHexString()
    }

    private fun ByteArray.toHexString() = joinToString("") {
        (0xFF and it.toInt()).toString(16).padStart(2, '0')
    }
}