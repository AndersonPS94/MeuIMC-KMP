import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.compose.ui:ui:1.5.0") // Adiciona Jetpack Compose Multiplatform
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.compose.ui:ui:1.5.0")
                implementation("androidx.compose.material3:material3:1.2.0") // Material 3
                implementation("androidx.compose.ui:ui-tooling:1.5.0")
                implementation("androidx.compose.foundation:foundation:1.5.0")
                implementation("androidx.compose.ui:ui-graphics:1.5.0")
                implementation("androidx.compose.ui:ui-text:1.5.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "com.example.meuimc"
    compileSdk = 35
    defaultConfig {
        minSdk = 24 // Ajuste para 24 ou menor, pois 34 é alto demais
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
