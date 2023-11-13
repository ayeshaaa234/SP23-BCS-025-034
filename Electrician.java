public class Electrician extends ServiceProvider {
    public Electrician(String name, String location) {
        super(name, location);
    }

    @Override
    public void provideService() {
        System.out.println("Electrician " + name + " is fixing the electrical problem at " + location);
    }

    @Override
    public double calculateBill() {
        return 70.0; // A placeholder value, you can modify it based on actual rates.
    }
}

