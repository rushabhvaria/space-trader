public class Item {
    private double price;
    private ItemType itemType;


    public static Item getRandomItem() {
        ItemType randItem = ItemType.getRandomItemType();
        Item retItem = new Item(randItem.getPrice(), randItem);
        return retItem;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public Item(double price, ItemType itemType) {
        this.price = price;
        this.itemType = itemType;
    }

    public int getTechLevel() {
        return itemType.getTechLevel();
    }

    public String toString() {
        String toString = String.format("%s: $%2.2f", itemType.name(), price);
        if (itemType.ordinal() == 27) {
            toString = Game.getPlayer().getName() + "'s Universe: $500";
        }
        return toString;
    }

    public String getName() {
        String toString = String.format("%s", itemType.name());
        return toString;
    }

    public ItemType getItemType() {
        return itemType;
    }

}
