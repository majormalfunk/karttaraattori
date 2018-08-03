/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.logic;

/**
 * This class represents the maze.
 * It is basically a 2D int array. At this point 0 means no wall and 1 means a wall.
 * 
 * @author jaakkovilenius
 */
public class Maze {
    
    int width;
    int height;
    int rooms;
    
    public int[][]map;
    
    /**
     * Constructor
     * 
     * @param width Width of the maze in grid blocks
     * @param height Height of the maze in grid blocks
     * @param rooms Number of rooms on the maze
     */
    public Maze(int width, int height, int rooms) {
        this.width = width;
        this.height = height;
        this.rooms = rooms;
        
        map = new int[width][height];
        
        fillMaze();
        
    }
    
    /**
     * This function fills the 2D array representing the maze
     */
    private void fillMaze() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = elementAt(i, j);
            }
        }

    }
    
    /**
     * This function returns the element at the given coordinates
     * 
     * @param width The width coordinate
     * @param height The height coordinate
     * @return Value of the element at coordinates (0 = no wall, 1 = wall)
     */
    private int elementAt(int width, int height) {
        return 1;
    }
    
}
