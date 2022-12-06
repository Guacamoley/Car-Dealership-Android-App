package edu.metrostate.cardealer;

import java.io.File;
import java.util.List;

/**
 * This class represents an inventory of cars distributed across multiple
 * dealerships. The public methods represent the fundamental functional
 * requirements. New car additions are handled using a
 * DealershipSystem.DealershipController, which controls a particular list of
 * dealerships ensuring that cars are added to the correct dealership.
 *
 * @author Paul Schmitz
 */
public class Inventory {

    // FIELDS

    /*
     * the dealership controller responsible for handling the creation of new
     * dealerships and the assignment of new cars to those dealerships.
     */
    private final DealershipController dc;

    /*
     * the json handler responsible for file import/export functions.
     */
    private final Json c = new Json();

    //private final xmlDealers xml = new xmlDealers();

    // CONSTRUCTORS

    public Inventory() {
        dc = new DealershipController();
    }

    // METHODS

    /**
     * Passes the file to the json reader to get the list of car objects. Then, the
     * objects are added to the inventory through the dealership controller. This
     * places each car into its correct dealership. The list of status enumerations
     * indicating which cars were successfully added is returned.
     *
     * @param file the json file to import
     * @return the list of status enumerations indicating whether each car
     * was successfully added.
     */
    public List<Status> importFile(File file, String extension) {
        if (extension.matches("json"))
            return dc.addCars(c.readFile(file));
        else if (extension.matches("xml"))
            //return dc.addCars(xml.xmlUnmarshal(file));
            return null;
        return null;
    }

    /**
     * returns the list of all dealership id strings.
     *
     * @return list of all dealership id's
     */
    public List<String> getAllDealershipIds() {
        return dc.getDealershipIds();
    }

    /**
     *
     * @return list of all dealerships
     */
    public List<Dealership> getAllDealerships(){
        return dc.getAllDealerships();
    }

    /**
     * adds one new vehicle per assignment requirement #5. returns a status
     * enumeration indicating whether it was successfully added. this
     * requires the car's dealership ID to be non-null. a null car parameter will
     * return a failure.
     *
     * @param car the car to add
     * @return true if the car was successfully added to the inventory system.
     * otherwise false.
     */
    public Status addIncomingVehicle(Car car) {
        return dc.addCar(car);
    }

    /**
     * removes one car from its dealer. the car is found by searching its dealerId
     * among the dealers and by matching the cars' vehicleIds. both ID matches are
     * case-sensitive. returns status enumeration indicating if it was successful.
     *
     * @param target the car to remove
     * @return status enumeration success if the car was found and removed.
     * otherwise returns status failure.
     */
    public Status removeIncomingVehicle(Car target) {
        // check if the car has the necessary fields to search and match
        if ((target == null || target.getDealershipId() == null) || (target.getId() == null)) {
            return Status.FAILURE;
        } else {
            // based on the car's dealerId, get that dealer's list of cars
            String dealershipToSearch = target.getDealershipId();
            for (Car currentCar : dc.getDealershipCars(dealershipToSearch)) {

                // search the list of cars for the matching car using vehicle id
                if (currentCar.getId().equals(target.getId())) {

                    // replace the dealer's car list with a new list of cars which has the target
                    // car removed
                    List<Car> updatedList = dc.getDealershipCars(dealershipToSearch);
                    updatedList.remove(currentCar);
                    dc.setDealershipCars(updatedList, dealershipToSearch);
                    return Status.SUCCESS;
                }
            }
        }
        return Status.FAILURE;
    }

    /**
     * checks if the dealership exists and allows acquisitions.
     *
     * @param dealershipId the dealership id to check
     * @return true if the dealership exists in the system, and it allows
     * acquisitions.
     */
    public boolean getDealerAcquisition(String dealershipId) {
        return dc.getDealershipAcquireEnabled(dealershipId);
    }

    /**
     * checks to see if the dealership being added via the add car button is a new
     * dealership ID
     *
     * @param dealershipId the dealership id to check
     * @return true if the dealership has been added already
     **/
    public boolean isExistingDealer(String dealershipId) {
        List<String> currentDealers = dc.getDealershipIds();
        return currentDealers.contains(dealershipId);
    }

    /**
     * toggles this dealership so that it can acquire future cars.
     *
     * @param dealershipId the dealership id to enable
     */
    public void enableDealerAcquisition(String dealershipId) {
        dc.setDealershipAcquireEnabled(dealershipId, true);
    }

    /**
     * toggles this dealership so that it cannot acquire future cars.
     *
     * @param dealershipId the dealership id to disable
     */
    public void disableDealerAcquisition(String dealershipId) {
        dc.setDealershipAcquireEnabled(dealershipId, false);
    }

    /**
     * export all cars for this dealership to a json file in the resources folder.
     *
     * @param dealershipId the dealership id to export
     */
    public void exportFile(String dealershipId, String fileName) {
        // get the list of cars to be exported
        List<Car> myList = dc.getDealershipCars(dealershipId);

        // pass the list to the json handler
        c.exportFile(myList, fileName);
    }

    /**
     * This method saves the current session (all cars) as a json file at the
     * specified path. it returns a status enumeration indicating success or
     * failure.
     *
     * @param savePath the path of the desired file
     * @return Status indicating whether the session was saved successfully
     * or not
     */
    public Status exportSession(String savePath) {
        // retrieve all cars and send them to the json exporter.
        c.exportFile(dc.getAllCars(), savePath);

        // return status
        return Status.SUCCESS;
    }

    // returns the list of cars in a specified dealership
    public List<Car> getDealerCars(String dealershipId) {
        return dc.getDealershipCars(dealershipId);
    }

    /**
     * gets one string which includes all cars at the specified dealership. the
     * specified delimiter string is inserted between each entry. returns "" if
     * there are no entries.
     *
     * @param dealershipId the dealership id to retrieve from
     * @param delimiter    the string to insert between each entry
     * @return string including all cars from the dealership with delimiters
     * inserted
     */
    public String printCars(String dealershipId, String delimiter) {
        StringBuilder result = new StringBuilder();
        List<Car> cars = dc.getDealershipCars(dealershipId);
        for (Car c : cars) {
            result.append(c.toString()).append(delimiter);
        }
        return result.toString();
    }

    /**
     * Transfers a car between two dealerships from user input.
     *
     * @param dealership1 The dealership to transfer a vehicle from.
     * @param dealership2 The dealership to transfer a vehicle to.
     * @param vehicleID   The car that is getting transferred.
     */
    public void transferCar(String dealership1, String dealership2, String vehicleID) {
        if (transferCarLogic(dealership1, dealership2, vehicleID)) {
            for (Car car : getDealerCars(dealership1)) {
                if (car.getId().equalsIgnoreCase(vehicleID)) {
                    car.setDealershipId(dealership2);
                    addIncomingVehicle(car);
                    getDealerCars(dealership1).remove(car);
                    break;
                }
            }
        }
    }

    /**
     * Logic used for transferCar method. Checks if dealer acquisition is enabled and if the
     * dealerID is the same as the transfer dealer. Checks if dealer1 has the car in its inventory.
     * Checks if car is loaned out. If all are false, then returns true.
     *
     * @param dealership1 The dealership to transfer a vehicle from.
     * @param dealership2 The dealership to transfer a vehicle to.
     * @param vehicleID   The car that is getting transferred.
     * @return false If one of the parameters is flagged.
     * @return true If every parameter passes.
     */
    private boolean transferCarLogic(String dealership1, String dealership2, String vehicleID) {
        if (getDealerAcquisition(dealership2) && dealership2.equalsIgnoreCase(dealership1))
            return false;
        if (!getDealerCars(dealership1).toString().contains(vehicleID))
            return false;
        Car carTransfer = getCarObject(dealership1, vehicleID);
        return !carTransfer.isLoaned();
    }

    // Method for returning a new car object with passed in dealerID and carID.
    private Car getCarObject(String dealerID, String carID) {
        for (Car car : this.getDealerCars(dealerID)) {
            if (car.getId().equals(carID)) {
                return car;
            }
        }
        return null;
    }

    public Dealership getDealerById(String dealerId) {
        return dc.getDealerById(dealerId);
    }

    public Car getCarById(String dealerId, String vehicleId) {
        return dc.getCarbyId(dealerId, vehicleId);
    }
}
