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
import org.json.simple.parser.ParseException;

import Model.Game;
import Model.Questions;
import Utils.DataType;
import Utils.Difficulty;
import Utils.JsonParser;

public class SysData {

	
	private static SysData SysData;
	private HashMap<Difficulty, ArrayList<Questions>> questions;
	private ArrayList<Game> games;
	private ArrayList<Game> pausedGames;
	private ArrayList<String> rules;
	private String quesJsonPath = "src/QuestionsFormat.txt"; 
	private String originalPath = quesJsonPath;


	public static SysData getInstance() {
		if (SysData == null)
			SysData = new SysData();
		return SysData;
	}

	//************************************** constructor*********************************************************************
	public SysData() {

		questions = new HashMap<Difficulty, ArrayList<Questions>>();
		games = new ArrayList<Game>();
//		pausedGames = new ArrayList<Game>();
		rules = new ArrayList<>();
		Game g = new Game("Hatooom",1,0,3,0,0,0,0);
		games.add(g);
	}

	//*********************************getters and setters********************************************************************
	public HashMap<Difficulty, ArrayList<Questions>> getQuestions() {
		return questions;
	}

	public void setQuestions(HashMap<Difficulty, ArrayList<Questions>> Questions) {
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
	// *******************************************loadQuestions************************************************************************

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
				Questions questionToAdd = new Questions(text,a1,a2,a3,a4, correctAnswerNum, level, team);

				// Add the question to questions according to the question level.
				if (!questions.containsKey(questionToAdd.getLevel())) {
					questions.put(questionToAdd.getLevel(), new ArrayList<Questions>());
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
	
	
	private void resetPathToDefault() {// Helper method
		quesJsonPath = originalPath;
	}
	
	static Difficulty getQuestionLevel(int level) { // Helper method to define question's difficulty level
		if (level == 1)
			return Difficulty.EASY;
		else if (level == 2)
			return Difficulty.MEDIUM;
		else if (level == 3)
			return Difficulty.HARD;

		return Difficulty.MEDIUM;
	}
	
	
	//************************************************add Question****************************************************************************
		public void addQuestion(Questions question) {
			ArrayList<Questions> myArray = questions.get(question.getLevel());
			if (myArray == null) {
				myArray = new ArrayList<Questions>();
				myArray.add(question);
			} else if (!myArray.contains(question)) {
				myArray.add(question);
			}
			questions.put(question.getLevel(), myArray);

		}

		//************************************************remove question***************************************************************************
		public boolean removeQuestion(Questions question) {
			ArrayList<Questions> myArray = questions.get(question.getLevel());
			if (myArray.contains(question)) {
				questions.get(question.getLevel()).remove(question);
				return true;
			}
			return false;
		}

		//*********************************************Edit Question******************************************************************************
		public boolean editQuestion(Questions question, Questions newQuestion) {
			if (removeQuestion(question)) {
				addQuestion(newQuestion);
				return true;
			}
			return false;
		}
		//***********************************************popQuestion*****************************************************************************
		public Questions popQuestion() {
			Object[] diff = questions.keySet().toArray();
			Difficulty key = (Difficulty) diff[new Random().nextInt(diff.length)];
			ArrayList<Questions> myArray = questions.get(key);
			Questions q = myArray.get(new Random().nextInt(myArray.size()));
			return q;
		}
	
	
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
