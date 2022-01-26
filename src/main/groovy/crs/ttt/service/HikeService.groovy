package crs.ttt.service

import crs.ttt.config.Configuration
import crs.ttt.domain.Hike
import crs.ttt.domain.Regions
import groovy.json.JsonSlurper

import java.time.LocalDateTime

class HikeService {

    List<Hike> filter() {
        def inputFile = new File("datasource/all-hikes.json").readLines()
        JsonSlurper jsonSlurper = new JsonSlurper()

        def allHikes = jsonSlurper.parseText(inputFile[0]) as List

        return allHikes
                .findAll { it.regions.contains(Regions.BUDAPEST_REGION) || it.regions.contains(Regions.BUDAI_HEGYSEG_REGION) }
                .findAll {
                    def hikeDetails = new File("datasource/${it.id}").readLines().join()
                    isTTTKupa(hikeDetails)
                }
                .collect {
                    new Hike(id: it.id, name: it.displayName, location: it.routes[0].route[0], startTime: LocalDateTime.ofEpochSecond(it.routes[0].startTimeFrom as long, 0, Configuration.myZoneOffset), endTime: LocalDateTime.ofEpochSecond(it.routes[0].startTimeTo as long, 0, Configuration.myZoneOffset),  url: "https://tturak.hu/hikeOccasion/${it.id}/details")
                }

    }

    boolean isTTTKupa(String description) {
        description =~ /(?i).*Budapest kupa.*/ || description =~ /(?i).*TTT kupa.*/
    }
}
