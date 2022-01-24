import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class HikeHandlerTest {
private HikeHandler underTest = new HikeHandler()

    @Test
    @Disabled("Project 'buildSrc' not found in root project 'TTT'")
    void timeFilter() {
        assert underTest.allHikesQueryUrl(2022) == "https://tturak.hu/api/hikeoccasion/list?from=1640995200&to=1672531199"
    }
}
