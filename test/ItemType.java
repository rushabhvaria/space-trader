import java.util.Random;

public enum ItemType {

    UGA_DIPLOMA(10, 0),
    GATECH_DIPLOMA(400, 5),
    TREE_BRANCH(10, 0),
    WORM(10, 0),
    BAD_FUEL(10, 0),
    WORSE_FUEL(10, 0),
    GUN_POWDER(10, 0),
    ROCK(10, 0),
    STONE(10, 0),
    TREE_BOMB(10, 0),
    CUTOFF(50, 0),
    BIRD(10, 1),
    GUN(50, 3),
    FLAG(10, 1),
    BOOM_TUBE(100, 6),
    POCKET_BLACK_HOLE(70, 5),
    KOLAXIAN_CRYSTAL(20, 5),
    MEGA_SEED(80, 5),
    ROBOT(180, 5),
    VALERIAN_GEM(30, 4),
    FLEEB(10, 5),
    NATIONAL_OCEANIST_BANNER(100, 5),
    CCCPLANNER(100, 4),
    CRYOGENIC_CHAMBER(40, 6),
    BASIC_RATIONS(13, 3),
    HONEY(10, 1),
    CANNON(50, 2),
    WINITEM(0, 10);

    private double price;
    private int techLevel;


    ItemType(double price,  int techLevel) {
        this.price = price;
        this.techLevel = techLevel;
    }

    public int getTechLevel() {
        return techLevel;
    }


    public double getPrice() {
        return price;
    }

    public static ItemType getRandomItemType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }


    public String toString() {
        String item = String.format("%s: $%2.2f",  name(),  price);
        if (ordinal() == 26) {
            item = Game.getPlayer().getName() + "'s Universe";
        }
        return item;
    }

}
