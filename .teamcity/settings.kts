import jetbrains.buildServer.configs.kotlin.*

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2023.05"

project {

    id("Project")
    name = "My Project"

    buildType(Build)

    subProject {
        id("SubProject")
        name = "My SubProject"

        features {
            createAWSProfile("AWSProfile1", this.id.toString())
        }
    }
}

fun ProjectExtension.createAWSProfile(profileName: String, namespace: String) {

    val projectHash = kotlin.math.abs(profileName.hashCode())
    val profile = "$namespace.$projectHash"

    cloudProfile {
        id(profile)
        name = profileName
        type = "aws"
        param("secure:accessKeyId", DSLContext.getSecret("credentialsJSON:"))
        param("secure:secretAccessKey", DSLContext.getSecret("credentialsJSON:"))
        param("region", "us-west-1")
    }
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "Test build"
            scriptContent = """
                echo "Test"
            """.trimIndent()
        }
    }

    triggers {
        vcs {
        }
    }
})
