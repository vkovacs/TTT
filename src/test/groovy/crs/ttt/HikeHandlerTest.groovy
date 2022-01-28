package crs.ttt

import crs.ttt.service.HikeService
import org.junit.jupiter.api.Test

class HikeHandlerTest {
    private HikeService underTest = new HikeService()

    @Test
    void isTTTKupa() {
        assert underTest.isTTTCoup("A túra a Budapest Kupa, a Cimbora Kupa 2022. a 20-22 MINI Kupa és a Félmillió Lépés része.")
        assert underTest.isTTTCoup("A túra a budapest kupa, a Cimbora Kupa 2022. a 20-22 MINI Kupa és a Félmillió Lépés része.")
        assert underTest.isTTTCoup("""<h2>A túra létszámkorlátos!&nbsp;100 fő.</h2>
            <h2>Előnevezés a következő <a href="https://docs.google.com/forms/d/18MpigmKwOzQRZMlEiBcs3VW7_rxa56NEyW8Y6qasR8o/edit"><strong>LINKEN!</strong></a></h2>
            <h2>A túra nevezési díja magában foglalja a díjazás (kitűző, emléklap), térképes itiner, szervezői költségek, csoki és a Pilisi Parkerdőnek fizetett területhasználati díj összegét.</h2>
            <h2><strong>Írószer szükséges!</strong></h2>
            <h2>A túra a <strong>TTT Kupa</strong> része. (Kupafüzet majd csak a következő hétvégére készül el, így a túrát utólag, egy másik Területi Honvédelmi Klub túrán tudod leigazolni, vagy email-es leadásnál szkenneld/fotózd majd a füzet mellé a hiányzó okleveleket is.)</h2>
            <p>&nbsp;</p>
            <h2>&nbsp;</h2>
            <p>&nbsp;</p>""")
    }
}
