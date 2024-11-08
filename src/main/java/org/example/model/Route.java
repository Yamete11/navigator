package org.example.model;

public class Route {
    private Long id;
    private Double distance;
    private StartLocation startLocation;
    private EndLocation endLocation;

    public Route() {
    }

    public Route(Long id, Double distance, StartLocation startLocation, EndLocation endLocation) {
        this.id = id;
        this.distance = distance;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public Route(Double distance, StartLocation startLocation, EndLocation endLocation) {
        this.distance = distance;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public StartLocation getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(StartLocation startLocation) {
        this.startLocation = startLocation;
    }

    public EndLocation getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", distance=" + distance +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                '}';
    }
}
