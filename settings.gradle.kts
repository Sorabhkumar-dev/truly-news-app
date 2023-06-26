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
            library("activity-compose", "androidx.activity:activity-compose:1.7.2")
            library("junit", "junit:junit:4.13.2")
            library("ext-junit", "androidx.test.ext:junit:1.1.5")
            library("espresso-core", "androidx.test.espresso:espresso-core:3.5.1")
            library("airbnb-lottie-compose", "com.airbnb.android:lottie-compose:6.0.0")
            library("compose-bom","androidx.compose:compose-bom:2023.04.01")
            library("compose_material_icon_extended","androidx.compose.material:material-icons-extended:1.4.3")
            library("compose_navigation","com.google.accompanist:accompanist-navigation-animation:0.30.1")
            library("compose-material3","androidx.compose.material3:material3:1.1.1")

            /** <---------------------- data module dependency --------------------> */
            // ktor client
            val ktorVersion = "2.3.1"
            library("ktor-client-core","io.ktor:ktor-client-core:$ktorVersion")
            library("ktor-client-android","io.ktor:ktor-client-android:$ktorVersion")
            library("ktor-serialization","io.ktor:ktor-serialization:$ktorVersion")
            library("ktor-serialization-kotlinx-json","io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            library("ktor-client-logging","io.ktor:ktor-client-logging:$ktorVersion")
            library("ktor-client-content-negotiation","io.ktor:ktor-client-content-negotiation:$ktorVersion")

            //kotlin json serializer
            library("kotlinx-serialization-json","org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

            /** kotlin-koin */
            library("koin-android","io.insert-koin:koin-android:3.3.3")
            library("koin-androidx-compose","io.insert-koin:koin-androidx-compose:3.4.2")

            /** lifecycle */
            val lifecycleVersion = "2.6.1"
            library("lifecycle-viewmodel-ktx","androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
            library("lifecycle-viewmodel-compose","androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
            library("lifecycle-runtime-ktx","androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
            library("lifecycle-runtime-compose","androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
            library("lifecycle-compiler","androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")

            /** Preferences DataStore  */
            val dateStore = "1.0.0"
            library("datastore-preferences","androidx.datastore:datastore-preferences:$dateStore")
            library("datastore-preferences-core","androidx.datastore:datastore-preferences-core:$dateStore")

            /** Compose Paging */
            library("paging-compose","androidx.paging:paging-compose:1.0.0-alpha20")

            /** Show image from network */
            library("coil-compose","io.coil-kt:coil-compose:2.4.0")



        }
    }
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Truly News"
include(":app",":data")
