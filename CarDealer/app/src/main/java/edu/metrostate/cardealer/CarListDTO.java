package edu.metrostate.cardealer;

import java.util.List;

/**
 * This class is a data transfer object which converts between the json input
 * file format and a list of car objects. The name of the list is car_inventory.
 * It is used by the Json handler class.
 *
 * @author Paul Schmitz
 *
 */
public class CarListDTO {

    // the list of cars
    private List<Car> car_inventory;

    /**
     * gets the car inventory
     *
     * @return carInventory the list of cars
     */
    public List<Car> getCarInventory() {
        return car_inventory;
    }

    /**
     * sets the car inventory
     *
     * @param carInventory the carInventory to set
     */
    public void setCarInventory(List<Car> carInventory) {
        this.car_inventory = carInventory;
    }
}
