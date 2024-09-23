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
        // If highways are more expensive than hospitals, then build hospitals everywhere
        if(highwayCost > hospitalCost){
            System.out.println((long)(hospitalCost)* (long)(n));
            return (long)(hospitalCost)* (long)(n);
        }

        int[] roots = new int[n+1];
        int node = 0;
        int temp = 0;
        int root1, root2 = 0;

        for(int i = 0; i < cities.length; i++){
            // Temporarily sets the roots to the cities we are trying to merge
            root1 = cities[i][0];
            root2 = cities[i][1];

            // Find root 1
            node = root1;
            // Keep going up the tree until the level above you is a root (ie negative)
            while(roots[root1] > 0){
                root1 = roots[root1];
            }
            // Go through path again and assign everything on the way to top root
            while(roots[node] > 0){
                temp = roots[node];
                roots[node] = root1;
                node = temp;
            }

            // Similarly, find root 2
            // Keep going up the tree until the level above you is a root (ie negative)
            node = root2;
            while(roots[root2] > 0){
                root2 = roots[root2];
            }
            // Go through path again and assign everything on the way to top root
            while(roots[node] > 0){
                temp = roots[node];
                roots[node] = root2;
                node = temp;
            }

            // Combine trees
            if(root1 != root2){
                // Make the root with smaller magnitude of order the root
                if(roots[root2] >= roots[root1]){
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


        // Do math to figure out final cost
        int clusters = 0;
        int singles = 0;
        // Start for loop at one to avoid counting roots[0], which is always empty
        for(int i = 1; i < roots.length; i++){
            // Figure out how many cluster there are by looking at number of negative elements in array
            if (roots[i] < 0){
                clusters++;
            }
            // Figure out how many single cities there are by looking at number of 0s in array
            if (roots[i] == 0){
                singles++;
            }
        }
        System.out.println((long)(hospitalCost * (clusters+singles)) + (long)highwayCost*(n - (singles + clusters)));
        // Put hospitals at all stand-alone cities and put one hospital at each cluster
        // Put highways between clusters = (n - # of clusters)
        // But also no highways at stand-alone cities (so subtract singles)
        return (hospitalCost * (long)(clusters+singles))
                + (long)highwayCost*((long)n - (long)(singles + clusters));
    }
}
