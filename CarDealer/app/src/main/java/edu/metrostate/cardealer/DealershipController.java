package edu.metrostate.cardealer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class manages a list of dealerships and the cars at those dealerships.
 * Note that dealerships contain a list of cars. This class takes incoming car
 * objects and adds them to the corresponding dealership by matching the car's
 * dealership id to the existing dealership's id. If an incoming car's
 * dealership id does not match a dealership, a new dealership is created and
 * added to the managed list. This class cannot add cars to a dealership if
 * acquiring is not enabled for that dealership. Newly created dealerships have
 * acquiring enabled by default.
 *
 * @author Paul Schmitz
 */
public class DealershipController {

    // FIELDS

    /**
     * holds all the car dealerships that currently exist. dealerships added to
     * this list should have unique dealership id's since that parameter is used as
     * a search key.
     */
    private final Map<String, Dealership> dealerships;

    // CONSTRUCTORS

    /**
     * standard constructor which starts an empty array list for dealerships.
     */
    public DealershipController() {
        dealerships = new HashMap<String, Dealership>();
    }

    // METHODS

    /**
     * adds one car to a dealership by matching the car's dealership id to the
     * dealership's id. will create a new dealership if needed. doesn't add anything
     * if the dealership is set up to not allow acquisitions. newly created
     * dealerships are set to allow acquisitions by default. returns a status
     * enumeration failure if the car could not be added to the dealership.
     * otherwise it returns a status enumeration success.
     *
     * @param car the car to add
     * @return status enumeration failure if the car was not added. otherwise,
     * status enumeration success.
     */
    public Status addCar(Car car) {
        // get car's dealership ID
        String dealershipId = null;
        if (car == null) {
            return Status.FAILURE;
        } else {
            dealershipId = car.getDealership_id();
        }

        // if car has no dealership ID, return failure status
        if (dealershipId == null) {
            return Status.FAILURE;
        }

        // search existing dealerships for ID
        Dealership dealership = dealerships.get(dealershipId);

        // if none exists, create dealership with that ID
        if (dealership == null) {
            dealership = new Dealership(dealershipId);
            dealerships.put(dealershipId, dealership);
        }

        // check if dealership is able to acquire cars
        if (!dealership.isAcquireEnabled()) {
            return Status.FAILURE;
        }

        // add car to dealership
        dealership.addCar(car);

        // if car came with a dealer name, update dealer name accordingly
        String dealerName = car.getDealerName();
        if(dealerName != null && !dealerName.equals("")) {
            dealership.setName(car.getDealerName());
        }

        // returns success because the car was successfully added
        return Status.SUCCESS;
    }

    /**
     * adds a list of cars to their respective dealerships using each car's
     * dealership id. will create new dealerships if needed. returns a list of
     * enumerations indicating whether each car was successfully added.
     *
     * @param cars a list of cars to add
     * @return a list of status enumerations representing whether each car
     * was successfully added. the order of the list corresponds to the
     * ordering of the input cars list.
     */
    public List<Status> addCars(List<Car> cars) {
        // the list of statuses
        List<Status> statusList = new ArrayList<Status>();

        // check for null argument
        if (cars != null) {

            // for each car in list, addCar(car)
            for (int i = 0; i < cars.size(); i++) {

                // add each car, and collect the status of each attempt
                Status currentStatus = addCar(cars.get(i));

                // add each status to the list
                statusList.add(currentStatus);
            }
        }
        return statusList;
    }

    public void setDealershipCars(List<Car> c, String dealerID) {
        Dealership dealership = dealerships.get(dealerID);
        dealership.setCars(c);
    }

    /**
     * Removes from the specified dealership the specified car. Done by matching
     * dealership id and vehicle id. Multiple vehicles could be removed if they all match.
     *
     * @param dealershipID the dealership id to remove from
     * @param carID        the vehicle id to be removed
     */
    public void removeCar(String dealershipID, String carID) {
        Dealership dealership = dealerships.get(dealershipID);
        if (dealership != null) {
            for (int i = 0; i < dealership.getCars().size(); i++) {
                Car currentCar = dealership.getCars().get(i);
                if (currentCar.getVehicle_id().equals(carID)) {
                    if (!currentCar.isLoaned())
                        dealership.getCars().remove(i--);
                }
            }
        }
    }

    /**
     * sets whether a given dealership can acquire new cars.
     *
     * @param dealershipId   the dealership id to affect
     * @param acquireEnabled the boolean value to set
     */
    public void setDealershipAcquireEnabled(String dealershipId, boolean acquireEnabled) {
        Dealership dealership = dealerships.get(dealershipId);
        if (dealership != null) {
            dealership.setAcquireEnabled(acquireEnabled);
        } else {
            return;
        }
    }

    /**
     * returns true if the dealership currently exists and allows acquisitions.
     *
     * @param dealershipId the dealership id to check
     * @return true if the dealership exists and allows acquisition, otherwise false
     */
    public boolean getDealershipAcquireEnabled(String dealershipId) {
        Dealership dealership = dealerships.get(dealershipId);
        if (dealership != null) {
            return dealership.isAcquireEnabled();
        } else
            return false;
    }

    /**
     * gets the list of all cars belonging to the given dealership id. returns an
     * empty list if the dealership cannot be found.
     *
     * @param dealershipId the dealership id to match
     * @return the list of all cars for this dealership
     */
    public List<Car> getDealershipCars(String dealershipId) {
        Dealership dealership = dealerships.get(dealershipId);
        if (dealership != null) {
            return dealership.getCars();
        } else {
            return new ArrayList<Car>();
        }
    }

    /**
     * gets the list of all current dealership id's.
     *
     * @return the list of all dealership id's
     */
    public List<String> getDealershipIds() {
        return new ArrayList<String>(dealerships.keySet());
    }

    /**
     * gets the list of all current dealership objects
     * @return list of all dealerships
     */
    public List<Dealership> getAllDealerships(){
        return new ArrayList<Dealership>(dealerships.values());
    }

    /**
     * gets all cars from all dealerships
     *
     * @return the list of all cars
     */
    public List<Car> getAllCars() {
        List<Car> allCars = new ArrayList<Car>();

        // for each dealership, get its cars and add it to the list
        for (Dealership d : dealerships.values()) {
            allCars.addAll(d.getCars());
        }
        return allCars;
    }

    public Dealership getDealerById(String dealerId) {
        return dealerships.get(dealerId);
    }

    public Car getCarbyId(String dealerId, String vehicleId) {
        List<Car> dealerCars = getDealershipCars(dealerId);
        for (Car c : dealerCars) {
            if (c.getVehicle_id().equals(vehicleId)){
                return c;
            }
        }
        return null;
    }
}