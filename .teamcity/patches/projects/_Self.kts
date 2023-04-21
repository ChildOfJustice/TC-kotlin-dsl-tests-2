package patches.projects

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project
import jetbrains.buildServer.configs.kotlin.projectFeatures.AwsConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.awsConnection
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the root project
accordingly, and delete the patch script.
*/
changeProject(DslContext.projectId) {
    features {
        val feature1 = find<AwsConnection> {
            awsConnection {
                id = "SomeTestConnection"
                name = "SomeTestConnection"
                credentialsType = static {
                    accessKeyId = "test"
                    secretAccessKey = "credentialsJSON:0b0df72f-376a-46b3-901d-396e3025e721"
                }
                param("awsSessionDuration", "60")
            }
        }
        feature1.apply {
        }
    }
}
