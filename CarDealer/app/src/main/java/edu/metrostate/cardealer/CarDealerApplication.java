package edu.metrostate.cardealer;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CarDealerApplication extends Application {
    public static final Inventory INVENTORY = new Inventory();
    //public static Dealership selectedDealer;
    //public static Car selectedCar;
    public static final String SAVE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/dealers.json";

    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: this is sample data. remove this eventually.
        ArrayList<Car> sampleData = new ArrayList<Car>();
        sampleData.add(new Car("12513", "suv", "Ford", "Explorer", "48934j", 20123d, 1515354694451l, "", "", false));
        sampleData.add(new Car("12513", "sedan", "Tesla", "Model 3", "83883", 50444d, 1515354694451l, "", "", false));
        sampleData.add(new Car("12513", "pickup", "Chevy", "Silverado", "89343883", 70444d, 1515354694451l, "", "", false));
        sampleData.add(new Car("77338", "sports car", "Toyota", "Supra", "229393", 49889d, 1515354694451l, "", "", false));
        sampleData.add(new Car("485", "suv", "Land Rover", "Range Rover", "848432", 17000d, 1515354694451l, "Wacky Bob's Automall", "pounds", false));
        sampleData.add(new Car("485", "pickup", "Toyota", "Tundra", "52523", 22600d, 1515354694451l, "Wacky Bob's Automall", "dollars", false));
        sampleData.add(new Car("485", "sedan", "Genesis", "G70", "151e5dde", 36600d, 1515354694451l, "Wacky Bob's Automall", "dollars", false));
        sampleData.add(new Car("485", "sports car", "Mazda", "Miata", "ern222", 22330d, 1515354694451l, "Wacky Bob's Automall", "dollars", false));
        for (Car c : sampleData){
            INVENTORY.addIncomingVehicle(c);
        }

        // TODO load saved data
        File saveFile = new File(SAVE_PATH);
        if (saveFile.isFile()) INVENTORY.importFile(saveFile, "json");
    }

    public List<Car> getVehicleList(String dealerId) {
        return INVENTORY.getDealerCars(dealerId);
    }
    public List<Dealership> getDealerList() {
        return INVENTORY.getAllDealerships();
    }

    public void writeFile() {

        //TODO: Remove this code
        // Gets the output path which is /sdcard/Android/data/edu.metrostate.cardealer/files directory
        File externalDir = getExternalFilesDir(null);

        // Establishes the output file as "myfile.txt"
        File outputFile = new File(externalDir, "myfile.txt");

        try {
            Files.createFile(outputFile.toPath());

            // Saves the string "My data" to the file
            Files.write(outputFile.toPath(), "My data".getBytes());

        } catch (IOException ex) {
            Log.e("FileCreation", "Error creating file", ex);
        }

    }



}
