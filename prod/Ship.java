public class Ship {
    private ShipType shipType;
    private double fuelCapacity;
    private double startFuel;
    private int health;
    private int cargoSpace;
    private int maxHealth;
    private String name;

    public Ship(ShipType sType) {
        shipType = sType;
        fuelCapacity = sType.getFuelCapacity();
        startFuel = fuelCapacity;
        health = sType.getHealth();
        maxHealth = health;
        cargoSpace = sType.getCargoCapacity();
        name = sType.getName();
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public double getStartFuel() {
        return startFuel;
    }

    public void setStartFuel(double startFuel) {
        this.startFuel = startFuel;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int health) {
        this.maxHealth = health;
    }

    public int getCargoSpace() {
        return cargoSpace;
    }

    public void setCargoSpace(int cargoSpace) {
        this.cargoSpace = cargoSpace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
