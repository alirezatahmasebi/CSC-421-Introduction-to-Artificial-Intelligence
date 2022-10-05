public class StatePancakes 
{    
    int N;
    int[] pancakeArray;

    public StatePancakes(int[] pancakeArray) { 
        this.pancakeArray = pancakeArray; 
        N = pancakeArray.length;
    }

    //It has to be a copy of values not reference because we will 
    //create many states and don't want to overwrite the same array.
    public StatePancakes(StatePancakes state) {
        N = state.N;
        pancakeArray = new int[N];

        for(int i=0; i<N; i++)
            pancakeArray[i] = state.pancakeArray[i];
    }

    public boolean equals(Object o) {
        return java.util.Arrays.equals( pancakeArray, ((StatePancakes) o).pancakeArray );
    }

    public int hashCode() {
        return java.util.Arrays.hashCode( pancakeArray );
    }    

    public String toString() {
        return java.util.Arrays.toString( pancakeArray );
    }
}