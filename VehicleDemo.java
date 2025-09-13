// Base Vehicle class
class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    
    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }
    
    public void forward() {
        System.out.println("Vehicle driving forward");
    }
    
    public void reverse() {
        System.out.println("Vehicle  driving reverse");
    }
    
    public String getInfo() {
        return year + " " + make + " " + model;
    }
}

// SUV class extending Vehicle
class SUV extends Vehicle {
    private final boolean fourWheelDrive;
    
    public SUV(String make, String model, int year, boolean fourWheelDrive) {
        super(make, model, year);
        this.fourWheelDrive = fourWheelDrive;
    }
    
    @Override
    public void forward() {
        if (fourWheelDrive) {
            System.out.println("SUV is driving  with all-wheel traction and higher ground clearance");
        } else {
            System.out.println("SUV is driving forward with standard traction");
        }
    }
    
    @Override
    public void reverse() {
        System.out.println("SUV is  reversing with backup sensors and camera assistance");
    }
    
    public void enableOffRoadMode() {
        System.out.println("SUV off-road mode activated");
    }
}

// SportsCar class extending Vehicle
class SportsCar extends Vehicle {
    private boolean turboMode;
    
    public SportsCar(String make, String model, int year, boolean turboMode) {
        super(make, model, year);
        this.turboMode = turboMode;
    }
    
    @Override
    public void forward() {
        if (turboMode) {
            System.out.println("Sports car is accelerating  with turbo boost engaged!");
        } else {
            System.out.println("Sports car is driving forward with smooth acceleration");
        }
    }
    
    @Override
    public void reverse() {
        System.out.println("Sports car is reversing with precision steering and low profile");
    }
    
    public void activateTurbo() {
        turboMode = true;
        System.out.println("Turbo mode activated - Maximum performance!");
    }
}

// Hybrid class extending Vehicle
class Hybrid extends Vehicle {
    private boolean electricMode;
    private int batteryLevel;
    
    public Hybrid(String make, String model, int year, int batteryLevel) {
        super(make, model, year);
        this.batteryLevel = batteryLevel;
        this.electricMode = batteryLevel > 20;
    }
    
    @Override
    public void forward() {
        if (electricMode && batteryLevel > 20) {
            System.out.println("Hybrid is moving forward silently on electric power (Battery: " + batteryLevel + "%)");
            batteryLevel -= 2;
        } else {
            System.out.println("Hybrid is moving forward using gasoline engine (Battery: " + batteryLevel + "%)");
            if (batteryLevel < 100) batteryLevel += 1; // Regenerative charging
        }
        
        if (batteryLevel <= 20) electricMode = false;
    }
    
    @Override
    public void reverse() {
        if (electricMode) {
            System.out.println("Hybrid is reversing quietly on electric power with regenerative braking");
            if (batteryLevel < 100) batteryLevel += 1;
        } else {
            System.out.println("Hybrid is reversing using gasoline engine");
        }
    }
    
    public void switchToElectricMode() {
        if (batteryLevel > 20) {
            electricMode = true;
            System.out.println("Switched to electric mode");
        } else {
            System.out.println("Insufficient battery for electric mode");
        }
    }
}

// Demo class to test the inheritance
public class VehicleDemo {
    public static void main(String[] args) {
        // Create instances of each vehicle type
        SUV suv = new SUV("Toyota", "RAV4", 2025, true);
        SportsCar sportsCar = new SportsCar("Porsche", "911", 2025, false);
        Hybrid hybrid = new Hybrid("Tesla", "S", 2025, 85);
        
        System.out.println("=== SUV Demo ===");
        System.out.println(suv.getInfo());
        suv.forward();
        suv.reverse();
        suv.enableOffRoadMode();
        
        System.out.println("\n=== Sports Car Demo ===");
        System.out.println(sportsCar.getInfo());
        sportsCar.forward();
        sportsCar.activateTurbo();
        sportsCar.forward();
        sportsCar.reverse();
        
        System.out.println("\n=== Hybrid Demo ===");
        System.out.println(hybrid.getInfo());
        hybrid.forward();
        hybrid.forward();
        hybrid.reverse();
        
        // Demonstrate polymorphism
        System.out.println("\n=== Polymorphism Demo ===");
        Vehicle[] vehicles = {suv, sportsCar, hybrid};
        
        for (Vehicle vehicle : vehicles) {
            System.out.println("\nTesting " + vehicle.getClass().getSimpleName() + ":");
            vehicle.forward();
            vehicle.reverse();
        }
    }
}