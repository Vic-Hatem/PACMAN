package Controller;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import Model.Game;
import Model.Question;
import Utils.DataType;
import Utils.Difficulty;
import Utils.JsonParser;

public class SysData {

	
	private static SysData SysData;
	// hashmap to save all the questions each questions have a key which is its difficulty level
	private HashMap<Difficulty, ArrayList<Question>> questions;
	// list for all the games
	private ArrayList<Game> games;
	private ArrayList<Game> pausedGames;
	// all the game rules as a string array
	private ArrayList<String> rules;
	private String quesJsonPath = "src/QuestionsFormat.txt"; 
	private String originalPath = quesJsonPath;

	// SINGELTON
	public static SysData getInstance() {
		if (SysData == null)
			SysData = new SysData();
		return SysData;
	}

	//constructor
	public SysData() {

		questions = new HashMap<Difficulty, ArrayList<Question>>();
		games = new ArrayList<Game>();
//		pausedGames = new ArrayList<Game>();
		rules = new ArrayList<>();
		Game g = new Game("Hatooom",1,0,3,0,0,0,0);
		games.add(g);
	}

	//getters and setters
	public HashMap<Difficulty, ArrayList<Question>> getQuestions() {
		return questions;
	}

	public void setQuestions(HashMap<Difficulty, ArrayList<Question>> Questions) {
		questions = Questions;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> Games) {
		games = Games;
	}

//	public ArrayList<Game> getPausedGames() {
//		return pausedGames;
//	}

//	public void setPausedGames(ArrayList<Game> PausedGames) {
//		pausedGames = PausedGames;
//	}

	public ArrayList<String> getRules() {
		return rules;
	}

	public void setRules(ArrayList<String> rules) {
		this.rules = rules;
	}
	// load Questions from the JSON file 
	@SuppressWarnings("unchecked")
	public boolean loadQuestions(String externalPath) {

		if (externalPath != null) {
			quesJsonPath = externalPath;
		}
		// Logger.log("Reading questions form path: " + quesJsonPath);
		JSONParser parser = new JSONParser();

		try {
			// get question's JSON file
			FileInputStream fis = new FileInputStream(originalPath);

			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			Object obj = parser.parse(reader);
			JSONObject jo = (JSONObject) obj;

			// convert question's JSON file to array .
			JSONArray quesArray = (JSONArray) jo.get("questions");

			// iterate over the values (questions).
			Iterator<JSONObject> quesIterator = quesArray.iterator();
			// get the questions data.
			while (quesIterator.hasNext()) {

				JSONObject q = quesIterator.next();

				// get question's content
				String text = (String) q.get("question");

				// get correct answer's number.
				String correctAnswerNum = (String) q.get("correct_ans");

				// get question's difficulty level.
				Difficulty level = getQuestionLevel(Integer.valueOf(q.get("level").toString()));

				// get question's created team name.
				 String team = (String) q.get("team");
			
				// get question's answers.
				JSONArray ansArray = (JSONArray) q.get("answers");

					String a1 = (String) ansArray.get(0);
					String a2 = (String) ansArray.get(1);
					String a3 = (String) ansArray.get(2);
					String a4 = (String) ansArray.get(3);

				// create an new object of the question.
				Question questionToAdd = new Question(text,a1,a2,a3,a4, correctAnswerNum, level, team);

				// Add the question to questions according to the question level.
				if (!questions.containsKey(questionToAdd.getLevel())) {
					questions.put(questionToAdd.getLevel(), new ArrayList<Question>());
					questions.get(questionToAdd.getLevel()).add(questionToAdd);

				} else {
					questions.get(questionToAdd.getLevel()).add(questionToAdd);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			resetPathToDefault();
			return false;
		}
		resetPathToDefault();
		return true;
	}
	
	// Helper method
	private void resetPathToDefault() {
		quesJsonPath = originalPath;
	}
	
	// Helper method to define question's difficulty level
	static Difficulty getQuestionLevel(int level) { 
		if (level == 1)
			return Difficulty.EASY;
		else if (level == 2)
			return Difficulty.MEDIUM;
		else if (level == 3)
			return Difficulty.HARD;

		return Difficulty.MEDIUM;
	}
	
	
	//add Question to the questions array
		public boolean addQuestion(Question question) {
			ArrayList<Question> myArray = questions.get(question.getLevel());
			if (myArray == null) {
				myArray = new ArrayList<Question>();
				myArray.add(question);
			} else if (!myArray.contains(question)) {
				myArray.add(question);
			}
			questions.put(question.getLevel(), myArray);
			return true;
		}

		//deleting a question from our records
		public boolean removeQuestion(Question question) {
			ArrayList<Question> myArray = questions.get(question.getLevel());
			if (myArray.contains(question)) {
				questions.get(question.getLevel()).remove(question);
				return true;
			}
			return false;
		}

		//Edit/modifing a question by deleteing the older version of it and adding a new question to list
		public boolean editQuestion(Question question, Question newQuestion) {
			if (removeQuestion(question)) {
				addQuestion(newQuestion);
				return true;
			}
			return false;
		}
		//poping a random question by getting a random difficutly and after that a random questions from that level we choose
		public Question popQuestion() {
			Object[] diff = questions.keySet().toArray();
			Difficulty key = (Difficulty) diff[new Random().nextInt(diff.length)];
			ArrayList<Question> myArray = questions.get(key);
			Question q = myArray.get(new Random().nextInt(myArray.size()));
			return q;
		}
	
		// loading the data from the JSON files by checking if the game stopped/paused or finished 
		public boolean loadData(DataType type) {

			if (type == null)
				return false;
			try {
			// TODO Auto-generated method stub
			 if (type.equals(DataType.PAUSED_GAMES)) {
				String file = "src/pausedGames.txt";
				String json = readFileAsString(file);
				List<Game> games = JsonParser.getInstance().parseToList(json, new Game());
				if(games != null) {
					pausedGames.clear();
					pausedGames.addAll(games);
				}
				return true;
			}
			 else if (type.equals(DataType.HISTORY_GAMES)) {
				String file = "src/historyGames.txt";
				String json = readFileAsString(file);
				System.out.println("the json parameter");
				System.out.println(json);
				List<Game>gamesHistory = JsonParser.getInstance().parseToList(json, new Game("Hatooom",1,0,3,0,0,0,0));
				System.out.println("im in the list of the games");
				System.out.println(gamesHistory);
				if(gamesHistory != null) {
					this.games.clear();
					this.games.addAll(gamesHistory);
				}
				return true;
			}
			else
				return false;
			} 
			 catch (Exception e) {
					e.printStackTrace();
					resetPathToDefault();
					return false;
			 }
		} 
		
		//writing the data to store it
		public boolean writeData(DataType type)
		{
			if(type==null)
				return false;
			FileWriter writer = null;

			try {
			   if(type.equals(DataType.PAUSED_GAMES))
				{
					String filePath = "src/pausedGames.txt";
					writer = new FileWriter(filePath);
					String parsedListToJson = JsonParser.getInstance().parseListToJsonArray(pausedGames, new Game());
					writer.write(parsedListToJson);
					return true;
				}
				else if(type.equals(DataType.HISTORY_GAMES))
				{
					System.out.println("im in the write Data");
					String filePath = "src/historyGames.txt";
					writer = new FileWriter(filePath);
					System.out.println(games + "this is games hatemmmmmmmm");
					String parsedListToJson = JsonParser.getInstance().parseListToJsonArray(games, new Game());
					System.out.println(parsedListToJson + "here here here here ");
					writer.write(parsedListToJson);
					return true;
				}
				else 
					return false;

			}catch (Exception e) {
				e.printStackTrace();
				resetPathToDefault();
				return false;
			} finally {
				try {
					writer.flush();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	
	public static String readFileAsString(String file) throws Exception {
		return new String(Files.readAllBytes(Paths.get(file)));
	}
	
	public void addPausedGame(Game game) {
		// TODO Auto-generated method stub
		if(!pausedGames.contains(game))
			pausedGames.add(game);
	}

	public void addFinishedGame(Game game) {
		if(!games.contains(game)) {
			games.add(game);
		}
	}


	
}
