import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.20")
    id("org.jetbrains.dokka").version("0.10.1")
    id("me.champeau.gradle.jmh").version("0.5.0")
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.hamcrest:hamcrest:2.1")
    testImplementation("org.objecttrouve:convenience-matchers:0.5.0")
    testImplementation("com.bitplan:mediawiki-japi:0.1.02")
    testImplementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")
    testImplementation("org.eclipse.persistence:eclipselink:2.7.4")
    testImplementation("com.google.guava:guava:27.1-jre")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")
    testImplementation("org.apache.commons:commons-csv:1.6")

    jmh("com.google.guava:guava:27.1-jre")
}

jmh {
    isIncludeTests = false
}

tasks {
    val dokka by getting(DokkaTask::class) {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokka"
    }

}

