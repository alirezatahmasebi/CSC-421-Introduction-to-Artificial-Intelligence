import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
public class GameNim extends Game{
    int winningState = 0;

    int WinningScore = 10;
    int LosingScore = -10;
    int NeutralScore = 0;
    
    public GameNim() {
    	currentState = new StateNim();
    }
    
    public boolean isWinState(State state)
    {
        StateNim tstate = (StateNim) state;     
            if (tstate.board==winningState) { 
            	return true;
            }
        return false;
    }
    
    public boolean isStuckState(State state) {
    
        return false;
    }
	
	
	public Set<State> getSuccessors(State state)
    {
		if(isWinState(state) || isStuckState(state))
			return null;
		
		Set<State> successors = new HashSet<State>();
        StateNim tstate = (StateNim) state;
        
        StateNim successor_state;
        
        for (int i = 1; i <= 3; i++) {
            if (tstate.board != winningState && tstate.board - i >= 0) {
                successor_state = new StateNim(tstate);
                successor_state.board -= i;
                successor_state.player = (state.player==0 ? 1 : 0); 
                
                successors.add(successor_state);
            }
        }
    
        return successors;
    }	
    
    public double eval(State state) 
    {   
    	if(isWinState(state)) {
    		//player who made last move
    		int previous_player = (state.player==0 ? 1 : 0);
    	
	    	if (previous_player==0) //Player wins
	            return LosingScore; 
	    	else //Computer wins
	            return WinningScore;
    	}

        return NeutralScore;
    }
    
    
    public static void main(String[] args) throws Exception {
        
        Game game = new GameNim(); 
        Search search = new Search(game);
        int depth = 13;
        
        //needed to get human's move
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("There are 13 coins");
        while (true) {
        	
        	StateNim 	nextState = null;
        	
            switch ( game.currentState.player ) {
              case 1: //Human
                  
            	  //get human's move
                  System.out.print("Enter your *valid* move from 1-3 > ");
                  int val = Integer.parseInt( in.readLine() );
            	  if (val <=3){
                    nextState = new StateNim((StateNim)game.currentState);
                    nextState.player = 1;
                    nextState.board -= val;
                    System.out.print("Coins remaining after Human's turn: " + nextState);
                    break;
                  }
                  
              case 0: //Computer
            	  
            	  nextState = (StateNim)search.bestSuccessorState(depth);
            	  nextState.player = 0;
            	  System.out.print("Coins remaining after Computer's turn: " + nextState);
                  break;
            }
                        
            game.currentState = nextState;
            //change player
            game.currentState.player = (game.currentState.player==0 ? 1 : 0);
            
            //Who wins?
            if ( game.isWinState(game.currentState) ) {
            
            	if (game.currentState.player == 1) //i.e. last move was by the computer
            		System.out.println("You win!");
            	else
            		System.out.println("Computer wins!");
            	
            	break;
            }
        }
    }
}
