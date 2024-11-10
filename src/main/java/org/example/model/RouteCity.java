package org.example.model;

public class RouteCity {
    private Long id;
    private City city;
    private Route route;
    private Long orderIndex;

    public RouteCity() {
    }

    public RouteCity(Long id, City city, Route route, Long index) {
        this.id = id;
        this.city = city;
        this.route = route;
        this.orderIndex = index;
    }

    public RouteCity(City city, Route route, Long index) {
        this.city = city;
        this.route = route;
        this.orderIndex = index;
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

    public Long getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Long orderIndex) {
        this.orderIndex = orderIndex;
    }

    @Override
    public String toString() {
        return "RouteCity{" +
                "id=" + id +
                ", city=" + city +
                ", route=" + route +
                ", index=" + orderIndex +
                '}';
    }
}
