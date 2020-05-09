import java.util.HashMap;
import java.util.ArrayList;

public class Player {

    private int pilot;
    private int fighter;
    private int merchant;
    private int engineer;
    private int karma;
    private String currentRegion;
    private int credits;
    private Ship ship;
    private String name;
    private ArrayList<Item> inventory;
    private String npcMessage;

    public Player(HashMap<String, String> playerInfo) {
        this.credits = Integer.parseInt(playerInfo.get("money"));
        this.pilot = Integer.parseInt(playerInfo.get("pilot"));
        this.fighter = Integer.parseInt(playerInfo.get("fighter"));
        this.merchant = Integer.parseInt(playerInfo.get("merchant"));
        this.engineer = Integer.parseInt(playerInfo.get("engineer"));
        this.currentRegion = playerInfo.get("currentRegion");
        this.name = playerInfo.get("name");
        this.inventory = new ArrayList<Item>();
        //        inventory.add(Item.getRandomItem());
        //        inventory.add(Item.getRandomItem());
        ship = new Ship(ShipType.STARSHIP);
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
    public void setPilot(int pilot) {
        this.pilot = pilot;
    }
    public void setFighter(int fighter) {
        this.fighter = fighter;
    }
    public void setMerchant(int merchant) {
        this.merchant = merchant;
    }
    public void setEngineer(int engineer) {
        this.engineer = engineer;
    }
    public void setCurrentRegion(String currentRegion) {
        this.currentRegion = currentRegion;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setShip(Ship ship) {
        this.ship = ship;
    }
    public void setNPCMessage(String msg) {
        this.npcMessage = msg;
    }
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }
    public void setKarma(int karma) {
        this.karma = karma;
    }
    public void goodKarma() {
        karma += 1;
    }

    public void badKarma() {
        karma -= 1;
    }

    public Ship getShip() {
        return ship;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public int getKarma() {
        return karma;
    }
    public int getCredits() {
        return this.credits;
    }
    public int getPilot() {
        return this.pilot;
    }
    public int getFighter() {
        return this.fighter;
    }
    public int getMerchant() {
        return this.merchant;
    }
    public int getEngineer() {
        return this.engineer;
    }
    public String getCurrentRegion() {
        return this.currentRegion;
    }
    public String getName() {
        return this.name;
    }
    public String getNPCMessage() {
        return this.npcMessage;
    }
    public ArrayList<Item> inventory() {
        return this.inventory;
    }

    public boolean purchaseItem(Item item) {
        if (this.credits >= item.getPrice() && Game.getShip().getCargoSpace() > 0) {
            this.credits -= item.getPrice();
            //System.out.println(this.ship.getCargoSpace());
            inventory.add(item);
            Game.getShip().setCargoSpace(Game.getShip().getCargoSpace() - 1);
            this.ship.setCargoSpace(this.ship.getCargoSpace() - 1);
            GameScreen.repaintMainDisplayPanel();
            System.out.println(Game.getShip().getCargoSpace());
            return true;
        } else {
            return false;
        }
    }

    public void sellItem(Item item) {
        this.credits += item.getPrice();
        inventory.remove(item);
        Game.getShip().setCargoSpace(Game.getShip().getCargoSpace() + 1);
        GameScreen.repaintMainDisplayPanel();
        System.out.println(inventory);
    }



}
