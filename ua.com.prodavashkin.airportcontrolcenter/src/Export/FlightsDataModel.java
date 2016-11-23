    package Export;

public class FlightsDataModel {
 
    private String flightNumber;
    private String portArrival;
    private String portDepartured;
    private String price;
    
 
    public FlightsDataModel() {
    }
 
    public FlightsDataModel(String flightNumber, String portArrival, String portDeparured, String price) {
        this.flightNumber = flightNumber;
        this.portArrival = portArrival;
        this.portDepartured = portDeparured;
        this.price = price;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPortArrival() {
        return portArrival;
    }

    public void setPortArrival(String portArrival) {
        this.portArrival = portArrival;
    }

    public String getPortDepartured() {
        return portDepartured;
    }

    public void setPortDepartured(String portDeparured) {
        this.portDepartured = portDeparured;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}