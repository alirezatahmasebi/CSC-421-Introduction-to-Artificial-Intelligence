import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProblemWolfGoatCabbage extends Problem {
    Map<String, Map<String, Double>> map;
    
    public Object goalState;

    public ProblemWolfGoatCabbage(String mapfilename) throws Exception {
        map = new HashMap<String, Map<String, Double>>();
        //read map from file of source-destination-cost triples (comma separated)
        BufferedReader reader = new BufferedReader( new FileReader (mapfilename));
        String line;
        while( ( line = reader.readLine() ) != null ) {
            String[] strA = line.split("[,]", 0);
            
            String  left_side  = strA[0], 
                    right_side = strA[1];
            Double  cost       = Double.parseDouble(strA[2]);

            if(!map.containsKey(left_side)) 
                map.put(left_side, new HashMap<String, Double>());
            map.get(left_side).put(right_side,cost);

            //putting the reverse edge as well
            if(!map.containsKey(right_side)) 
                map.put(right_side, new HashMap<String, Double>());
            map.get(right_side).put(left_side,cost);
        }
        reader.close();
    }

    boolean goal_test(Object state) {
        return state.equals(goalState);
    }

    Set<Object> getSuccessors(Object state) {
        Set<Object> result = new HashSet<Object>();
        for(Object successor_state : map.get(state).keySet())
            result.add(successor_state);
        return result;
    }

    double step_cost(Object fromState, Object toState) {
        return map.get(fromState).get(toState);
    }

    public double h(Object state) { return 0; }

    public static void main(String[] args) throws Exception {
        ProblemWolfGoatCabbage problem = new ProblemWolfGoatCabbage("WolfGoatCabbage.txt");
        problem.initialState = "Left:PWGC-Right:Nothing";
        problem.goalState = "Left:Nothing-Right:PWGC";

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
