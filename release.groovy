#!/usr/bin/groovy
def stage(){
  return stageProject{
    project = 'fabric8-jenkins/fabric8-jenkins-platform'
    useGitTagForNextVersion = true
  }
}

def release(project){
  releaseProject{
    stagedProject = project
    useGitTagForNextVersion = true
    helmPush = false
    groupId = 'io.fabric8.jenkins'
    githubOrganisation = 'fabric8-jenkins'
    artifactIdToWatchInCentral = 'parent'
    artifactExtensionToWatchInCentral = 'pom'
    promoteToDockerRegistry = 'docker.io'
    dockerOrganisation = 'fabric8'
    imagesToPromoteToDockerHub = []
    extraImagesToTag = null
  }
}

return this;
