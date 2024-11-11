package org.example.model;

public class Route {
    private Long id;
    private Double totalDistance;
    private StartLocation startLocation;
    private EndLocation endLocation;

    public Route() {
    }

    public Route(Long id, Double distance, StartLocation startLocation, EndLocation endLocation) {
        this.id = id;
        this.totalDistance = distance;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public Route(Double distance, StartLocation startLocation, EndLocation endLocation) {
        this.totalDistance = distance;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = totalDistance;
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
                ", distance=" + totalDistance +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                '}';
    }
}
