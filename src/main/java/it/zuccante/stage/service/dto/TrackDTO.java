package it.zuccante.stage.service.dto;

import java.io.Serializable;

public class TrackDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String hospitalName;
    private double duration;
    private double distance;
    private String congestion;

    public TrackDTO(String hospitalName, double duration, double distance, String congestion) {
        this.hospitalName = hospitalName;
        this.duration = duration;
        this.distance = distance;
        this.congestion = congestion;
    }

    public TrackDTO() {
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getCongestion() {
        return congestion;
    }

    public void setCongestion(String congestion) {
        this.congestion = congestion;
    }
}
