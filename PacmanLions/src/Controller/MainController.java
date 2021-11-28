package Controller;

import java.util.ArrayList;

import Model.Game;
import Model.Ghost;
import Model.Maze;
import Model.Pacman;
import Utils.DataType;

public class MainController {

	
	public static void main(String[] args) {
		
		
		System.out.println("~~ Welcome to PACMAN ~~");
//		initSysData();
//		Maze maze = new Maze();
//		ArrayList<Game> ga=new ArrayList<Game>();
//		ga.add(new Game("jghcgc", 0, 0, 0, 0, 0, 0, 0));
//		ga.add(new Game("0907990", 4, 4, 0, 0, 0, 0, 0));
//		ga.add(new Game("#########", 4, 4, 0, 0, 0, 0, 0));
		SysData.getInstance().loadQuestions("src/QuestionsFormat.txt");
		
	

//		printMaze(maze);
		
		
		
		
	}

	private static void initSysData() {
		// TODO Auto-generated method stub
//		SysData.getInstance().writeData(DataType.HISTORY_GAMES);
//		SysData.getInstance().loadData(DataType.HISTORY_GAMES);
//		System.out.println(SysData.getInstance().getGames());
//		SysData.getInstance().loadQuestions(null);
//		System.out.println(SysData.getInstance().getQuestions());
//		SysData.getInstance().loadData(DataType.PAUSED_GAMES);
		
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
