import org.junit.jupiter.api.Test

class GetAllHikesIntoFileTest {

    private GetAllHikesIntoFile underTest = new GetAllHikesIntoFile()
    @Test
    void timeFilter() {
        assert underTest.allHikesQueryUrl(2022) == "https://tturak.hu/api/hikeoccasion/list?from=1640995200&to=1672531199"
    }
}
