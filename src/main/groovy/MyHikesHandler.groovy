import groovy.json.JsonSlurper
import groovy.transform.Field

import java.time.LocalDateTime
import java.time.ZoneOffset

@Field
static final ZoneOffset myZoneOffset = ZoneOffset.of("+01:00")

def inputFile = new File("../resources/all-hikes.json").readLines()
JsonSlurper jsonSlurper = new JsonSlurper()

def allHikes = jsonSlurper.parseText(inputFile[0]) as List

def myHikes = allHikes
        .findAll { it.regions.contains(Regions.BUDAPEST_REGION) || it.regions.contains(Regions.BUDAI_HEGYSEG_REGION) }
        .findAll {
            def hikeDetails = new File("../resources/${it.id}").readLines().join()
            isTTTKupa(hikeDetails)
        }
        .collect {
            new Hike(id: it.id, name: it.displayName, location: it.routes[0].route[0], startTime: LocalDateTime.ofEpochSecond(it.routes[0].startTimeFrom as long, 0, myZoneOffset), endTime: LocalDateTime.ofEpochSecond(it.routes[0].startTimeTo as long, 0, myZoneOffset),  url: "https://tturak.hu/hikeOccasion/${it.id}/details")
        }

println(myHikes)

boolean isTTTKupa(String description) {
    description =~ /(?i).*Budapest kupa.*/ || description =~ /(?i).*TTT kupa.*/
}