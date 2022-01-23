import groovy.json.JsonSlurper

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

final int BUDAPEST_REGION = 8
final int BUDAI_HEGYSEG_REGION = 9

def getHikesRequest = HttpRequest.newBuilder(new URI("https://tturak.hu/api/hikeoccasion/list?from=1640991600&to=1672527599.999")).GET().build()
def hikesResponse = HttpClient.newHttpClient().send(getHikesRequest, HttpResponse.BodyHandlers.ofString())
def hikes = new JsonSlurper().parseText(hikesResponse.body())

def aroundBudapestHikes = hikes
        .findAll { it.regions.contains(BUDAPEST_REGION) || it.regions.contains(BUDAI_HEGYSEG_REGION) }

println(aroundBudapestHikes.size())
