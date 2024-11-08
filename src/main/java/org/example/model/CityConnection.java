package org.example.model;

public class CityConnection {
    private Long id;
    private Double distance;

    private City firstCity;
    private City secondCity;

    public CityConnection() {
    }

    public CityConnection(Long id, Double distance, City firstCity, City secondCity) {
        this.id = id;
        this.distance = distance;
        this.firstCity = firstCity;
        this.secondCity = secondCity;
    }

    public CityConnection(Double distance, City firstCity, City secondCity) {
        this.distance = distance;
        this.firstCity = firstCity;
        this.secondCity = secondCity;
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

    public City getFirstCity() {
        return firstCity;
    }

    public void setFirstCity(City firstCity) {
        this.firstCity = firstCity;
    }

    public City getSecondCity() {
        return secondCity;
    }

    public void setSecondCity(City secondCity) {
        this.secondCity = secondCity;
    }

    @Override
    public String toString() {
        return "CityConnection{" +
                "id=" + id +
                ", distance=" + distance +
                ", firstCity=" + firstCity +
                ", secondCity=" + secondCity +
                '}';
    }
}
