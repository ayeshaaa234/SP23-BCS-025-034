

public abstract class ServiceProvider implements Service {
    protected String name;
    protected String location;

    public ServiceProvider(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public abstract void provideService();

    public abstract double calculateBill();

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

}