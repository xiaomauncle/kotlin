// !LANGUAGE: +MultiPlatformProjects
// MODULE: m1-common
// FILE: common.kt

expect class A {
    <!PRIVATE_EXPECTED_CLASS_MEMBER, JVM:PRIVATE_EXPECTED_CLASS_MEMBER!>private<!> fun foo()
    <!PRIVATE_EXPECTED_CLASS_MEMBER, JVM:PRIVATE_EXPECTED_CLASS_MEMBER!>private<!> val bar: String
    <!PRIVATE_EXPECTED_CLASS_MEMBER, JVM:PRIVATE_EXPECTED_CLASS_MEMBER!>private<!> fun Int.memExt(): Any

    <!PRIVATE_EXPECTED_CLASS_MEMBER, JVM:PRIVATE_EXPECTED_CLASS_MEMBER!>private<!> class Nested
}

// MODULE: m1-jvm(m1-common)
// FILE: jvm.kt

actual class A {
    <!ACTUAL_MISSING!>private fun foo()<!> {}
    <!ACTUAL_MISSING!>private val bar: String<!> = ""
    actual private fun Int.memExt(): Any = 0

    actual private class Nested
}
