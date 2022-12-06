package edu.metrostate.cardealer;

/**
 * The purpose of this class is to import xml files from dealerships that use xml
 */

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

//DEALERS ELEMENT
@XmlRootElement(name = "Dealers")
@XmlAccessorType(XmlAccessType.FIELD)
public class xmlDealers {
    @XmlElement(name = "Dealer")
    List<Dealer> dealer = null;

    public List<Dealer> getxmlDealer() {
        return dealer;
    }

    public void setxmlDealer(List<Dealer> dealer) {
        this.dealer = dealer;
    }

    //Method for deserialzing/unmarshalling the xml file
    public List<Car> xmlUnmarshal(File file){
        List<Car> cars = new ArrayList<>();
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(xmlDealers.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            xmlDealers dealers = (xmlDealers) unmarshaller.unmarshal(file);

            //data




            for(Dealer deal : dealers.getxmlDealer()) { //Loops through all dealers
                //data for car class
                // Extract data
                String dealershipID = null;
                String vehicleType = null;
                String vehicleManu = null; //Synonymous with brand or make
                String vehicleModel = null;
                String vehicleID = null;
                Double price = null;
                long acquisitionDate = System.currentTimeMillis()/1000;

                //New Data
                String dealerName = null;
                String currency = null;
                Boolean loaned = null;

                dealerName = deal.getDealerName();
                dealershipID = deal.getDealerId();

                for (Vehicles vehi : deal.getVehicles()) { //Loops through all vehicles;
                    vehicleType = vehi.getVehicleType();

                    vehicleID = vehi.getVehicleID();

                    vehicleManu = vehi.getVehicleMake();
                    vehicleModel = vehi.getVehicleModel();

                    for (Price pri : vehi.getPrices()) { //Loops through all prices

                        currency = pri.getCurrency();

                        price = pri.getValue();


                    }
                    Car car = new Car(dealershipID, vehicleType, vehicleManu, vehicleModel, vehicleID, price, acquisitionDate,
                            dealerName, currency, loaned);
                    // Array list for storing cars and their attributes
                    cars.add(car);


                }
                //Debugging
                // System.out.println(cars);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cars;
    }


}

//DEALER ELEMENT
@XmlRootElement(name = "Dealer")
@XmlAccessorType(XmlAccessType.FIELD)
class Dealer {
    @XmlAttribute(name = "id")
    String dealerId;

    @XmlElement(name = "Name")
    String dealerName;
    @XmlElement(name = "Vehicle")
    List<Vehicles> vehicles = null;

    public List<Vehicles> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicles> vehicles) {
        this.vehicles = vehicles;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
}

//VEHICLES ELEMENT
@XmlRootElement(name = "Vehicle")
@XmlAccessorType(XmlAccessType.FIELD)
class Vehicles {
    @XmlAttribute(name = "type")
    String vehicleType = null;
    @XmlAttribute(name = "id")
    String vehicleID = null;

    @XmlElement(name = "Make")
    String vehicleMake = null;
    @XmlElement(name = "Model")
    String vehicleModel = null;

    @XmlElement(name = "Price")
    List<Price> prices = null;

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
}

//PRICES
@XmlRootElement(name = "Price")
@XmlAccessorType(XmlAccessType.FIELD)
class Price{
    @XmlAttribute(name = "unit")
    String currency = null;

    @XmlValue
    double value;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}

