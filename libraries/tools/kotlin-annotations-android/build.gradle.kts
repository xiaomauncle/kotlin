import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

description = "Kotlin annotations for Android"

apply { plugin("kotlin") }

tasks.withType<KotlinCompile> {
    kotlinOptions.jdkHome = rootProject.extra["JDK_16"]!!.toString()
    kotlinOptions.jvmTarget = "1.6"

    kotlinOptions.freeCompilerArgs += listOf(
            "-Xallow-kotlin-package",
            "-module-name", "kotlin.annotations.android"
    )
}

sourceSets {
    "main" {
        projectDefault()
    }
}

runtimeJar()
dist()
