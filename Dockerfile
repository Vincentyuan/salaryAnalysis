FROM sebp/elk:502

ADD ./start.sh /usr/local/bin/start.sh
RUN chmod +x /usr/local/bin/start.sh

EXPOSE 5601 9200 9300
VOLUME /var/lib/elasticsearch

ADD ./target/sample-java-elasticsearch-client-jar-with-dependencies.jar /usr/local/lib/sample-java-elasticsearch-client-jar-with-dependencies.jar
ADD ./data /data
ADD ./Kibana_export/export.json /tmp/kibana-export.json

CMD [ "/usr/local/bin/start.sh" ]
