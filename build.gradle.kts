plugins {
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.21" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21" apply false
}

buildscript{
    repositories {
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}