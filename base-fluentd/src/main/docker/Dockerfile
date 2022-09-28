FROM ${fluentd.base.image}

USER root
#RUN ["gem", "install", "fluent-plugin-elasticsearch", "--no-document", "--version", "5.0.3"]
#RUN ["fluent-gem", "install", "fluent-plugin-elasticsearch"]
USER fluent

ADD fluent-elastic.conf /fluentd/etc/fluent.conf