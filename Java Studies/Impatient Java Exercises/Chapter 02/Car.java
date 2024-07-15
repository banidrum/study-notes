public class Car {

    public double distance;
    public double fuelLevel;
    public final double fuelEfficiency;

    public Car(double fuelEfficiency) {
        this.distance = 0;
        this.fuelLevel = 0;
        this.fuelEfficiency = fuelEfficiency;
    }

    void drive(double miles) {
        if (miles < 0) {
            throw new IllegalArgumentException("Miles cannot be lower than 0.");
        }

        double requiredFuel = miles / fuelEfficiency;

        distance += miles;
        fuelLevel -= requiredFuel;
    }

    void fillTank(double gallons) {
        fuelLevel += gallons;
    }

    Double getFuelLevel() {
        return fuelLevel;
    }

    Double getDistanceFromOrigin() {
        return distance;
    }

    public static void main(String[] args) {
        var car = new Car(25.0);
        car.fillTank(10);

        System.out.println("Current car fuel level is: " + car.getFuelLevel());

        car.drive(150);
        System.out.println("Distance from origin: " + car.getDistanceFromOrigin());
        System.out.println("After travelling, the current fuel level is: " + car.getFuelLevel());
    }
}
