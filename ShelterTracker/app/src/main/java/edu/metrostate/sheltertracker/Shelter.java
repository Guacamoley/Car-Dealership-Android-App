package edu.metrostate.sheltertracker;

public class Shelter {
    private String shelterId = "";
    private String shelterName = "";

    public Shelter(String shelterId, String shelterName) {
        this.shelterId = shelterId;
        this.shelterName = shelterName;
    }


    public String getShelterId() {
        return shelterId;
    }

    public void setShelterId(String shelterId) {
        this.shelterId = shelterId;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }
}
