package org.example.model;

public class EndLocation {
    private Long id;
    private City city;

    public EndLocation() {
    }

    public EndLocation(Long id, City city) {
        this.id = id;
        this.city = city;
    }

    public EndLocation(City city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "EndLocation{" +
                "id=" + id +
                ", city=" + city +
                '}';
    }
}
