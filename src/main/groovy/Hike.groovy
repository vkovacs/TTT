import groovy.transform.ToString

import java.time.LocalDateTime

@ToString
class Hike {
    private int id
    private String name
    private String location
    private LocalDateTime startTime
    private LocalDateTime endTime
    private String url
}
