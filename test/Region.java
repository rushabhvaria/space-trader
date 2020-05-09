public class Region {

    private int x;
    private int y;
    private TechLevel techLevel;
    private String name;
    private int regionNumber;
    private Market market;

    public Region(int x, int y, TechLevel techLevel, String name, int number) {
        this.x = x;
        this.y = y;
        this.techLevel = techLevel;
        this.name = name;
        this.regionNumber = number;
        this.market = new Market(techLevel, number);
        //        for (Item itm: this.market.getShelf()) {
        //            itm.setPrice( calcPrice(itm, player1.getMerchant()) );
        //        }
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }
    public int getRegionNumber() {
        return regionNumber;
    }

    public Market getMarket() {
        return market;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", TechLevel: " + techLevel + ", Coordinates: "
                + x + ", " + y + ", Region Number: " + regionNumber;
    }

    public static double getDist(Region curr, Region newRegion) {
        int x1 = curr.getX();
        int x2 = newRegion.getX();
        int y1 = curr.getY();
        int y2 = newRegion.getY();
        double yDiff = Math.pow((y1 - y2), 2);
        double xDiff = Math.pow((x1 - x2), 2);
        System.out.println("xdiff: " + xDiff + "ydiff: " + yDiff);
        double dist = Math.sqrt(xDiff + yDiff);
        System.out.println("dist: " + dist);

        return dist;
    }

    public void calcPrice(int merchSkill) {
        //TODO change price first, similar to below, based on region/tech level
        //        item.setPrice(item.getPrice() - techLevel.ordinal());
        for (Item itm: getMarket().getShelf()) {
            if ((itm.getPrice() - merchSkill - (0.5 * techLevel.ordinal())) <= 0) {
                itm.setPrice(1.0);
            } else {
                itm.setPrice((itm.getPrice() - merchSkill - (0.5 * techLevel.ordinal())));
            }
        }
        //        System.out.println(item.getPrice() + " merch" + merchSkill);
    }
}
