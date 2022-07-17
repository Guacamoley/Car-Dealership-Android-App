package edu.metrostate.sheltertracker;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
        writeFile();
    }

    public List<Shelter> getShelterList() {
        return shelterList;
    }



    public void writeFile() {

        // this will put files in the /sdcard/Android/data/edu.metrostate.sheltertracker/files directory
        File externalDir = getExternalFilesDir(null);

        File outputFile = new File(externalDir, "myfile.txt");

        try {
            Files.createFile(outputFile.toPath());
            Files.write(outputFile.toPath(), "My data".getBytes());

        } catch (IOException ex) {
            Log.e("FileCreation", "Error creating file", ex);
        }

    }



}
