import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Isha Gupta
 *
 */

public class HighwaysAndHospitals {

    /**
     * TODO: Complete this function, cost(), to return the minimum cost to provide
     *  hospital access for all citizens in Menlo County.
     */
    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {
        boolean[] hasVisited = new boolean[n];
        // Length = # of clusters = # hospitals
        // Size of each element = number of cities --- # highways = cities - 1
        ArrayList<Integer> clusters = new ArrayList<Integer>();


        Queue<Integer> toVisit = new LinkedList<Integer>();
        toVisit.add(0);


        for(int i = 0; i < n; i++){
        }


        return 0;
    }
}
