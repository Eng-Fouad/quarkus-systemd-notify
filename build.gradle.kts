plugins {
    java
    id("io.quarkus")
}

group = "io.fouad.examples"
version = "0.1"

repositories {
    mavenCentral()
    mavenLocal()
}

// project properties can be accessed via delegation
val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(platform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.compilerArgs = listOf("-parameters", "-Xdoclint:none", "-Xlint:all", "-Xlint:-exports",
                                      "-Xlint:-serial", "-Xlint:-try", "-Xlint:-requires-transitive-automatic",
                                      "-Xlint:-requires-automatic", "-Xlint:-missing-explicit-ctor")
    }

    compileTestJava {
        options.encoding = "UTF-8"
        options.compilerArgs = listOf("-parameters", "-Xdoclint:none", "-Xlint:all", "-Xlint:-exports",
                                      "-Xlint:-serial", "-Xlint:-try", "-Xlint:-requires-transitive-automatic",
                                      "-Xlint:-requires-automatic", "-Xlint:-missing-explicit-ctor")
    }
    
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
    
    test {
        useJUnitPlatform()
    }
    
    quarkusDev {
        workingDirectory.set(rootProject.projectDir)
    }
}