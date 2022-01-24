import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.LocalDateTime
import java.time.ZoneOffset

String allHikesQueryUrl(int year) {
    def from = LocalDateTime.of(2022, 01, 01, 00, 00, 00).toEpochSecond(ZoneOffset.UTC)
    def to = LocalDateTime.of(2022, 12, 31, 23, 59, 59).toEpochSecond(ZoneOffset.UTC)

    "https://tturak.hu/api/hikeoccasion/list?from=${from}&to=${to}"
}

final int year = 2022

def getHikesForThisYearRequest = HttpRequest.newBuilder(new URI(allHikesQueryUrl(year))).GET().build()
def httpClient = HttpClient.newHttpClient()
def getHikesForThisYearResponse = httpClient.send(getHikesForThisYearRequest, HttpResponse.BodyHandlers.ofString())

new File("build/hike/all-hikes.json").write(getHikesForThisYearResponse.body())