package ru.mobileup.codequality.issueconverter

import ru.mobileup.codequality.input.DetektParser
import ru.mobileup.codequality.input.LintParser
import ru.mobileup.codequality.output.IssuesWriter


class IssuesConverterFactory {

    fun createIssuesConverter(): IssuesConverter {
        return IssuesConverter(
            DetektParser(),
            LintParser(),
            IssuesWriter()
        )
    }
}