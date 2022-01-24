
class HikeHandlerTest {
private HikeHandler underTest = new HikeHandler()

    //@Test
    void timeFilter() {
        assert underTest.allHikesQueryUrl(2022) == "https://tturak.hu/api/hikeoccasion/list?from=1640995200&to=1672531199"
    }
}
