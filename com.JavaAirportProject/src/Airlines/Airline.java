package Airlines;

public class Airline {
    public int Sr;
    private String source;
    private String destination;
    private double economyClass;
    private double businessClass;
    private double firstClass;

    public Airline(int sr, String source, String destination, double economyClass, double businessClass, double firstClass) {
        Sr = sr;
        this.source = source;
        this.destination = destination;
        this.economyClass = economyClass;
        this.businessClass = businessClass;
        this.firstClass = firstClass;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getSr() {
        return Sr;
    }

    public void setSr(int sr) {
        Sr = sr;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getEconomyClass() {
        return economyClass;
    }

    public void setEconomyClass(double economyClass) {
        this.economyClass = economyClass;
    }

    public double getBusinessClass() {
        return businessClass;
    }

    public void setBusinessClass(double businessClass) {
        this.businessClass = businessClass;
    }

    public double getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(double firstClass) {
        this.firstClass = firstClass;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "Sr=" + Sr +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", economyClass=" + economyClass +
                ", businessClass=" + businessClass +
                ", firstClass=" + firstClass +
                '}';
    }
}
