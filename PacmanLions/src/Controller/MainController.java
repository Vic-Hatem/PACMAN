package Controller;

import Model.Game;
import Model.Ghost;
import Model.Maze;
import Model.Pacman;
import Utils.DataType;

public class MainController {

	
	public static void main(String[] args) {
		
		
		System.out.println("~~ Welcome to PACMAN ~~");
		initSysData();
//		SysData sysdata = new SysData();
		Maze maze = new Maze();
		Game game = new Game("Hatooom",1,0,3,0,0,0,0);
		Pacman pacman = new Pacman(23,15,23,15,0);
		
		Ghost ghost1= new Ghost(20,15,20,15,0,0.0);
		Ghost ghost2= new Ghost(20,16,20,16,0,0.0);
		Ghost ghost3= new Ghost(20,17,20,17,0,0.0);
		

		printMaze(maze);
	}

	private static void initSysData() {
		// TODO Auto-generated method stub
		SysData.getInstance().writeData(DataType.HISTORY_GAMES);
		SysData.getInstance().loadData(DataType.HISTORY_GAMES);
		System.out.println(SysData.getInstance().getGames());
		SysData.getInstance().loadQuestions(null);
		System.out.println(SysData.getInstance().getQuestions());
		SysData.getInstance().loadData(DataType.PAUSED_GAMES);
		
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
