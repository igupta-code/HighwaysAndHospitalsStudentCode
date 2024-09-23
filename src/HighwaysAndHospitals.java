import java.sql.SQLOutput;
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
        if(highwayCost > hospitalCost){
            System.out.println((long)(hospitalCost)* (long)(n));
            return (long)(hospitalCost)* (long)(n);
        }

        int[] roots = new int[n+1];
        int node = 0;
        int temp = 0;

        int root1, root2 = 0;
        for(int i = 0; i < cities.length; i++){
            // Sets the root to the first layer above the city
            root1 = cities[i][0];
            root2 = cities[i][1];


            // Find root 1
            node = root1;
            // Keep going up the tree until the level above you is zero(that's you're root)
            while(roots[root1] > 0){
                root1 = roots[root1];
            }
            // Go through path again and assign everything on the way to top root
            // While the root of the node is not the top off the tree **and** the root of the node is not 0
            while(roots[node] > 0 && roots[node] != root1 ){
                temp = roots[node];
                roots[node] = root1;
                node = temp;
            }

            // Similarly, find root 2
            // Keep going up the tree until the level above you is zero
            node = root2;
            while(roots[root2] > 0){
                root2 = roots[root2];
            }
            // Go through path again and assign everything on the way to top root
            while(roots[node] > 0 && roots[node] != root2){
                temp = roots[node];
                roots[node] = root2;
                node = temp;
            }


            if(root1 != root2){
                // Make the root with smaller magnitude of order the root
                if(roots[root2] >= roots[root1]) {
                    // Make root1 the root of root2
                    roots[root1] = roots[root2] - 1;
                    roots[root2] = cities[i][0];
                }
                else{
                    // Make root2 the root of root1
                    roots[root2] = roots[root1] - 1;
                    roots[root1] = cities[i][1];

                }
            }
        }

        // Figure out how many cluster there are by looking at number of empty element in array
        int clusters = 0;
        int singles = 0;
        for(int i = 1; i < roots.length; i++){
            if (roots[i] < 0){
                clusters++;
            }
            if (roots[i] == 0){
                singles++;
            }
        }
        System.out.println((long)(hospitalCost * (clusters+singles)) + (long)highwayCost*(n - (singles + clusters)));
        return (long)(hospitalCost * (long)(clusters+singles)) + (long)highwayCost*((long)n - (long)(singles + clusters));
    }
}
