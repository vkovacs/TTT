import groovy.json.JsonSlurper

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

final int BUDAPEST_REGION = 8
final int BUDAI_HEGYSEG_REGION = 9

def inputFile = new File("../resources/all-hikes.json").readLines()
JsonSlurper jsonSlurper = new JsonSlurper()
def allHikes = jsonSlurper.parseText(inputFile[0]) as List

allHikes
        .findAll { it.regions.contains(BUDAPEST_REGION) || it.regions.contains(BUDAI_HEGYSEG_REGION) }
        .forEach {
            println it
            def httpClient = HttpClient.newHttpClient()
            def hikeDetailsRequest = HttpRequest.newBuilder(new URI("https://tturak.hu/api/hikeoccasion/${it.id}")).GET().build()
            def hikeDetailsResponse = httpClient.send(hikeDetailsRequest, HttpResponse.BodyHandlers.ofString())
            def description = new JsonSlurper().parseText(hikeDetailsResponse.body()).description

            new File("../resources/${it.id}").write(description == null ? "" : description)
        }

