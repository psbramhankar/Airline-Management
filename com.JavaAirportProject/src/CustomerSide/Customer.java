package CustomerSide;

public class Customer {

    private String name;
    private char gender;
    private String doB;
    private int age;
    private String phoneNumber;
    private String travellingFrom;
    private String travellingTo;
    private String preferredClass;

    public Customer(String name, char gender, String doB, int age, String phoneNumber, String travellingFrom, String travellingTo, String preferredClass) {
        this.name = name;
        this.gender = gender;
        this.doB = doB;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.travellingFrom = travellingFrom;
        this.travellingTo = travellingTo;
        this.preferredClass = preferredClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getDoB() {
        return doB;
    }

    public void setDoB(String doB) {
        this.doB = doB;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTravellingFrom() {
        return travellingFrom;
    }

    public void setTravellingFrom(String travellingFrom) {
        this.travellingFrom = travellingFrom;
    }

    public String getTravellingTo() {
        return travellingTo;
    }

    public void setTravellingTo(String travellingTo) {
        this.travellingTo = travellingTo;
    }

    public String getPreferredClass() {
        return preferredClass;
    }

    public void setPreferredClass(String preferredClass) {
        this.preferredClass = preferredClass;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", doB='" + doB + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", travellingFrom='" + travellingFrom + '\'' +
                ", travellingTo='" + travellingTo + '\'' +
                ", preferredClass='" + preferredClass + '\'' +
                '}';
    }
}



