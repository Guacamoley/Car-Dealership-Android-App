package edu.metrostate.cardealer;

/**
 * The car class defines the car object that we use to create the cars for use in the dealerships.
 **/
public class Car implements Comparable<Car> { //CLASS DIAGRAM IS PUT IN AS "VEHICLE"
    private String dealershipId;
    private String type;
    private String manufacturer;
    private String model;
    private String id;
    private Double price;
    private long acquisitionDate = System.currentTimeMillis()/1000;

    //New fields from new dealers
    private String dealerName;
    private String currency;

    private Boolean loaned;

    public Car(String dealershipId, String id) {
        this.dealershipId = dealershipId;
        this.id = id;
    }

    public Car(String dealershipId, String type, String manufacturer, String model,
               String id, Double price, long acquisitionDate, String dealerName, String currency, Boolean loaned) {
        this.dealershipId = dealershipId;
        this.type = type;
        this.manufacturer = manufacturer;
        this.model = model;
        this.id = id;
        this.price = price;
        this.acquisitionDate = acquisitionDate;
        this.dealerName = dealerName;
        this.currency = currency;
        this.loaned = loaned;
    }

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

    public String getDealershipId() {
        return dealershipId;
    }

    /**
     * setter for the dealership_id.
     **/

    public void setDealershipId(String dealership_id) {
        this.dealershipId = dealership_id;
    }

    /**
     * returns the vehicle type from the car object.
     **/

    public String getType() {
        return type;
    }

    /**
     * setter for the vehicle type.
     **/

    public void setType(String vehicle_type) {
        this.type = vehicle_type;
    }

    /**
     * returns the manufacturer's name of the car object.
     **/
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * setter for the vehicle manufacturer.
     **/
    public void setManufacturer(String vehicle_manufacturer) {
        this.manufacturer = vehicle_manufacturer;
    }

    /**
     * will return the model of the car object.
     **/
    public String getModel() {
        return model;
    }

    /**
     * setter for the model of the vehicle.
     **/
    public void setModel(String vehicle_model) {
        this.model = vehicle_model;
    }

    /**
     * returns the vehicle id of the car object.
     **/

    public String getId() {
        return id;
    }


    /**
     * will set the vehicle id of the car object.
     **/
    public void setId(String vehicle_id) {
        this.id = vehicle_id;
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
    public long getAcquisitionDate() {
        return acquisitionDate;
    }


    /**
     * setter for the acquisition date.
     **/
    public void setAcquisitionDate(long acquisition_date) {
        this.acquisitionDate = acquisition_date;
    }


    /**
     * toString for the car object. This method is used to list the vehicles in the gui.
     **/
    @Override
    public String toString() {
        return
                "dealership_id = " + dealershipId + '\n' +
                        "vehicle_type = " + type + '\n' +
                        "vehicle_manufacturer = " + manufacturer + '\n' +
                        "vehicle_model = " + model + '\n' +
                        "vehicle_id = " + id + '\n' +
                        "price = " + price + '\n' +
                        "acquisition_date = " + acquisitionDate + '\n' +
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
        int firstComparison = this.dealershipId.compareTo(o.dealershipId);
        if (firstComparison != 0){
            return firstComparison;
        }
        // second level of comparison is done by vehicle id
        return this.id.compareTo(o.id);
    }
}
