plugins {
    java

    id("com.github.johnrengelman.shadow") version("7.1.2")

}

group = "net.shibacraft.core"
version = "0.0.1"

    dependencies {
        implementation(project(":api"))
    }

allprojects{

    apply(plugin = "java")

    repositories {
        mavenCentral()
        mavenLocal()
        maven("https://repo.codemc.io/repository/nms/")
        maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/repositories/central")
        maven("https://repo.unnamed.team/repository/unnamed-public/")
    }

    dependencies{
        implementation("team.unnamed.common:commons-error:2.0.0-SNAPSHOT")
        implementation("me.fixeddev:commandflow-bukkit:0.5.0-SNAPSHOT")
        implementation("org.jetbrains:annotations:22.0.0")
        compileOnly("org.spigotmc:spigot:1.17.1-R0.1-SNAPSHOT") // The Spigot API with no shadowing. Requires the OSS repo.
        compileOnly("me.clip:placeholderapi:2.10.10")
        compileOnly("net.luckperms:api:5.3")
        compileOnly("org.projectlombok:lombok:1.18.22")
    }
}

