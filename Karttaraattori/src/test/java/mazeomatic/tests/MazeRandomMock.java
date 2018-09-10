/*
* Jaakko Vilenius 2018
*/

package mazeomatic.tests;

import mazeomatic.structures.MazeRandom;

/**
 *
 * @author jaakkovilenius
 */
public class MazeRandomMock implements MazeRandom {

    private int[] fakeRandom;
    private int counter;
    
    public MazeRandomMock() {
        
        fakeRandom = new int[]{17, 20, 24, 23};
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
