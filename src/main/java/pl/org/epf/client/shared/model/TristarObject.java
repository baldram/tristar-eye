package pl.org.epf.client.shared.model;

public class TristarObject {
    private Integer id;
    private TristarObjectType type;
    private String wkt; // TODO: to use here Map<WktDataType, String> or List<Pair<WktDataType, String>>
    private String name;

    public TristarObject() {
    }

    public TristarObject(Integer id, String wkt, String name, TristarObjectType type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.wkt = wkt;
    }

    public TristarObjectType getType() {
        return type;
    }

    public void setType(TristarObjectType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWkt() {
        return wkt;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
    }
}
