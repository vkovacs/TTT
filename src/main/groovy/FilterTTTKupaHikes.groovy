import groovy.json.JsonSlurper

def inputFile = new File("../resources/all-hikes.json").readLines()
JsonSlurper jsonSlurper = new JsonSlurper()

def allHikes = jsonSlurper.parseText(inputFile[0]) as List
final int BUDAPEST_REGION = 8
final int BUDAI_HEGYSEG_REGION = 9

def myHikes = allHikes
        .findAll { it.regions.contains(BUDAPEST_REGION) || it.regions.contains(BUDAI_HEGYSEG_REGION) }
        .findAll {
            def hikeDetails = new File("../resources/${it.id}").readLines().join()
            isTTTKupa(hikeDetails)
        }
        .collect {
            new Hike(id: it.id, name: it.displayName, location: it.routes[0].route[0], startTime: java.time.LocalDateTime.ofEpochSecond(it.routes[0].startTimeFrom, 0, java.time.ZoneOffset.of("+01:00")), endTime:java.time.LocalDateTime.ofEpochSecond(it.routes[0].startTimeTo, 0, java.time.ZoneOffset.of("+01:00")),  url: "https://tturak.hu/api/hikeoccasion/${it.id}")
        }

print(myHikes)


boolean isTTTKupa(String description) {
    description =~ /(?i).*Budapest kupa.*/ || description =~ /(?i).*TTT kupa.*/
}