package edu.metrostate.sheltertracker;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class ShelterTrackerApplication extends Application {

    private final List<Shelter> shelterList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        for(int i = 0; i < 20; i++) {
            shelterList.add(new Shelter(Integer.toString(i), "Shelter Number " + i));
        }
    }

    public List<Shelter> getShelterList() {
        return shelterList;
    }



}
