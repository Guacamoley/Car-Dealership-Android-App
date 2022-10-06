package edu.metrostate.cardealer;

public class Vehicle {

    private final String id;
    private final String model;

    Vehicle(String id, String model){
        this.id = id;
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

}
