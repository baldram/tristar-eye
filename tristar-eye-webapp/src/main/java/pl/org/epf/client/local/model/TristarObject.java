package pl.org.epf.client.local.model;

public class TristarObject {
    private Integer id;
    private TristarObjectType type;
    private double latitude;
    private double longitude;
    private String name;
    private int resourcesCount;

    public TristarObject() {
    }

    public TristarObject(Integer id, String name, TristarObjectType type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public int getResourcesCount() {
        return resourcesCount;
    }

    public void setResourcesCount(int resourcesCount) {
        this.resourcesCount = resourcesCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
