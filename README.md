Merpar recruitment process application
====================

Content analyzer
---------------------

RESTful web service to analyze xml files 


To build a tagged docker image run:

`./mvnw install dockerfile:build`

To run docker image with content analyzer service exoposed on port 8080 :

`docker run -p 8080:8080  macd3b/content-analyzer:latest`

Example Request:

```
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
  "url": "https://s3-eu-west-1.amazonaws.com/merapar-assessment/arabic-posts.xml"
}' \
 'http://localhost:8080/analyze/'
 ```