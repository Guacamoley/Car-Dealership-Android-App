package edu.metrostate.cardealer;

/**
 * The car class defines the car object that we use to create the cars for use in the dealerships.
 **/
public class Car implements Comparable<Car> { //CLASS DIAGRAM IS PUT IN AS "VEHICLE"
    // Fields
    private String dealership_id;
    private String vehicle_type;
    private String vehicle_manufacturer;
    private String vehicle_model;
    private String vehicle_id;
    private Double price;
    private long acquisition_date = System.currentTimeMillis()/1000;

    // New fields
    private String dealerName;
    private String currency;
    private Boolean loaned;

    // Constructors
    public Car(String dealership_id, String vehicle_id) {
        this.dealership_id = dealership_id;
        this.vehicle_id = vehicle_id;
    }

    public Car(String dealership_id, String vehicle_type, String vehicle_manufacturer, String vehicle_model,
               String vehicle_id, Double price, long acquisition_date, String dealerName, String currency, Boolean loaned) {
        this.dealership_id = dealership_id;
        this.vehicle_type = vehicle_type;
        this.vehicle_manufacturer = vehicle_manufacturer;
        this.vehicle_model = vehicle_model;
        this.vehicle_id = vehicle_id;
        this.price = price;
        this.acquisition_date = acquisition_date;
        this.dealerName = dealerName;
        this.currency = currency;
        this.loaned = loaned;
    }

    // Methods
    public Boolean isLoaned() {
        return loaned;
    }

    public void setLoaned(Boolean loaned) {
        this.loaned = loaned;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * getDealership_id will return the dealership_id from the car object, so it can be assigned to its corresponding dealership.
     **/
    public String getDealership_id() {
        return dealership_id;
    }

    /**
     * setter for the dealership_id.
     **/
    public void setDealership_id(String dealership_id) {
        this.dealership_id = dealership_id;
    }

    /**
     * returns the vehicle type from the car object.
     **/
    public String getVehicle_type() {
        return vehicle_type;
    }

    /**
     * setter for the vehicle type.
     **/
    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    /**
     * returns the manufacturer's name of the car object.
     **/
    public String getVehicle_manufacturer() {
        return vehicle_manufacturer;
    }

    /**
     * setter for the vehicle manufacturer.
     **/
    public void setVehicle_manufacturer(String vehicle_manufacturer) {
        this.vehicle_manufacturer = vehicle_manufacturer;
    }

    /**
     * will return the model of the car object.
     **/
    public String getVehicle_model() {
        return vehicle_model;
    }

    /**
     * setter for the model of the vehicle.
     **/
    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    /**
     * returns the vehicle id of the car object.
     **/
    public String getVehicle_id() {
        return vehicle_id;
    }


    /**
     * will set the vehicle id of the car object.
     **/
    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    /**
     * returns the price of the vehicle stored in the car object.
     **/
    public Double getPrice() {
        return price;
    }

    /**
     * setter for the vehicle price.
     **/
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * returns the date of acquisition of the car stored in the car object.
     **/
    public long getAcquisition_date() {
        return acquisition_date;
    }

    /**
     * setter for the acquisition date.
     **/
    public void setAcquisition_date(long acquisition_date) {
        this.acquisition_date = acquisition_date;
    }

    /**
     * toString for the car object. This method is used to list the vehicles in the gui.
     **/
    @Override
    public String toString() {
        return
                "dealership_id = " + dealership_id + '\n' +
                        "vehicle_type = " + vehicle_type + '\n' +
                        "vehicle_manufacturer = " + vehicle_manufacturer + '\n' +
                        "vehicle_model = " + vehicle_model + '\n' +
                        "vehicle_id = " + vehicle_id + '\n' +
                        "price = " + price + '\n' +
                        "acquisition_date = " + acquisition_date + '\n' +
                        "dealerName = " + dealerName + '\n' +
                        "currency = " + currency + '\n' +
                        "Loaned = " + loaned + '\n';

    }

    /**
     * compares cars first by dealership id, then by vehicle id
     *
     * @param o the object to be compared.
     * @return integer consistent with Comparable interface
     */
    @Override
    public int compareTo(Car o) {
        // first level of comparison is done by dealership id
        int firstComparison = this.dealership_id.compareTo(o.dealership_id);
        if (firstComparison != 0){
            return firstComparison;
        }
        // second level of comparison is done by vehicle id
        return this.vehicle_id.compareTo(o.vehicle_id);
    }
}
