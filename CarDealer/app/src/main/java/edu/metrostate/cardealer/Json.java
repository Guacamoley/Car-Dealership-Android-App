package edu.metrostate.cardealer;

import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Json {
    private JsonElement fileElement;

    public List<Car> readFile(File fileInput) {
        List<Car> cars = new ArrayList<>();
        {
            try {
                fileElement = JsonParser.parseReader(new FileReader(fileInput));
                JsonObject fileObject = fileElement.getAsJsonObject();

                // Process all cars
                JsonArray jsonArrayOfCars = fileObject.get("car_inventory").getAsJsonArray();

                for (JsonElement carElement : jsonArrayOfCars) {
                    // Get the DealershipSystem.Json Object:
                    JsonObject carJsonObject = carElement.getAsJsonObject();

                    // Extract data
                    String dealershipID = null;
                    String vehicleType = null;
                    String vehicleManu = null;
                    String vehicleModel = null;
                    String vehicleID = null;
                    Double price = null;
                    long acquisitionDate = carJsonObject.get("acquisition_date").getAsLong();
                    //New Data
                    String dealerName = null;
                    String currency = null;
                    Boolean loaned = null;

                    /*
                     * -Input validation if the object attributes are empty inside the object to
                     * prevent an exception from occurring -I would like to make this more efficient
                     * by putting this through a loop for every object, and it's associated
                     * attributes if it's null or not
                     */

                    if (carJsonObject.has("dealership_id")) {
                        dealershipID = carJsonObject.get("dealership_id").getAsString();
                    }
                    if (carJsonObject.has("vehicle_type")) {
                        vehicleType = carJsonObject.get("vehicle_type").getAsString();
                    }
                    if (carJsonObject.has("vehicle_model")) {
                        vehicleModel = carJsonObject.get("vehicle_model").getAsString();
                    }
                    if (carJsonObject.has("vehicle_manufacturer")) {
                        vehicleManu = carJsonObject.get("vehicle_manufacturer").getAsString();
                    }
                    if (carJsonObject.has("vehicle_id")) {
                        vehicleID = carJsonObject.get("vehicle_id").getAsString();
                    }

                    /*
                     * -I'm not sure if you can leave primitives empty in the JSON file because it's
                     * part of the syntax? Therefore it will just go straight to the exception
                     * instead? -Needs further investigation -Exporting you can put in null but
                     * importing you can't unless if you directly tinker the file and place the data
                     * type as null
                     */
                    if (carJsonObject.has("price")) {
                        price = carJsonObject.get("price").getAsDouble();
                    }
                    if (carJsonObject.has("acquisition_date")) {
                        acquisitionDate = carJsonObject.get("acquisition_date").getAsLong();
                    }
                    if (carJsonObject.has("dealerName")){
                        dealerName = carJsonObject.get("dealerName").getAsString();
                    }
                    if(carJsonObject.has("currency")){
                        currency = carJsonObject.get("currency").getAsString();
                    }
                    if(carJsonObject.has("loaned")){
                        loaned = carJsonObject.get("loaned").getAsBoolean();
                    }

                    Car car = new Car(dealershipID, vehicleType, vehicleManu, vehicleModel, vehicleID, price, acquisitionDate, dealerName, currency, loaned);
                    // Array list for storing cars and their attributes
                    cars.add(car);

                }
                // This is temporary and only for testing purposes
                // System.out.println(cars);
                //System.out.println(fileInput);

                // Error for input file if DNE
            } catch (FileNotFoundException e) {
                System.err.println("Error input file not found");
                e.printStackTrace();
            }
            // Error if attribute data types are incorrect
            catch (Exception e) {
                System.err.println("Error processing the input file given in JSON");
                e.printStackTrace();
            }

        }
        return cars;
    }

    /**
     * exports the given list of car objects to a json object in accordance with the
     * CarListDTO. the new file is placed in the resources folder.
     *
     * @see https://stackoverflow.com/questions/43167016/how-to-keep-list-name-using-gson
     * @param cars The list of cars to be exported.
     * @param filePath The file path that the export file will go to.
     */
    public void exportFile(List<Car> cars, String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {

            // create the data transfer object so that the list will be properly named
            CarListDTO output = new CarListDTO();
            output.setCarInventory(cars);

            // write the file
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(output, fw);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
