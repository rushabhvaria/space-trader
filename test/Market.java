//import java.util.Random;
import java.util.ArrayList;

public class Market {

    private ArrayList<Item> shelf;
    private TechLevel techLevel;
    private ArrayList<ItemType> typeList;
    private static int randRegion;

    public Market(TechLevel techLevel, int regionNumber) {
        this.techLevel = techLevel;
        this.typeList = new ArrayList<ItemType>();
        this.shelf = new ArrayList<Item>();
        updateShelf();
        if (regionNumber == randRegion) {
            System.out.println("");
            System.out.println("Worked");
            System.out.println("");
            shelf.remove(shelf.get(9));
            shelf.add(new Item(500, ItemType.WINITEM));
            System.out.println("Worked");
        }
        System.out.println(this.shelf);
    }

    public static void setWinRegion(int num) {
        Market.randRegion = num;
    }

    public void updateShelf() {
        for (int i = 0; i < 10; i++) {
            Item randItem = Item.getRandomItem();
            //randItem.getTechLevel() <= techLevel.ordinal()
            if (!typeList.contains(randItem.getItemType())
                && randItem.getTechLevel() <= techLevel.ordinal()) {
                System.out.println(techLevel.ordinal() + " " + randItem.toString());
                typeList.add(randItem.getItemType());
                shelf.add(randItem);
            } else {
                i--;
            }
        }
    }

    public ArrayList<Item> getShelf() {
        return shelf;
    }
}
