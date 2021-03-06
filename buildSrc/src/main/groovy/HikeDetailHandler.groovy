import crs.ttt.domain.Regions
import groovy.json.JsonSlurper

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

def inputFile = new File("datasource/all-hikes.json").readLines()
JsonSlurper jsonSlurper = new JsonSlurper()
def allHikes = jsonSlurper.parseText(inputFile[0]) as List

allHikes
        .findAll { it.regions.contains(Regions.BUDAPEST_REGION) || it.regions.contains(Regions.BUDAI_HEGYSEG_REGION) }
        .forEach {
            println it
            def httpClient = HttpClient.newHttpClient()
            def hikeDetailsRequest = HttpRequest.newBuilder(new URI("https://tturak.hu/api/hikeoccasion/${it.id}")).GET().build()
            def hikeDetailsResponse = httpClient.send(hikeDetailsRequest, HttpResponse.BodyHandlers.ofString())
            String description = new JsonSlurper().parseText(hikeDetailsResponse.body()).description

            new File("datasource/${it.id}").write(description == null ? "" : description.replaceAll(/&nbsp;/, ' '), "utf-8")
        }

