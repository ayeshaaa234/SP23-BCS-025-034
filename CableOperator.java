public class CableOperator extends ServiceProvider{
    public CableOperator(String name, String location) {
        super(name, location);
    }

    @Override
    public void provideService() {
        System.out.println("Cable operator " + name + " is setting up the connection at " + location);
    }

    @Override
    public double calculateBill() {
        return 30.0; // A placeholder value, you can modify it based on actual rates.
    }
}
