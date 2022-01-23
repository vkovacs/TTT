import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

def getHikesForThisYearRequest = HttpRequest.newBuilder(new URI("https://tturak.hu/api/hikeoccasion/list?from=1640991600&to=1672527599.999")).GET().build()
def httpClient = HttpClient.newHttpClient()
def getHikesForThisYearResponse = httpClient.send(getHikesForThisYearRequest, HttpResponse.BodyHandlers.ofString())
new File("../resources/all-hikes.json").write(getHikesForThisYearResponse.body())