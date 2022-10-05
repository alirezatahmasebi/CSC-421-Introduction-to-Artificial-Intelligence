import java.util.HashSet;
import java.util.Set;
import java.lang.Math;

public class ProblemPancakes extends Problem {

    //public static int total_cost;
    
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
        //Random rand = new Random();
        //int sp = rand.nextInt(N); // get a number between 0 and N-1 (position of the spatula)
        //sp += 1; // adjust the number to be between 1 and N (# of pancakes to flip)
        int sp = (int)Math.floor(Math.random()*(N-1+1)+1);
        int j = sp - 1;
        int i = 0;
        int count = 1;
        //ProblemPancakes.total_cost = ProblemPancakes.total_cost + sp;
        System.out.println(sp);
        
        // flipping even # of pancakes 
        ss = new StatePancakes(s);
        if(sp % 2 == 0) {
            while(count <= sp/2){
                ss.pancakeArray[i] = s.pancakeArray[j];
                ss.pancakeArray[j] = s.pancakeArray[i];
                i++;
                j--;
                count++;
            }
            //System.out.println(ss);
            set.add(ss);
        }
        
        // flipping odd # of pancakes 
        ss = new StatePancakes(s);
        if (sp % 2 != 0){
            while(count < sp/2){
                ss.pancakeArray[i] = s.pancakeArray[j];
                ss.pancakeArray[j] = s.pancakeArray[i];
                i++;
                j--;
                count++;
            }
            //System.out.println(ss);
            set.add(ss);
        }

        return set;
    }

    double step_cost(Object fromState, Object toState) { return 1; }

    public double h(Object state) { return 0; }

    public static void main(String[] args) throws Exception {
        ProblemPancakes problem = new ProblemPancakes();
        //int[] pancakeArray = {1, 0, 3, 5, 2, 4};
        int[] pancakeArray = {1, 0, 3, 2};
        problem.initialState = new StatePancakes(pancakeArray); 

        Search search  = new Search(problem);

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
