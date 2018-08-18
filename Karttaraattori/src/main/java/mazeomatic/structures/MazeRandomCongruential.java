/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.structures;

import java.util.Random; // WE NEED TO REPLACE THIS WITH AN IMPLEMENTATION OF OUR OWN

/**
 *
 * @author jaakkovilenius
 */
public class MazeRandomCongruential implements MazeRandom {
    
    private Random random;
    
    public MazeRandomCongruential() {
        random = new Random();
    }

    @Override
    public int nextInt(int n) {
        return random.nextInt(n);
    }
    
}
