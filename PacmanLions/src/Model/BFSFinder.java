package Model;

import java.awt.*;
import java.util.*;

import Utils.moveType;

/*@authors Nahawand and Grace*/
//Finds Path Between two Maze Points Using Breadth-Frist Search (BFS)
public class BFSFinder {

    int[][] map;
    int mx;//ghost position x
    int my;//ghost position y

    public BFSFinder(Ghost pb){
        this.mx = pb.pos_currx; //stores curr ghost pos x
        this.my = pb.pos_curry; //stores cutt ghost pos y
        //init BFS map
        
        map = new int[pb.pos_currx][pb.pos_curry]; //inits at current pos
       
        for(int ii=0;ii<pb.pos_curry;ii++){
            for(int jj=0;jj<pb.pos_currx;jj++){
                if(pb.mazee[jj/30][ii/30]>1 && pb.mazee[jj/30][ii/30]<26){
                    map[jj][ii] = 1;
                }else{
                    map[jj][ii] = 0;
                }
            }
        }
        
    }

    private class MazeCell {
        int x;
        int y;
        boolean isVisited;

        public MazeCell(int x, int y) {
            this.x = x;
            this.y = y;
            isVisited =false;
        }

        public String toString() {
            return "x = " + x + " y = " + y;
        }
    }

    private boolean isValid(int i,int j,boolean[][] markMat) {
        return (i>=0 && i<mx && j>=0 && j<my && map[i][j]==0 && !markMat[i][j]);
    }

    //Construct Parentship LinkedList
    public moveType getMove(int x, int y,int tx,int ty) {

        //already reached
        if(x==tx && y==ty){
            return moveType.NONE;
        }

        System.out.println("FINDING PATH FROM : " + x + "," + y + " TO " + tx + "," + ty);

        MazeCell[][] mazeCellTable = new MazeCell[mx][my];
        Point[][] parentTable = new Point[mx][my];
        boolean[][] markMat = new boolean[mx][my];
       

        for (int ii = 0; ii < mx; ii++) {
            for (int jj = 0; jj < my; jj++) {
                markMat[ii][jj] = false;
            }
        }
        
        MazeCell[] Q = new MazeCell[2000];
        int size = 1;

        MazeCell start = new MazeCell(x, y);
        
        System.out.println("MazeCell "+mazeCellTable+" start "+start);
        System.out.println(""+mazeCellTable[x-1][y-1]);
        mazeCellTable[x][y] = start;
        
        Q[0] = start;
        markMat[x][y] = true;

        for (int k = 0; k < size; k++) {
            int i = Q[k].x;
            int j = Q[k].y;
            //RIGHT
            if (isValid(i + 1, j, markMat)) {
                MazeCell m = new MazeCell(i + 1, j);
                mazeCellTable[i + 1][j] = m;
                Q[size] = m;
                size++;
                markMat[i + 1][j] = true;
                parentTable[i + 1][j] = new Point(i, j);
            }
            //LEFT
            if (isValid(i - 1, j, markMat)) {
                MazeCell m = new MazeCell(i - 1, j);
                mazeCellTable[i - 1][j] = m;
                Q[size] = m;
                size++;
                markMat[i - 1][j] = true;
                parentTable[i - 1][j] = new Point(i, j);
            }
            //UP
            if (isValid(i, j - 1, markMat)) {
                MazeCell m = new MazeCell(i, j - 1);
                mazeCellTable[i][j - 1] = m;
                Q[size] = m;
                size++;
                markMat[i][j - 1] = true;
                parentTable[i][j - 1] = new Point(i, j);
            }
            //DOWN
            if (isValid(i, j + 1, markMat)) {
                MazeCell m = new MazeCell(i, j + 1);
                mazeCellTable[i][j + 1] = m;
                Q[size] = m;
                size++;
                markMat[i][j + 1] = true;
                parentTable[i][j + 1] = new Point(i, j);
            }
        }

        int ttx = tx;
        int tty = ty;
        MazeCell t = mazeCellTable[ttx][tty];
        MazeCell tl = null;
        while (t != start) {
            Point tp = parentTable[ttx][tty];
            ttx = tp.x;
            tty = tp.y;
            tl = t;
            t = mazeCellTable[ttx][tty];
        }

        if (x == tl.x - 1 && y == tl.y) {
            return moveType.RIGHT;
        }
        if (x == tl.x + 1 && y == tl.y) {
            return moveType.LEFT;
        }
        if (x == tl.x && y == tl.y - 1) {
            return moveType.DOWN;
        }
        if (x == tl.x && y == tl.y + 1) {
            return moveType.UP;
        }
        return moveType.NONE;
    }

}