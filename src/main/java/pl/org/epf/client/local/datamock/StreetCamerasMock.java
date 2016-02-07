package pl.org.epf.client.local.datamock;

import com.google.common.collect.ImmutableMap;
import pl.org.epf.client.local.model.TristarObject;
import pl.org.epf.client.local.model.TristarObjectType;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: to replace with data from the API when ready
 */
public class StreetCamerasMock {

    private ImmutableMap<Integer, TristarObject> cameras;

    public StreetCamerasMock () {
        mockData();
    }

    private void mockData() {
        cameras = ImmutableMap.<Integer, TristarObject>builder().
                put(1, createCameraObject(1, 54.382577, 18.598193, "GRUNWALDZKA-SLOWACKIEGO", 1)).
                put(2, createCameraObject(2, 54.374408, 18.567442, "SŁOWACKIEGO - POTOKOWA", 2)). // blad jest w Tristar - SŁOWACKIEGO - ZŁOTA KARCZMA
                put(3, createCameraObject(3, 54.376500, 18.522235, "SŁOWACKIEGO - ZŁOTA KARCZMA", 1)).
                put(4, createCameraObject(4, 54.376600, 18.508348, "SŁOWACKIEGO - BUDOWLANYCH", 1)).
                put(5, createCameraObject(5, 54.378141, 18.607059, "GRUNWALDZKA-JASKOWA DOLINA", 1)).
                put(6, createCameraObject(6, 54.375956, 18.614617, "GRUNWALDZKA-MISZEWSKIEGO", 1)).
                put(7, createCameraObject(7, 54.372563, 18.624282, "ZWYCIESTWA-HALLERA", 1)).
                put(8, createCameraObject(8, 54.376117, 18.623689, "HALLERA - WYSPIAŃSKIEGO", 1)).
                put(9, createCameraObject(9, 54.387254, 18.591316, "GRUNWALDZKA-WOJSKA POLSKIEGO", 1)).
                put(10, createCameraObject(10, 54.401169, 18.574053, "GRUNWALDZKA-KOLOBRZESKA", 1)).
                put(11, createCameraObject(11, 54.410549, 18.567015, "GRUNWALDZKA-PIASTOWSKA", 1)).
                put(12, createCameraObject(12, 54.414364, 18.566048, "GRUNWALDZKA-POMORSKA", 1)).
                put(13, createCameraObject(13, 54.423591, 18.563546, "GRUNWALDZKA-CZYZEWSKIEGO", 1)).
                put(14, createCameraObject(14, 54.430906, 18.561707, "AL. NIEPODLEGLOSCI-ARMII KRAJOWEJ", 1)). // blad "," zamiast "."
                put(15, createCameraObject(15, 54.436211, 18.568169, "3 MAJA-WLADYSLAWA IV", 1)).
                put(16, createCameraObject(16, 54.441614, 18.560037, "AL. NIEPODLEGLOSCI- PODJAZD-SIKORSKIEGO", 1)). // blad "," zamiast "."
                put(17, createCameraObject(17, 54.442650, 18.570529, "GRUNWALDZKA-CHOPINA", 1)).
                put(18, createCameraObject(18, 54.4500098, 18.5542968, "AL. NIEPODLEGLOSCI-MALCZEWSKIEGO", 1)). // blad "," zamiast "."
                put(19, createCameraObject(19, 54.387428, 18.610323, "LEGIONÓW - KOŚCIUSZKI", 1)).
                put(20, createCameraObject(20, 54.387903, 18.622167, "HALLERA - KOŚCIUSZKI", 1)).
                put(21, createCameraObject(21, 54.402125, 18.622539, "HALLERA - UCZNIOWSKA", 1)).
                put(22, createCameraObject(22, 54.388052, 18.650034, "MARYNARKI POLSKIEJ - REJA", 1)).build();
    }

    private TristarObject createCameraObject(int id, double latitude, double longitude, String title, int resourcesCount) {
        TristarObject    camera = new TristarObject();
        camera.setId(id);
        camera.setLatitude(latitude);
        camera.setLongitude(longitude);
        camera.setName(title);
        camera.setResourcesCount(resourcesCount);
        camera.setType(TristarObjectType.CAMERA);
        return camera;
    }

    public ImmutableMap<Integer, TristarObject> getCameras() {
        return cameras;
    }

    public List<TristarObject> getCamerasList() {
        return new ArrayList<>(cameras.values());
    }

}
