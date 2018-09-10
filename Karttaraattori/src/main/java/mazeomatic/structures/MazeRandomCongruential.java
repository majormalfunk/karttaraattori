/*
* Jaakko Vilenius 2018
*/

package mazeomatic.structures;

/**
 * Class to create pseudorandom numbers for placing rooms in the map.
 * This follows the Microsoft implementation at: https://rosettacode.org/wiki/Linear_congruential_generator
 * 
 * @author jaakkovilenius
 */
public class MazeRandomCongruential implements MazeRandom {
        
    long seed;
    final static long MULTIPLIER = 214013;
    final static long ADDER = 2531011;
    final static long DIVIDER = 65536;
    
    /**
     * Constructor
     */
    public MazeRandomCongruential() {
        
        seed = System.currentTimeMillis();
        seed = seed % (long)Integer.MAX_VALUE;
        
    }

    /**
     * Return the next int with excluded upper boundary
     * @param n upper boundary
     * @return new randow int
     */
    @Override
    public int nextInt(int n) {
        
        seed = (seed * MULTIPLIER + ADDER) % (long)Integer.MAX_VALUE;
        return (int) (seed / DIVIDER) % n;
        
    }
    
}
