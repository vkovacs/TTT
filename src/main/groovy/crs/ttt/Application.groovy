package crs.ttt

import crs.ttt.service.CalendarService
import crs.ttt.service.HikeService
import groovy.json.JsonSlurper

class Application {
    static void main(String[] args) {
        assert args.length > 0 : "Please provide calendar id"

        def inputFile = new File("datasource/all-hikes.json").readLines()
        JsonSlurper jsonSlurper = new JsonSlurper()
        def allHikes = jsonSlurper.parseText(inputFile[0]) as List

        def hikeService = new HikeService()
        def tttCoupHikes = hikeService.filter(allHikes)

        def calendarId = args[0]

        def calendarService = new CalendarService()
        calendarService.printEvents(calendarId)

        if (args.length > 1 && args[1] == "insert-hikes") {
            calendarService.insertHikes(calendarId, tttCoupHikes)
        }
    }
}
