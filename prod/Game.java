
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game {
    private static String gameDifficulty;
    private static HashMap<String, String> userInfo;
    private static String[] regionNames = {"Hyades Sector", "Orion Nebula",
        "Terragen Sphere", "Archaipelago", "Mosaic Star System", "XC-28",
        "Titan", "Arrowhead Galaxy", "Neptune", "Keiper Belt"};
    private static ArrayList<Region> rgnList;
    private static Region currentRegion;
    private static Player player;
    private static Ship ship;
    private static boolean win;
    private static boolean lose;

    public Game() {
        System.out.println("Creating new Game!");
    }

    public static void win() {
        Game.win = true;
    }

    public static void lose() {
        Game.lose = true;
    }

    public static boolean lost() {
        return Game.lose;
    }

    public static String getDifficulty() {
        return gameDifficulty;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Game.player = player;
    }

    public Game(HashMap<String, String> userInfo) {
        this.gameDifficulty = userInfo.get("difficulty");
        this.userInfo = userInfo;
        //startGame();
    }

    public static void setupGame(HashMap<String, String> userInfo) {
        gameDifficulty = userInfo.get("difficulty");
        Game.userInfo = userInfo;
    }

    public static void startGame() {
        player = new Player(userInfo);
        Universe universe = new Universe(regionNames);
        ShipType[] sTypes = ShipType.values();
        rgnList = universe.getRegions();
        Random randRegion = new Random();
        Random randShip = new Random();
        int randInt = randRegion.nextInt(universe.getRegions().size());
        int randS = randShip.nextInt(sTypes.length);
        ship = new Ship(sTypes[randS]);
        currentRegion = universe.getRegions().get(randInt);
        userInfo.put("currentRegion",
                currentRegion.toString());

        for (Region rgn: rgnList) {
            rgn.calcPrice(player.getMerchant());
        }

        //        for (Region rgn: rgnList) {
        //            for (Item itm: rgn.getMarket().getShelf()) {
        //                itm.setPrice( rgn.calcPrice(itm, player1.getMerchant()) );
        //            }
        //        }
    }

    public static String getCurrentRegion() {
        return userInfo.get("currentRegion");
    }

    public static Region getCurrRegion() {
        return currentRegion;
    }

    public static HashMap<String, String> getUserInfo() {
        return userInfo;
    }

    public static ArrayList<Region> getRegionList() {
        return rgnList;
    }

    public static void setCurrRegion(Region region) {
        currentRegion = region;
    }

    public static Ship getShip() {
        return ship;
    }

    public static void refresh() {
        win = false;
        lose = false;
    }


    /**
     Create a travel method. On button click for "Travel Map" button on local
     instance of GameScreen display TravelMap in frame or as new frame or as
     popup. Then select planet, get return value, reassign the value in
     currentRegion and reset the RegionDisplay value in GameScreen.
     **/

    private static void travel() {
        TravelMap tMap = new TravelMap(currentRegion,
                (Region[]) rgnList.toArray());
        tMap.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Use this method to add features specific to the current
                 * gamescreen/panel or whatever you use.
                 *
                 * Only AFTER this button is clicked:
                 * 1. Pull the value from selectedRegion
                 * 2. Close the screen or switch back to the original panel on
                 * the screen.
                 * 3. Set current region for the user to the new region.
                 * 4. Use the region display to display the new region.
                 */
            }
        });
        currentRegion = tMap.getSelectedRegion();
    }

}
