package org.example.model;

public class RouteCity {
    private Long id;
    private City city;
    private Route route;
    private Long index;

    public RouteCity() {
    }

    public RouteCity(Long id, City city, Route route, Long index) {
        this.id = id;
        this.city = city;
        this.route = route;
        this.index = index;
    }

    public RouteCity(City city, Route route, Long index) {
        this.city = city;
        this.route = route;
        this.index = index;
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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "RouteCity{" +
                "id=" + id +
                ", city=" + city +
                ", route=" + route +
                ", index=" + index +
                '}';
    }
}
