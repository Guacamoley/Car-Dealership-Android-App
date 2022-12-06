package edu.metrostate.cardealer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a car dealership which controls a list of car objects.
 * Each dealership has a unique id which cannot be changed after its creation.
 * Each dealership has a boolean which tracks whether the dealership is
 * allowed to acquire new cars. That business logic is implemented in the
 * DealershipController class.
 *
 * @author Paul Schmitz
 *
 */
public class Dealership {

    // FIELDS

    // the list of cars that belong to this dealership
    private List<Car> cars;

    // the dealership's unique id
    private final String dealershipId;

    // dealership name
    private String name;

    // whether the dealership is allowed to acquire cars
    private boolean acquireEnabled;

    // CONSTRUCTORS

    /**
     * creates a new dealership with the provided id. starts a car list using
     * ArrayList, and enables car acquisition.
     *
     * @param dealershipId the dealership id for the new dealership
     */
    public Dealership(String dealershipId) {
        cars = new ArrayList<Car>();
        this.dealershipId = dealershipId;
        acquireEnabled = true;
    }

    // METHODS

    /**
     * add a car to the dealership. note that no restrictions are implemented at
     * this level, so the dealership id of the car need not match the dealership's
     * id, and canAcquire is not checked.
     *
     * note: the business logic is implemented using the dealershipController class.
     *
     * @param car the car to add
     */
    public void addCar(Car car) {
        cars.add(car);
    }

    /**
     * @return the list of cars from this dealership
     */
    public List<Car> getCars() {
        return cars;
    }

    /**
     * @param cars the list of cars to set
     */
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    /**
     * @return the dealership id
     */
    public String getDealershipId() {
        return dealershipId;
    }

    /**
     * @return true if the dealership can acquire cars
     */
    public boolean isAcquireEnabled() {
        return acquireEnabled;
    }

    /**
     * @param acquireEnabled sets whether the dealership can acquire cars
     */
    public void setAcquireEnabled(boolean acquireEnabled) {
        this.acquireEnabled = acquireEnabled;
    }

    public String getName() {
        if (name != null) return name;
        else return "Dealer " + dealershipId;
    }

    public void setName(String name) {
        this.name = name;
    }
}