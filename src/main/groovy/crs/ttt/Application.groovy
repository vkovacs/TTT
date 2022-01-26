package crs.ttt

import crs.ttt.service.CalendarService
import crs.ttt.service.HikeService

class Application {
    static void main(String[] args) {
        assert args.length > 0 : "Please provide calendar id"

        def hikeService = new HikeService()
        def tttCoupHikes = hikeService.filter()

        def calendarId = args[0]

        def calendarService = new CalendarService()
        calendarService.printEvents(calendarId)

        if (args.length > 1 && args[1] == "insert-hikes") {
            calendarService.insertHikes(calendarId, tttCoupHikes)
        }
    }
}
