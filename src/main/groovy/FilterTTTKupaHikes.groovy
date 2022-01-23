import groovy.json.JsonSlurper

def inputFile = new File("../resources/all-hikes.json").readLines()
JsonSlurper jsonSlurper = new JsonSlurper()

def allHikes = jsonSlurper.parseText(inputFile[0]) as List
final int BUDAPEST_REGION = 8
final int BUDAI_HEGYSEG_REGION = 9

println allHikes
        .findAll { it.regions.contains(BUDAPEST_REGION) || it.regions.contains(BUDAI_HEGYSEG_REGION) }
        .collect { new File("../resources/${it.id}").readLines()}
        .collect {it.join()}
        .findAll {isTTTKupa(it)}
        .size()


boolean isTTTKupa(String description) {
    description =~ /(?i).*Budapest kupa.*/ || description =~ /(?i).*TTT kupa.*/
}