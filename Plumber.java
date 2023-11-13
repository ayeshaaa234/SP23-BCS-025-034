public class Plumber extends ServiceProvider {
    public Plumber(String name, String location) {
        super(name, location);
    }

    @Override
    public void provideService() {
        System.out.println("Plumber " + name + " is fixing the issue at " + location);
    }

    @Override
    public double calculateBill() {
        return 50.0; // A placeholder value, you can modify it based on actual rates.
    }
}
