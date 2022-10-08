import java.util.HashSet;
import java.util.Set;
import java.lang.Math;

public class ProblemPancakes extends Problem {
    
    boolean goal_test(Object state) {
        StatePancakes pancake_state = (StatePancakes) state;

        int k = 0;
        for(int i=0; i<pancake_state.N; i++){
            if(pancake_state.pancakeArray[i] != k){
                return false;
            }
            k++;
        }

        return true;
    }

    Set<Object> getSuccessors(Object state) {

        Set<Object> set = new HashSet<Object>();
        StatePancakes s = (StatePancakes) state; 
        
        StatePancakes ss; //successor state
        
        int N = s.N;
        
        for (int k = 1; k <= N; k++){
            // flipping even # of pancakes 
            int j = k - 1;
            int i = 0;
            int count = 1;
            
            ss = new StatePancakes(s);
            if(k % 2 == 0) {
                while(count <= k/2){
                    ss.pancakeArray[i] = s.pancakeArray[j];
                    ss.pancakeArray[j] = s.pancakeArray[i];
                    i++;
                    j--;
                    count++;
                }
                set.add(ss);
            }

            // flipping odd # of pancakes 
            ss = new StatePancakes(s);
            if (k % 2 != 0){
                while(count < k/2 + 1){
                    ss.pancakeArray[i] = s.pancakeArray[j];
                    ss.pancakeArray[j] = s.pancakeArray[i];
                    i++;
                    j--;
                    count++;
                }
                set.add(ss);
            }
        }
        return set;
    }

    double step_cost(Object fromState, Object toState) { 
        return 1;
    }

    public double h(Object state) { 
        int num_neighbours_with_gaps = 0;
        StatePancakes s = (StatePancakes) state;         
        int N = s.N;
        int m = 0;
        int n = 1;
        for (int k = 0; k < (N-1); k++){
            if (Math.abs(s.pancakeArray[m] - s.pancakeArray[n]) != 1) {
                num_neighbours_with_gaps++;
            }
            m++;
            n++;
        }
        return num_neighbours_with_gaps; 
    }

    public static void main(String[] args) throws Exception {
        ProblemPancakes problem = new ProblemPancakes();
        int[] pancakeArray = {1, 0, 3, 5, 2, 4};
        problem.initialState = new StatePancakes(pancakeArray); 

        Search search = new Search(problem);

        System.out.println("TreeSearch------------------------");
        System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());

        System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
        System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
        System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
        System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());

        System.out.println("\n\nGraphSearch----------------------");
        System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
        System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
        System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
        System.out.println("GreedyBestGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
        System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());

        System.out.println("\n\nIterativeDeepening----------------------");
        System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
        System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
  
  }

}
