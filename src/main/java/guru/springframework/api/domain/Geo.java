package guru.springframework.api.domain;

import java.io.Serializable;

public class Geo implements Serializable {
    Float lat;
    Float lon;

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }
}
