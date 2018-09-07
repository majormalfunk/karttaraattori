/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.structures;

/**
 * Class to cfeate pseudorandom numbers for placing rooms in the map.
 * This follows the Microsoft implementation at: https://rosettacode.org/wiki/Linear_congruential_generator
 * 
 * @author jaakkovilenius
 */
public class MazeRandomCongruential implements MazeRandom {
        
    long seed;
    final static long MULTIPLIER = 214013;
    final static long ADDER = 2531011;
    final static long DIVIDER = 65536;
    
    
    public MazeRandomCongruential() {
        
        seed = System.currentTimeMillis();
        seed = seed % (long)Integer.MAX_VALUE;
        
    }

    @Override
    public int nextInt(int n) {
        
        seed = (seed * MULTIPLIER + ADDER) % (long)Integer.MAX_VALUE;
        return (int) (seed / DIVIDER) % n;
        
    }
    
}
