import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.projectFeatures.activeStorage
import jetbrains.buildServer.configs.kotlin.projectFeatures.awsConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.s3Storage
import jetbrains.buildServer.configs.kotlin.triggers.vcs

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

version = "2022.10"

project {

    buildType(Build)

    features {
        s3Storage {
            id = "PROJECT_EXT_4"
            storageName = "Test old S3"
            bucketName = "yuldashev-test"
            bucketPrefix = "oldS3"
            awsEnvironment = default {
                awsRegionName = "eu-central-1"
            }
            useDefaultCredentialProviderChain = true
        }
        activeStorage {
            id = "PROJECT_EXT_5"
            activeStorageID = "PROJECT_EXT_4"
        }
        awsConnection {
            id = "SomeTestConnection"
            name = "SomeTestConnection"
            credentialsType = default()
        }
    }
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    triggers {
        vcs {
        }
    }
})
