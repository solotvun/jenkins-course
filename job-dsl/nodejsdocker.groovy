job('NodeJS Docker example') {
    scm {
        git('https://github.com/solotvun/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('solotvun')
            node / gitConfigEmail('solotvun@mail.ru')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('solotvun/nodejs')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('docker_hub_login')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
