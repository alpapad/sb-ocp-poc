## What & Why
This project will build a docker-image which will export a `volume` holding the files added by the Dockerfile.

It is used to "mimic" a kubernetes config map, which is "mountable" in a pod in OCP. It adds the file `/app/configuration/app.env`, which is evaluated by `/app/entrypoint.sh` in the base image (it is loaded and variables are exposed to the spring boot application). 

In this poc, it is used to expose the server hostname for the client application:
  - so the client application can be started standalone via the IDE -- and reach localhost--, 
  - or resolve $APP_GRPC_SERVER_HOST which points to `spring-grpc-server->hostname` (`grpcserver`) in the docker-compose.yml file in the root folder of this project

This way, the images are production-ready and can be used unchanged in docker and ocp.


## How
 - Create a service in docker-compose, like the following:
 
```
   config-map:
    image: alpapad/dummy-config-map:latest
    container_name: config-map
```

Then map the volumes exposed by the image like the following example:

```
  another-service:
...
    volumes_from:
      - config-map:ro
```


The version of the docker-compose.yml should be 2 (I guess it can be done with v3, but it will be more verbose...)

The `config-map` container will be started by docker, its volumes will be exposed and it will stop, as it does not have a command or entrypoint which runs something meaningfull.


## Variables

Prefix variables using a project specific prefix. This demo uses `APP_` . This:
 - will help differentiate them from other variables your base image might be using, 
 - it is easier for developers/readers to understand that these variables are to be used in application.yml files 
 - and it will allow to build an override mechanism for docker-compose files 
    - not implemented here, so as the file is the last to be evaluated, it will override variables set via `environment` in docker-compose