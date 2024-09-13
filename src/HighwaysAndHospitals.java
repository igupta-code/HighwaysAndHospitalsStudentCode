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
        // # empty slots = # of clusters = # hospitals
        // # highways = cities(n) - # of clusters(empty slots)
        int[] roots = new int[n];

        int root1, root2 = 0;
        for(int i = 0; i < cities[0].length; i++){
            // Sets the root to the first layer above the city
            root1 = roots[cities[i][0]];
            root2 = roots[cities[i][1]];

            // Find root 1
            // If the layer above is 0, then the city is a root (meaning city = city's root)
            if(root1 == 0){
                root1 = cities[i][0];
            }
            else{
                // Keep going up the tree until the level above you is zero
                while(roots[root1] != 0){
                    root1 = roots[root1];
                }
            }

            // Similarly, find root 2
            if(root2 == 0){
                root2 = cities[i][1];
            }
            else{
                // Keep going up the tree until the level above you is zero
                while(roots[root2] != 0){
                    root2 = roots[root2];
                }
            }


            if(root1 != root2){
                // Make root1 the root of root2
                roots[root2] = root1;
            }

        }

        // Figure out how many cluster there are by looking at number of empty element in array
        int clusters = 0;
        for(int i = 0; i < roots.length; i++){
            if (roots[i] == 0){
                i++;
            }
        }

        return hospitalCost * clusters + highwayCost*(n - clusters);
    }
}
