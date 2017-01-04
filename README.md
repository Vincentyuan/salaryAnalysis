

prepare :
1.install docker on your computer and run docker.
2.install command line mvn
3.make sure the port 9200 and 5601 is not in use.


how to run the project 
1. Build the project : "mvn clean package assembly:single"

2. Build the container : "docker build -t template:0.0.1 .""

3. Run the container locally : "docker run -p 9200:9200 -p 5601:5601 template:0.0.1"

4. Browse http://localhost:9200 and http://localhost:5601

5. Browse http://localhost:5601
   Uncheck "Index contains time-based events"
   Replace "logstash-*" by "sf" and click on "Create"
   Click on "Saved Objects"
   Click on "Import" and select the file in kibana_export/export.json
