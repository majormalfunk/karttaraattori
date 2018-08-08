/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.logic;

import java.util.Random; // WE NEED TO REPLACE THIS WITH AN IMPLEMENTATION OF OUR OWN

/**
 * This class represents the maze. It is basically a 2D int array. At this point
 * 0 means no wall and 1 means a wall.
 *
 * @author jaakkovilenius
 */
public class Maze {

    int width;
    int height;
    int rooms;

    public int[][] map;
    
    private Random random;
    
    public Node[] roomNodes;
    
    

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
        
        random = new Random();
        
        roomNodes = new Node[rooms];

    }

    /**
     * This function fills the 2D array representing the maze
     */
    public void fillMaze() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = 1;
            }
        }
        for (int k = 0; k < roomNodes.length; k++) {
            Node node = roomNodes[k];
            for (int l = node.x-1; l < node.x+2; l++) {
                for (int m = node.y-1; m < node.y+2; m++) {
                    map[l][m] = node.type;
                }
            }
        }

    }
    
    /**
     * This function builds a list of room nodes
     */
    public void placeRooms() {
        System.out.println("Placing rooms");
        for (int i = 0; i < rooms; i++) {
            int roomX = random.nextInt(width-4)+2;
            int roomY = random.nextInt(height-4)+2;
            Node room = new Node(roomX, roomY, 0);
            System.out.println("Room @ " + roomX + "," + roomY);
            roomNodes[i] = room;
        }
        
    }

    /**
     * This function returns the element at the given coordinates
     *
     * @param width The width coordinate
     * @param height The height coordinate
     * @return Value of the element at coordinates (0 = no wall, 1 = wall)
     */
    public int elementAt(int width, int height) {
        return map[width][height];
    }

}
