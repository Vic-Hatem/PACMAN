package Controller;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Game;
import Model.Ghost;
import Model.Maze;
import Model.Pacman;
import Model.Question;
import Utils.Difficulty;

public class MainController {

	
	public static void main(String[] args) {
		
//		SysData.getInstance().loadQuestions(null);
//		System.out.println(SysData.getInstance().getQuestions());
		System.out.println("~~ Welcome to PACMAN ~~");
//		initSysData();
//		Maze maze = new Maze();
//		ArrayList<Game> ga=new ArrayList<Game>();
//		ga.add(new Game("jghcgc", 0, 0, 0, 0, 0, 0, 0));
//		ga.add(new Game("0907990", 4, 4, 0, 0, 0, 0, 0));
//		ga.add(new Game("#########", 4, 4, 0, 0, 0, 0, 0));
//		ArrayList<Question> qa=new ArrayList<Question>();
//		Question q=new Question("234234234", "11111111", "22222", "vfdvfdv", "2", "2",Difficulty.MEDIUM, "LIONs");
//
//		Question q1=new Question("ssssssssssss", "00000000", "66666666", "vfdvfdv", "2", "2",Difficulty.EASY, "LIONs");
//
//
//		Question q2=new Question("dfbdfdfb", "dfvdfvdfv", "fdfvdfv", "vfdvfdv", "2", "2",Difficulty.HARD, "LIONs");
//		
//		
//		qa.add(q);
//		qa.add(q1);
//
//		qa.add(q2);
//		HashMap<Difficulty, ArrayList<Question>> questions=new HashMap<Difficulty, ArrayList<Question>>();
//		questions.put(q.getLevel(), new ArrayList<Question>());
//		questions.put(q1.getLevel(), new ArrayList<Question>());
//		questions.put(q2.getLevel(), new ArrayList<Question>());
//
//		questions.get(q.getLevel()).add(q);
//		questions.get(q1.getLevel()).add(q1);
//
//		questions.get(q2.getLevel()).add(q2);
//
//		SysData.getInstance().loadQuestions();
//
//		//SysData.getInstance().setQuestions(questions);
//		SysData.getInstance().writeJSON(false);
//		SysData.getInstance().loadQuestions();
//		System.out.println(SysData.getInstance().getQuestions());

		
	

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
