/*
 * For any game, the stateT structure records the current state
 * of the game.  For Nim, the main component of the
 * state record is the board, which is an int and whose player it is, 0 and 1  for players and computer
 */


public class StateNim extends State {
	
    public int board;
    
    public StateNim() {
    	board = 13;
        player = 1;
    }
    
    public StateNim(StateNim state) {
    	
        this.board = state.board; 
        player = state.player;
    }
    
    public String toString() {
    	
    	String ret = "";
    	ret+= board+"\n";
    	return ret;
    }
}
