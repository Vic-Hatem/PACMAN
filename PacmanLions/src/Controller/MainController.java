package Controller;

import java.util.ArrayList;

import Model.Game;
import Model.Ghost;
import Model.Maze;
import Model.Pacman;

/*@authors Hatem, Moran, Nahawand and Grace*/

public class MainController {

	
	public static void main(String[] args) {
		
		
		System.out.println("~~ Welcome to PACMAN ~~");

	}

	private static void initSysData() {
		// TODO Auto-generated method stub
	}

	private static void printMaze(Maze maze1) {
		// TODO Auto-generated method stub
		int [][]maze = maze1.getMaze();
		int i,j;
		for(i=0 ; i<maze.length ; i++) {
			for(j=0 ; j<maze.length ; j++) {
				
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();

		}
	}
	

	
}
