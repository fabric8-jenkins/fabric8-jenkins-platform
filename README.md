# Fabric8 Jenkins

## Quickstart
If you want to get going quickly with auth disabled in Jenkins then run:
```
gofabric8 deploy --namespace fabric8 --package=jenkins --legacy=false
```

If you want Single Sign On then we currently support GitHub Oauth out of the box:
```
gofabric8 deploy --namespace fabric8 --package=jenkins-sso --legacy=false
```

## What's in the box?

Jenkins
Artifact repository (currently defaults to Nexus)
Keycloak (Optional but needed for Single Sign On)

## FAQ

### Why Nexus 2 and not Nexus 3
We tried Nexus 3 but there was significant increase in build times so right now we deem it not usable.

To try it we have an app you can install:
```
kubectl apply -f http://central.maven.org/maven2/io/fabric8/apps/nexus3-app/1.0.0/nexus3-app-1.0.0-kubernetes.yml
```

You will need to modify the settings.xml in the maven settings secret as the mirror repository URL is different for Nexus3

### Why does Nexus not included in the SSO?
As far as we can tell Nexus 2 doesn't support OAUTH

### What's the credentials for Nexus?
We use the default:
`
admin/admin123
`
If you change this be sure to also update the settings.xml in the maven settings secret so the pipelines can continue to deploy artifacts there

### Can we use our own artifact repository?

Yes, the pipelines use a kubernetes service to deploy artifacts.  So if you have your own internal or external repository then you can modify the `artifact-repository` service that runs in the same namespace as Jenkins.  For external repos looks at using `ExternalName`, see this [link](https://kubernetes.io/docs/concepts/services-networking/service/#without-selectors) for more details.

Make sure to update the server section of the settings.xml in the maven settings secret, to include any new credentials.