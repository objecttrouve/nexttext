
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.20")
    id("org.jetbrains.dokka").version("0.10.1")
    id("me.champeau.gradle.jmh").version("0.5.0")
    id ("com.github.hierynomus.license").version("0.15.0")
    id("com.dorongold.task-tree").version("1.5")
    id("net.researchgate.release") version "2.8.1"
    signing
    `maven-publish`
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.hamcrest:hamcrest:2.1")
    testImplementation("org.objecttrouve:convenience-matchers:1.0.0")
    testImplementation("com.bitplan:mediawiki-japi:0.1.02")
    testImplementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")
    testImplementation("org.eclipse.persistence:eclipselink:2.7.4")
    testImplementation("com.google.guava:guava:27.1-jre")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")
    testImplementation("org.apache.commons:commons-csv:1.6")

    jmh("com.google.guava:guava:27.1-jre")
    jmh("commons-io:commons-io:2.7")

}

jmh {
    isIncludeTests = false
}

license {
    header = file("license/header.txt")
    strictCheck = true
    includes(mutableSetOf("**/*.kt", "**/*.java"))
}

publishing {
    publications {
        create<MavenPublication>("mavenKotlin") {
            groupId = "org.objecttrouve"
            pom {
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("objecttrouve")
                        name.set("O. Trouvé")
                        email.set("un.object.trouve@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git://github.com/objecttrouve/nexttext.git")
                    developerConnection.set("scm:https://github.com/objecttrouve/nexttext.git")
                    url.set("https://github.com/objecttrouve/nexttext")
                }
            }
        }
    }
    repositories {
        maven {
            name = "nextTextOnSonatype"
            // change URLs to point to your repos, e.g. http://my.org/repo
            val releasesRepoUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
        }
    }
}

signing {
    sign(publishing.publications["mavenKotlin"])
}

tasks {

    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }

    val dokka by getting(DokkaTask::class) {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokka"
    }

    val dokkaJar by creating(Jar::class) {
        group = JavaBasePlugin.DOCUMENTATION_GROUP
        description = "Assembles Kotlin docs with Dokka."
        archiveClassifier.set("javadoc")
        exclude("nexttext/org.objecttrouve.nexttext.benchmarks/**")
        from(dokka)
        dependsOn(dokka)
    }

    named("afterReleaseBuild") {
        dependsOn("publish")
    }

    artifacts {
        archives(sourcesJar)
        archives(jar)
        archives(dokkaJar)
    }




}



val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}