package pl.org.epf.client.shared.fixture;

import com.google.common.collect.ImmutableMap;
import pl.org.epf.client.shared.model.TristarObject;
import pl.org.epf.client.shared.model.TristarObjectType;

import java.util.ArrayList;
import java.util.List;

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
                put(162, createCameraObject(162, 54.456998, 18.555485, "Niepodleglosci-Haffnera")).
                put(163, createCameraObject(163, 54.4500098, 18.5542968, "Niepodległości-Malczewskiego")). // blad "," zamiast "."
                put(164, createCameraObject(164, 54.441614, 18.560037, "Niepodległości-Podjazd-Sikorskiego")). // blad "," zamiast "."
                put(165, createCameraObject(165, 54.430906, 18.561707, "Niepodległości-Armii Krajowej ")). // blad "," zamiast "."
                put(166, createCameraObject(166, 54.436211, 18.568169, "3 Maja-Władysława IV")).
                put(167, createCameraObject(167, 54.442650, 18.570529, "Grunwaldzka-Chopina")).
                put(168, createCameraObject(168, 54.423591, 18.563546, "Grunwaldzka-Czyżewskiego")).
                put(169, createCameraObject(169, 54.414364, 18.566048, "Grunwaldzka-Pomorska")).
                put(170, createCameraObject(170, 54.410621, 18.567117, "Grunwaldzka-Piastowska")).
                put(171, createCameraObject(171, 54.410587, 18.566966, "Grunwaldzka-Piastowska")).
                put(172, createCameraObject(172, 54.401169, 18.574053, "Grunwaldzka-Kołobrzeska")).
                put(173, createCameraObject(173, 54.387254, 18.591316, "Grunwaldzka-Wojska Polskiego")).
                put(174, createCameraObject(174, 54.382577, 18.598193, "Grunwaldzka-Słowackiego")).
                put(175, createCameraObject(175, 54.378141, 18.607059, "Grunwaldzka-Jaśkowa Dolina")).
                put(176, createCameraObject(176, 54.375956, 18.614617, "Grunwaldzka-Miszewskiego")).
                put(177, createCameraObject(177, 54.372467, 18.624267, "Zwyciestwa-Hallera")).
                put(178, createCameraObject(178, 54.372590, 18.624102, "Zwyciestwa-Hallera")).
                put(182, createCameraObject(182, 54.541900, 18.460662, "Morska-Kartuska")).
                put(183, createCameraObject(183, 54.538591, 18.471116, "Morska-Wiejska")).
                put(184, createCameraObject(184, 54.533205, 18.488605, "Morska-Kwiatkowskiego")).
                put(195, createCameraObject(195, 54.519628, 18.538896, "10 Lutego-Władysława IV")).
                put(196, createCameraObject(196, 54.519415, 18.542441, "10 Lutego-Świętojańska")).
                put(205, createCameraObject(205, 54.374408, 18.567442, "Słowackiego-Potokowa")). // błąd jest w Tristar: SŁOWACKIEGO - ZŁOTA KARCZMA
                put(206, createCameraObject(206, 54.376438, 18.522224, "Słowackiego-Złota Karczma")).
                put(207, createCameraObject(207, 54.376618, 18.522527, "Słowackiego-Złota Karczma (Obwodnica)")).
                put(208, createCameraObject(208, 54.376600, 18.508348, "Słowackiego-Budowlanych")).
                put(209, createCameraObject(209, 54.376117, 18.623689, "Hallera-Wyspiańskiego")).
                put(204, createCameraObject(204, 54.387428, 18.610323, "Legionów-Kościuszki")).
                put(210, createCameraObject(210, 54.387903, 18.622167, "Hallera-Kościuszki")).
                put(211, createCameraObject(211, 54.402125, 18.622539, "Hallera-Uczniowska")).
                put(213, createCameraObject(213, 54.388052, 18.650034, "Marynarki Polskiej-Reja")).
                put(214, createCameraObject(214, 54.388054, 18.650046, "Marynarki Polskiej-Uczniowska")).
                put(287, createCameraObject(287, 54.379416, 18.588981, "Żołnierzy Wyklętych-Chrzanowskiego")).
                put(291, createCameraObject(291, 54.393782, 18.581968, "Grunwaldzka-Abrahama")).
                build();
    }

    private TristarObject createCameraObject(int id, double latitude, double longitude, String title) {
        TristarObject camera = new TristarObject();
        camera.setId(id);
        camera.setLatitude(latitude);
        camera.setLongitude(longitude);
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

    public List<TristarObject> fetch(TristarObjectType type, List<Integer> ids) {
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
