import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Universe {

    //private static Universe universeInstance;
    private static ArrayList<Region> regions;
    private static ArrayList<String> regionNames;

    /**
     * Will edit this constructor to accept an Array of Strings which are the
     * names of all the regions.
     *
     * @param regionNames The list of region names accepted from the game class.
     */
    public Universe(String[] regionNames) {
        Universe.regionNames = new ArrayList<>(Arrays.asList(regionNames));
        createRegions();
        //throw new RuntimeException("Use getInstance() method to instantiate
        // this class");
    }

    public Universe() {
        String[] names = {"Mercury", "Venus",
            "Earth", "Mars", "Jupiter", "Saturn",
            "Titan", "Uranus", "Neptune", "Pluto"};
        Universe.regionNames = new ArrayList<>(Arrays.asList(names));
        createRegions();
    }

    /**
     * Will edit this class to accept an Array of Strings which are the names
     * of all the regions.
     * @return a single instance of the Universe class.
     */

    /*    public static Universe getInstance() {
            if (universeInstance == null) {
                universeInstance = new Universe();
            }

            //regions = new ArrayList<Region> regions;
            //Need to assign regions as an ArrayList<Region>
            using the List created in the Game class.

            //Will allow this method to accept list of names
            createRegions();
            return universeInstance;
        }
        */


    private static void createRegions() {
        /*
        Use the random class to create all the regions. Make sure all the regions
        are at least 5 coordinates apart from each other.
         */
        Class tchLvl = TechLevel.class;
        Object[] tchLevels = tchLvl.getEnumConstants();
        //System.out.println(tchLevels[1].toString() instanceof String);
        regions = new ArrayList<>();

        Random randx = new Random();
        Random randy = new Random();
        Random randRegion = new Random();
        Random randName = new Random();
        int winRegion = new Random().nextInt(9);

        int[] xArr = new int[10];
        int[] yArr = new int[10];

        ArrayList<Integer> xList = new ArrayList<>();
        ArrayList<Integer> yList = new ArrayList<>();

        for (int i = -200; i <= 200; i++) {
            xList.add(i);
            yList.add(i);
        }

        int bounds = 401;

        for (int i = 0; i < 10; i++) {
            int x = randx.nextInt(bounds);
            int y = randy.nextInt(bounds);
            int rand = randRegion.nextInt(7);
            int rName = randName.nextInt(regionNames.size());
            int xCoord = xList.get(x);
            int yCoord = yList.get(y);
            xArr[i] = xCoord;
            yArr[i] = yCoord;
            //System.out.println(xCoord + "," + yCoord + " - "
            //      + (TechLevel) tchLevels[rand]);
            regions.add(new Region(xCoord, yCoord, (TechLevel) tchLevels[rand],
                regionNames.get(rName), i + 1));
            regionNames.remove(rName);
            Market.setWinRegion(winRegion);
            System.out.println(regions.get(i).toString());
            xList.remove(x);
            yList.remove(y);
            for (int j = 1; j <= 5; j++) {
                xList.remove((Integer) (xCoord - j));
                xList.remove((Integer) (xCoord + j));
                yList.remove((Integer) (yCoord - j));
                yList.remove((Integer) (yCoord + j));

            }
            bounds = bounds - 11;
        }
    }

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public static void main(String[] args) {
        String[] names = {"Mercury", "Venus",
            "Earth", "Mars", "Jupiter", "Saturn",
            "Titan", "Uranus", "Neptune", "Pluto"};
        regionNames = new ArrayList<>(Arrays.asList(names));
        createRegions();
    }
}
