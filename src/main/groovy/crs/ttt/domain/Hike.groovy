package crs.ttt.domain

import groovy.transform.ToString

import java.time.LocalDateTime

@ToString
class Hike {
    int id
    String name
    String location
    LocalDateTime startTime
    LocalDateTime endTime
    String url
}
