# Code Quality for Android
[![Maven Central](https://img.shields.io/maven-central/v/ru.mobileup/rxpagingloading)](https://repo1.maven.org/maven2/ru/mobileup/rxpagingloading)[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Gradle Plugin for integration of code quality tools with Gitlab CI.
It converts Detekt and Lint reports to compatible with [Gitlab Code Quality](https://docs.gitlab.com/ee/user/project/merge_requests/code_quality.html) format.

## Usage

1) Add dependency
```
buildscript {
    repositories {
        ...
        mavenCentral()
    }

    dependencies {
        ...
        classpath 'ru.mobileup:code-quality-android:1.0.0'
    }
}
```

2) Setup plugin
```
apply plugin: 'ru.mobileup.code-quality-android'

codeQuality {
    reportsDirectory = file('build/reports')
    issuesFile = rootProject.file('code_quality_issues.json')
    collectInformationIssues = false
}
```

3) Run your Detekt and Lint checks. Report file names for Detekt have to match "detekt\*.xml", and for Lint - "lint\*.xml".

4) Run `gradlew collectCodeQualityIssues`. 
Generated file (code_quality_issues.json) can be uploaded as [Gitlab Code Quality artifact](https://docs.gitlab.com/ee/ci/pipelines/job_artifacts.html#artifactsreportscodequality-starter).

## License
```
MIT License

Copyright (c) 2020 MobileUp

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
