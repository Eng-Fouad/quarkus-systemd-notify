pluginManagement {
    
    val quarkusPluginId: String by settings
    val quarkusPluginVersion: String by settings
    
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
    
    plugins {
        id(quarkusPluginId) version quarkusPluginVersion
    }
}

rootProject.name = "quarkus-systemd-notify"
include("systemd-notify-jni")