pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    versionCatalogs{
        create("libs"){
            library("core-ktx", "androidx.core:core-ktx:1.10.1")
            library("lifecycle-runtime-ktx", "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
            library("activity-compose", "androidx.activity:activity-compose:1.7.2")
            library("junit", "junit:junit:4.13.2")
            library("ext-junit", "androidx.test.ext:junit:1.1.5")
            library("espresso-core", "androidx.test.espresso:espresso-core:3.5.1")
            library("airbnb-lottie-compose", "com.airbnb.android:lottie-compose:6.0.0")
            library("compose-bom","androidx.compose:compose-bom:2023.04.01")
            library("compose_material_icon_extended","androidx.compose.material:material-icons-extended:1.4.3")
            library("compose_navigation","com.google.accompanist:accompanist-navigation-animation:0.30.1")
        }
    }
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Truly News"
include(":app",":data")
