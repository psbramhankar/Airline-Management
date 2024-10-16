package AirportSide;

public class Airport {

    public String flightNo;
    public String airline;
    public String time;
    public Double travelDuration;
    public String sourceCity;

    public String destinationCity;

    public double firstClassFare;
    public double businessClassFare;
    public double economyClassFare;

    public Airport(String flightNo, String airline, String time, Double travelDuration, String sourceCity, String destinationCity,
                   double firstClassFare, double businessClassFare, double economyClassFare) {
        this.flightNo = flightNo;
        this.airline = airline;
        this.time = time;
        this.travelDuration = travelDuration;
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
        this.firstClassFare = firstClassFare;
        this.businessClassFare = businessClassFare;
        this.economyClassFare = economyClassFare;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getTravelDuration() {
        return travelDuration;
    }

    public void setTravelDuration(Double travelDuration) {
        this.travelDuration = travelDuration;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public double getFirstClassFare() {
        return firstClassFare;
    }

    public void setFirstClassFare(double firstClassFare) {
        this.firstClassFare = firstClassFare;
    }

    public double getBusinessClassFare() {
        return businessClassFare;
    }

    public void setBusinessClassFare(double businessClassFare) {
        this.businessClassFare = businessClassFare;
    }

    public double getEconomyClassFare() {
        return economyClassFare;
    }

    public void setEconomyClassFare(double economyClassFare) {
        this.economyClassFare = economyClassFare;
    }

    @Override
    public String toString() {
        return "\nflightNo=" + flightNo + "\nAirline= " + airline + "\ntime= " + time + "\ntravelDuration= " + travelDuration +
                " hr\nsourceCity='" + sourceCity + "\ndestinationCity=" + destinationCity + "\nfirstClassFare=" + firstClassFare +
                "\nbusinessClassFare= " + businessClassFare + "\neconomyClassFare=" + economyClassFare +"\n";
    }
}
