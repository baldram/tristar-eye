package pl.org.epf.client.shared.fixture;

import com.google.common.collect.ImmutableMap;
import pl.org.epf.client.shared.model.TristarObject;
import pl.org.epf.client.shared.model.TristarObjectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * TODO: to remove when external API is ready
 */
public class TristarDataSet {

    private ImmutableMap<Integer, TristarObject> cameras;

    public TristarDataSet() {
        mockData();
    }

    private void mockData() {
        cameras = ImmutableMap.<Integer, TristarObject>builder().
                put(162, createCameraObject(162, "[POINT (2065593.15287473 7257176.57009129)]", "Niepodleglosci-Haffnera")).
                put(163, createCameraObject(163, "[POINT (2065518.79145488 7255837.74329779)]", "Niepodległości-Malczewskiego")). // blad "," zamiast "."
                put(164, createCameraObject(164, "[POINT (2066089.41516468 7254230.95519973)]", "Niepodległości-Podjazd-Sikorskiego")). // blad "," zamiast "."
                put(165, createCameraObject(165, "[POINT (2066286.89594135 7252178.77333926)]", "Niepodległości-Armii Krajowej ")). // blad "," zamiast "."
                put(166, createCameraObject(166, "[POINT (2067012.92166031 7253206.71112025)]", "3 Maja-Władysława IV")).
                put(167, createCameraObject(167, "[POINT (2067263.50183408 7254439.99323139)]", "Grunwaldzka-Chopina")).
                put(168, createCameraObject(168, "[POINT (2066479.92393839 7250782.44070892)]", "Grunwaldzka-Czyżewskiego")).
                put(169, createCameraObject(169, "[POINT (2066760.89433315 7249021.54463364)]", "Grunwaldzka-Pomorska")).
                put(170, createCameraObject(170, "[POINT (2066872.65910191 7248286.23309656)]", "Grunwaldzka-Piastowska")).
                put(171, createCameraObject(171, "[POINT (2066873.21569936 7248286.42437572)]", "Grunwaldzka-Piastowska")).
                put(172, createCameraObject(172, "[POINT (2067635.86553079 7246477.70593128)]", "Grunwaldzka-Kołobrzeska")).
                put(173, createCameraObject(173, "[POINT (2069585.84905101 7243831.84148659)]", "Grunwaldzka-Wojska Polskiego")).
                put(174, createCameraObject(174, "[POINT (2070340.4838791 7242943.52215105)]", "Grunwaldzka-Słowackiego")).
                put(175, createCameraObject(175, "[POINT (2071328.11040142 7242101.93857644)]", "Grunwaldzka-Jaśkowa Dolina")).
                put(176, createCameraObject(176, "[POINT (2072170.35366876 7241671.52917514)]", "Grunwaldzka-Miszewskiego")).
                put(177, createCameraObject(177, "[POINT (2073254.71682858 7240986.59237962)]", "Zwyciestwa-Hallera")).
                put(178, createCameraObject(178, "[POINT (2073214.86445087 7241025.00382616)]", "Zwyciestwa-Hallera")).
                put(179, createCameraObject(179, "[POINT (2051982.34137442 7274757.2416192)]", "Morska - Chylońska II")).
                put(180, createCameraObject(180, "[POINT (2052842.95235774 7274316.39914206)]", "Morska - Owsiana")).
                put(181, createCameraObject(181, "[POINT (2054441.50024553 7273689.62797282)]", "Morska - Obwodowa")).
                put(182, createCameraObject(182, "[POINT (2055031.82750521 7273459.73489649)]", "Morska-Kartuska")).
                put(183, createCameraObject(183, "[POINT (2056209.03112035 7272814.42002985)]", "Morska-Wiejska")).
                put(184, createCameraObject(184, "[POINT (2058146.10157964 7271782.56403247)]", "Morska-Kwiatkowskiego")).
                put(185, createCameraObject(185, "[POINT (2059005.26540958 7271371.04912867)]", "Morska - Kalksztajnów")).
                put(186, createCameraObject(186, "[POINT (2059777.37739773 7271019.02440237)]", "Morska - Mireckiego")).
                put(187, createCameraObject(187, "[POINT (2062430.67746078 7269466.85121216)]", "Morska - Warszawska")).
                put(188, createCameraObject(188, "[POINT (2063140.33921459 7267739.91176446)]", "Śląska - Warszawska - Witomińska")).
                put(189, createCameraObject(189, "[POINT (2063201.34229554 7266984.62728085)]", "Śląska - Kielecka")).
                put(190, createCameraObject(190, "[POINT (2060490.26741676 7265081.8786501)]", "Chwarznieńska - Małokacka - Wielkokacka")).
                put(191, createCameraObject(191, "[POINT (2058771.38315943 7272742.84957861)]", "Estakada Kwiatkowskiego - Hutnicza")).
                put(192, createCameraObject(192, "[POINT (2059165.8994348 7273143.11524799)]", "Wiśniewskiego - Łącznica Estakady - Energetyków")).
                put(193, createCameraObject(193, "[POINT (2061531.88389212 7270606.39866847)]", "Wiśniewskiego - Polska - Al. Solidarności")).
                put(194, createCameraObject(194, "[POINT (2063686.91791438 7269900.51569571)]", "Władysława IV - Jana z Kolna")).
                put(195, createCameraObject(195, "[POINT (2063751.92849701 7269175.5173495)]", "10 Lutego-Władysława IV")).
                put(196, createCameraObject(196, "[POINT (2064134.42226737 7269143.29675831)]", "10 Lutego-Świętojańska")).
                put(197, createCameraObject(197, "[POINT (2063746.36252247 7268832.41322035)]", "Władysława IV - Armii Krajowej")).
                put(198, createCameraObject(198, "[POINT (2075215.6096589 7238956.02206717)]", "Węzeł Brama Oliwska")).
                put(199, createCameraObject(199, "[POINT (2075674.13464148 7238186.50308673)]", "Węzeł Piastowski")).
                put(200, createCameraObject(200, "[POINT (2075696.50985913 7237084.51060152)]", "Węzeł Hucisko")).
                put(201, createCameraObject(201, "[POINT (2075576.17348958 7235816.71342565)]", "Węzeł Toruński")).
                put(202, createCameraObject(202, "[POINT (2074581.31120036 7234003.61127517)]", "Trakt Św. Wojciecha - Zremb")).
                put(203, createCameraObject(203, "[POINT (2074087.94321716 7231724.22235931)]", "Trakt Św. Wojciecha - Gościnna")).
                put(204, createCameraObject(204, "[POINT (2071704.14764132 7243865.67875002)]", "Legionów - Kościuszki")).
                put(205, createCameraObject(205, "[POINT (2066923.19815073 7241383.51972805)]", "Słowackiego-Potokowa")). // błąd jest w Tristar: SŁOWACKIEGO - ZŁOTA KARCZMA
                put(206, createCameraObject(206, "[POINT (2061889.33077705 7241758.48831886)]", "Słowackiego-Złota Karczma")).
                put(207, createCameraObject(207, "[POINT (2061928.6265573 7241793.08112529)]", "Słowackiego-Złota Karczma (Obwodnica)")).
                put(208, createCameraObject(208, "[POINT (2060348.89166346 7241795.37457641)]", "Słowackiego-Budowlanych")).
                put(209, createCameraObject(209, "[POINT (2073176.12526808 7241709.17949928)]", "Hallera-Wyspiańskiego")).
                put(210, createCameraObject(210, "[POINT (2073019.16478606 7243954.57395095)]", "Hallera-Kościuszki")).
                put(211, createCameraObject(211, "[POINT (2073060.79827561 7246710.05996536)]", "Hallera-Uczniowska")).
                put(212, createCameraObject(212, "[POINT (2074384.83229911 7240777.53039013)]", "Jana z Kolna")).
                put(213, createCameraObject(213, "[POINT (2074309.24636486 7242300.71465318)]", "Marynarki Polskiej-Reja")).
                put(214, createCameraObject(214, "[POINT (2076114.06926909 7243973.69133006)]", "Marynarki Polskiej-Uczniowska")).
                put(215, createCameraObject(215, "[POINT (2076754.8242581 7235892.91298561)]", "Podwale Przedmiejskie - Chmielna")).
                put(216, createCameraObject(216, "[POINT (2078958.50489784 7236594.02184581)]", "Elbląska - Głęboka")).
                put(217, createCameraObject(217, "[POINT (2082012.22116928 7236941.63921768)]", "Elbląska - Miałki Szlak")).
                put(218, createCameraObject(218, "[POINT (2082722.21688156 7237073.24124015)]", "Elbląska - Sztutowska")).
                put(219, createCameraObject(219, "[POINT (2075237.98487655 7236369.41573128)]", "3 maja - Armii Krajowej")).
                put(220, createCameraObject(220, "[POINT (2072442.86378222 7235537.13039892)]", "Armii Krajowej - Sikorskiego")).
                put(221, createCameraObject(221, "[POINT (2072442.86378222 7235537.13039892)]", "Armii Krajowej - Sikorskiego 2")). // ta sama pozycja - jak punkt sie wyswietla?
                put(222, createCameraObject(222, "[POINT (2070582.49245208 7234477.32614731)]", "Armii Krajowej - Łostowicka - Warszawska")).
                put(273, createCameraObject(273, "[POINT (2063495.6710292 7267280.86499017)]", "Władysława IV - Piłsudskiego")).
                put(274, createCameraObject(274, "[POINT (2063475.52220137 7266655.61522735)]", "Al. Zwycięstwa - Nowa Kopernika")).
                put(275, createCameraObject(275, "[POINT (2063655.303179 7264885.20776424)]", "Al. Zwycięstwa - Redłowska")).
                put(276, createCameraObject(276, "[POINT (2063919.79828912 7263976.86701918)]", "Al. Zwycięstwa - Stryjska")).
                put(277, createCameraObject(277, "[POINT (2064483.63150999 7262605.25931133)]", "Al. Zwycięstwa - Wielkopolska")).
                put(278, createCameraObject(278, "[POINT (2065248.9530092 7261460.9353883)]", "Al. Zwycięstwa - Orłowska")).
                put(279, createCameraObject(279, "[POINT (2064027.11027825 7262418.42341175)]", "Wielkopolska - Lotników - Inżynierska - Kościelna")).
                put(280, createCameraObject(280, "[POINT (2062950.4281633 7261884.38474386)]", "Wielkopolska - Łowicka - Kowieńska")).
                put(281, createCameraObject(281, "[POINT (2060965.37900347 7260225.20723275)]", "Wielkopolska - Racławicka")).
                put(282, createCameraObject(282, "[POINT (2060421.36065196 7259509.14883125)]", "Wielkopolska - Sopocka - Nowodworcowa")).
                put(283, createCameraObject(283, "[POINT (2058259.31350178 7259889.19950413)]", "Chwaszczyńska - Brzechwy - Gryfa Pomorskiego")).
                put(284, createCameraObject(284, "[POINT (2057312.20727411 7260124.63328337)]", "Chwaszczyńska - Nowowiczlińska")).
                put(286, createCameraObject(286, "[POINT (209280.642691354 319620.626729217)]", "Nowa testowa po zmianie")). // WTF????
                put(287, createCameraObject(287, "[POINT (2069313.11629857 7242340.66160707)]", "Żołnierzy Wykletych - Chrzanowskiego")).
                put(288, createCameraObject(288, "[POINT (2065392.7777913 7248221.00717615)]", "Spacerowa - Opacka")).
                put(289, createCameraObject(289, "[POINT (2075214.05118603 7237081.45450193)]", "3 Maja - Hucisko - Nowe Ogrody")).
                put(290, createCameraObject(290, "[POINT (2073922.52245384 7240303.81729576)]", "Zwycięstwa - Trauguta")).
                put(291, createCameraObject(291, "[POINT (2068528.09124949 7245055.04613159)]", "Grunwaldzka-Abrahama")).
                put(292, createCameraObject(292, "[POINT (2060618.39615067 7236732.30295635)]", "Kartuska - Kalinowa")).

                build();
    }

    private TristarObject createCameraObject(int id, String wkt, String title) {
        TristarObject camera = new TristarObject();
        camera.setId(id);
        camera.setWkt(wkt);
        camera.setName(title);
        camera.setType(TristarObjectType.CAMERA);
        return camera;
    }

    public ImmutableMap<Integer, TristarObject> fetchAll(TristarObjectType type) {
        if (TristarObjectType.CAMERA == type) {
            return cameras;
        }
        return ImmutableMap.of();
    }

    public TristarObject fetch(TristarObjectType type, Integer id) {
        if (TristarObjectType.CAMERA == type) {
            return cameras.get(id);
        }
        return null;
    }

    public List<TristarObject> fetch(TristarObjectType type, Set<Integer> ids) {
        List<TristarObject> choosenCameras = new ArrayList<>();
        if (TristarObjectType.CAMERA == type) {
            for (Integer id : ids) {
                if (ids.contains(id)) {
                    choosenCameras.add(cameras.get(id));
                }
            }
        }
        return choosenCameras;
    }
}
