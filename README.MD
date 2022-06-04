# TTT
Filter TTT hikes in the Budapest and Budapest környéke region and which are part of the TTT coup.
Register these hikes in Google Calendar.

## Prerequisites
### Project credentials
~~~
src/main/resources/credentials.json
~~~
which can be acquired from https://console.cloud.google.com/apis/credentials -> 
OAuth 2.0 Client IDs -> Download JSON for the given project.

### Google Calendar Id
where the events are registered. Can be found at Google Calendars -> Settings -> ${Calendar name} -> Share -> Calendar settings -> Calendar Id

### Fresh user credentials
Delete
~~~
tokens/StoredCredential
~~~

## Usage
### Gradle Tasks: (Under group TTT)
1. DownloadAllHikes: Download all hike data in json format into the datasource folder (all-hikes.json)
2. DownloadHikeDetails: Download details of all the hikes in the specified regions. Only the details contains the information if a hike is part of the TTT coup or not.

### List events of the given calendar
~~~
gw run --args "asd@group.calendar.google.com"
~~~

### Execute hike insertion:
~~~
gw run --args "asd@group.calendar.google.com insert-hikes"
~~~

To upload hikes into the specifies calendar which are part of the TTT coup.

## Debug
Apply the following JVM Arguments to log for google's the http clients. (application plugin crs.ttt.config in build.gradle)
~~~
-Djava.util.logging.crs.ttt.config.file=logging.properties
~~~