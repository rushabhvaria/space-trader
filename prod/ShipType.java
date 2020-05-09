public enum ShipType {
    STARSHIP(20, 275, 100, "Starship"),
    JET(30, 375, 150, "Jet"),
    WASP(40, 475, 200, "Wasp"),
    LADYBUG(50, 575, 250, "Ladybug"),
    BUBMBLEBEE(60, 700, 300, "Bumblebee"),
    ROBOFLIGHT(70, 800, 350, "Roboflight");

    private int cargoCapacity;
    private double fuelCapacity;
    private int health;
    private String name;

    private ShipType(int cSpace, double fCap, int hth, String nme) {
        cargoCapacity = cSpace;
        fuelCapacity = fCap;
        health = hth;
        name = nme;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
