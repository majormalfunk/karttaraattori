/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.structures;

/**
 *
 * @author jaakkovilenius
 */
public class MazeRandomMock implements MazeRandom {

    private int[] fakeRandom;
    private int counter;
    
    public MazeRandomMock() {
        
        fakeRandom = new int[]{0, 0, 4, 4};
        counter = 0;

    }
    
    
    @Override
    public int nextInt(int n) {
        int ret = fakeRandom[counter];
        counter++;
        if (counter == fakeRandom.length) {
            counter = 0;
        }
        return ret;
    }

}
