PoC showcasing building dockerized spring boot applications ready to run in OCP

## Logging
The `base-logging` module is used to enable logging output as json to the console (later on might hold logging to a fluentd daemon).  2 implementations exist at the moment, one to log to console and one to files. It uses the logstash encoders and can be adapted to log extra fields, either as extraFields or as json snippets.


## Base docker image 
A base docker image is build using the fabric8 maven plugin. This plugin is capable of replacing maven properties in the dockerfiles, so it is possible to have a parametric `FROM ${image}` directive. The base docker image is build so the applications using it will just need copy the jar file in a specific location/name. The base image is also responsible for switching between logback implementations depending on the environment (one for OCP, one for dockerized development environments)

## Spring boot apps
There are 2 spring boot applications, a grpc client and a server. These, are configured so the resultin application is packaged as a docker image and a corsponding helm chart is build (and could be deployed). The charts build are not working, they are just some vanilla charts to showcase the helm plugin.

### Helm plugin
The helm plugin, as it is configured now, will try to download the helm binary from the net. This can be changed, an existing helm binary can be used (for air-gapped envs) or it can be configured to download one from a different location. As explained above, the charts are dummy, just to show-case that charts are actually build.
The plugin is capable of using authentication info configured in settings.xml

### Docker plugin
The fabric8 docker plugin is used (not the spotify one), which is capable of replacing maven variables in the dockerfile. The plugin is capable of using authentication info configured in settings.xml

### Elastic, Kibana, fluentd


Navigate to http://localhost:5601/app/management/kibana/indexPatterns/create and create an index
using as index pattern name `fluentd-*` and and as primary time filed `@timestamp`

In `Discover` view, select `fluentd-*` as index name and you can browse the logs

 

## Future work
 - Implement proper helm charts, allowing the application to be show-cased in a live OCP instance
 - Implement a master helm chart, so applications can be bundled as a release
 - Add a fluentd demo case
 - Improve the base image with remote debugging options/configuration (missing, but one can set the variables in docker-compose)
 - Expose more information from the pods for logging
    - showcase opentelemetry headers logging