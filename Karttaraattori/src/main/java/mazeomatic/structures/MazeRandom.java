/*
* Jaakko Vilenius 2018
*/

package mazeomatic.structures;

/**
 * Interface for a Random generator
 * @author jaakkovilenius
 */
public interface MazeRandom {
    
    /**
     * Return the next int with excluded upper boundary
     * @param n upper boundary
     * @return new randow int
     */
    public int nextInt(int n);

}
    
