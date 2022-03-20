package crs.ttt.service

import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat

class HikeServiceTest {
    private HikeService hikeService = new HikeService()

    @Test
    void teryOdonIsTTTHike() {
        // given
        var description = "<p><b>Minden távhoz: </b><strong><span style=\"color:#ff0000;\">A József napi 30 éves Téry jubileumon NEVEZÉSI DÍJ NINCS.</span></strong><br />\nTérkép: a Budai-hegység, a Pilis és a Visegrádi-hegység turistatérképe.<br />\nAz 50km-es és a 20km-es gyalogos távok a <b>TTT Kupa</b> túrasorozat részei.<br />\n<i>(Táv, szint, szintidő adatok itiner szerint.)</i></p>\n"

        //when
        boolean isTTTHike = hikeService.isTTTKupa(description)

        //then
        assertThat(isTTTHike, CoreMatchers.is(true))
    }
}
