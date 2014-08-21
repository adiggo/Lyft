import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Xiaoping on 8/21/14.
 */


public class FindDetourDistance {



    static Map<Character, Location> location = new HashMap<Character, Location>();

    static Location A, B, C, D;
    public static void dataMock(){
        A = new Location(0, 0);
        B = new Location(0, 30);
        C = new Location(30, 0);
        D = new Location(30, 30);
    }


    //mock the path
    public static List<String> generatePath(){
        List<String> pathList = new ArrayList<String>();
        pathList.add("ACDB");
        pathList.add("CABD");
        return pathList;
    }



    public static void main(String[] args) {
        dataMock(); //mock initial data
        location.put('A', A);
        location.put('B', B);
        location.put('C', C);
        location.put('D', D);
        List<String> paths = generatePath();
        List<Double> distances = new ArrayList<Double>();
        for (String path : paths){
            double distance = 0;
            for (int k = 0; k < path.length() - 1; k++){
                Location start = location.get(path.charAt(k));
                Location end = location.get(path.charAt(k+1));
                distance += getDistance(start, end);

            }
            distances.add(distance);
        }
        //get minimal distance
        double minimal_distance = Integer.MAX_VALUE;
        for (Double d : distances){
            if (d < minimal_distance)
                minimal_distance = d;
        }
        System.out.printf("The least distance is %f miles", minimal_distance);
    }


    /**
     *
     * @param a start location which is formed by longitude and latitude
     * @param b end location which formed by longitude and latitude
     * @return distance between two different location.
     */
    public static double getDistance(Location a, Location b) {
        double radius = 3958.75;  //miles, earth radius
        double aLatitudeRadian = Math.toRadians(a.latitude);
        double bLatitudeRadian = Math.toRadians(b.latitude);


        double dLongitude = Math.toRadians(a.longitude) - Math.toRadians(b.longitude);

        double relativeRadian = Math.sin(aLatitudeRadian) * Math.sin(bLatitudeRadian) +
                Math.cos(aLatitudeRadian) * Math.cos(bLatitudeRadian);
        double distance = radius * Math.acos(relativeRadian);
        return distance;
    }


}
