package Controller;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.omg.CORBA.IRObject;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
//import org.json.simple.parser.ParseException;

import Model.Game;
import Model.Question;
import Utils.Difficulty;

/*@authors Hatem and Moran*/
public class SysData {

	
	private static SysData SysData;
	// hashmap to save all the questions each questions have a key which is its difficulty level
	private HashMap<Difficulty, ArrayList<Question>> questions;
	// list for all the games
	private ArrayList<Game> games;

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

	// load Questions from the JSON file 
	@SuppressWarnings("unchecked")
	public boolean loadQuestions(String path) {

		if(path != null) {
		JSONParser parser = new JSONParser();

		try {
			// get question's JSON file
			FileInputStream fis = new FileInputStream(path);

			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			
			Object obj = parser.parse(reader);
			JSONObject jo = (JSONObject) obj;

			// convert question's JSON file to array .
			JSONArray quesArray = (JSONArray) jo.get("questions");
			
			//if the JSON file is empty and there is no questions
			if(Objects.isNull(quesArray)) {
				return false;
			}

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
				Difficulty level = getQuestionLevel(q.get("level").toString());

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
					//checking if the question already exits to make sure that there is no duplicates in questions
					if(!questions.get(questionToAdd.getLevel()).contains(questionToAdd)){
						questions.get(questionToAdd.getLevel()).add(questionToAdd);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		}
		return false;
	}
	

	// Helper method to define question's difficulty level
	public Difficulty getQuestionLevel(String level) { 
	
		if (level.equals("EASY"))
			return Difficulty.EASY;
		else if (level.equals("MEDIUM"))
			return Difficulty.MEDIUM;
		else if (level.equals("HARD"))
			return Difficulty.HARD;
		// Default difficulty for the question are MEDIUM
		return Difficulty.MEDIUM;
	}
	
	
	//add Question to the questions array
			public boolean addQuestion(Question question) {
				
				if(Objects.isNull(question)) {
					return false;
				}
				//checking if nothing is null in the question object
				if(question.getQuestion()!=null&&question.getAnswer1()!=null&&question.getAnswer2()!=null&&question.getAnswer3()!=null&&question.getAnswer4()!=null&&question.getCorrect_ans()!=null&&question.getLevel()!=null&&question.getTeam()!=null) {
					
					
					ArrayList<Question> myArray = questions.get(question.getLevel());
					if (myArray == null) {
						myArray = new ArrayList<Question>();
						myArray.add(question);
					} else if (!myArray.contains(question)) {
							myArray.add(question);
						
						}
					else {
						return false;
					}
						questions.put(question.getLevel(), myArray);
						return true;
				}
				else {
					return false;
				}
			}

			//deleting a question from our records
			public boolean removeQuestion(Question question) {
				if(question!=null) {
				
					ArrayList<Question> myArray = questions.get(question.getLevel());
					if (myArray.contains(question)) {
						questions.get(question.getLevel()).remove(question);
						return true;
					}
				}
				return false;
			}

			//Edit/modifying a question by deleting the older version of it and adding a new question to list
			public boolean editQuestion(Question question, Question newQuestion) {
				if(newQuestion!=null) {

					if(!questions.get(newQuestion.getLevel()).contains(newQuestion)) {
				
						if (removeQuestion(question)) {
							
							addQuestion(newQuestion);
							return true;
						}
					}
				}
				
				return false;
			}
			
			
			//Popping a random question by getting a random difficulty and after that a random questions from that level we choose
			public Question popQuestion(Difficulty level) {
				ArrayList<Question> myArray = questions.get(level);
				Question q = myArray.get(new Random().nextInt(myArray.size()));
				return q;
			}
		
			// loading the history data from the JSON file
			public boolean loadData() {
				
			games.clear();
			JSONParser parser = new JSONParser();

			try {
				// get games JSON file
				FileInputStream fis = new FileInputStream("src/historyGames.txt");

				BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
				Object obj = parser.parse(reader);
				JSONObject jo = (JSONObject) obj;

				// convert games JSON file to array .
				JSONArray quesArray = (JSONArray) jo.get("games");
				
				

				// iterate over the values (games).
				Iterator<JSONObject> quesIterator = quesArray.iterator();
				// get the games data.
				while (quesIterator.hasNext()) {

					JSONObject q = quesIterator.next();

					
					String nickname = (String) q.get("nickname");

					
					int level = Math.toIntExact( (long) q.get("level"));


					int score =  Math.toIntExact( (long) q.get("score"));

					int live =  Math.toIntExact( (long)q.get("live"));

					int totalQuest =  Math.toIntExact( (long) q.get("totalQuest"));
					int correctquesteasy =  Math.toIntExact( (long)q.get("correctquesteasy"));
					int correctquestmedium =  Math.toIntExact( (long) q.get("correctquestmedium"));
					int correctquesthard =  Math.toIntExact( (long) q.get("correctquesthard"));

					
					Game gameToAdd=new Game(nickname,level,score,live,totalQuest,correctquesteasy,correctquestmedium,correctquesthard);
					//avoiding duplicates
					if(games!=null) {
						if(!games.contains(gameToAdd)) {
							games.add(gameToAdd);
						}
					}
					else games.add(gameToAdd);


				}

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
			
			
			// writing data to a json file 
			// the flag parameter indicates either we want to write a history file or questions file...
			public Boolean writeJSON(Boolean flag){
				// flag = true ---> History/Games
				// flag = false ---> Question

				//Game
				if(flag){
					try {
					JSONObject JsonObject = new JSONObject();
					JSONArray JsonArray = new JSONArray();

					for (Game g : this.games) {
						Map<String, Object> map = new LinkedHashMap<String, Object>(8);
						map.put("nickname", g.getNickname());
						map.put("level", g.getLevel());
						map.put("score", g.getScore());
						map.put("live", g.getLive());
						map.put("totalQuest", g.getTotalQuest());
						map.put("correctquesteasy", g.getCorrectquesteasy());
						map.put("correctquestmedium", g.getCorrectquestmedium());
						map.put("correctquesthard", g.getCorrectquesthard());


						JsonArray.add(map);
					}
					JsonObject.put("games", JsonArray);
					PrintWriter pw = new PrintWriter("src/historyGames.txt");
					pw.write(JsonObject.toJSONString());
					pw.flush();
					pw.close();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return false;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				return true;
				}
				//Question
				else{
					try {
					JSONObject JsonObject = new JSONObject();
					JSONArray JsonArray = new JSONArray();
					
					for(Difficulty key : questions.keySet()){
						for(Question qq : questions.get(key)) {
							Map<String, Object> map = new LinkedHashMap<String, Object>(5);
							map.put("question", qq.getQuestion());
							ArrayList<String> array=new ArrayList<String>();
							array.add((String) qq.getAnswer1());
							array.add((String) qq.getAnswer2());
							array.add((String) qq.getAnswer3());
							array.add((String) qq.getAnswer4());
							map.put("answers", array);
							map.put("correct_ans", (String) qq.getCorrect_ans());
							map.put("level",  (String) qq.getLevel().toString());
							map.put("team", (String) qq.getTeam());


							JsonArray.add(map);
							
						}
					}
					JsonObject.put("questions",  JsonArray);

					PrintWriter pw = new PrintWriter("src/QuestionsFormat.txt");
					pw.write(JsonObject.toJSONString());
					pw.flush();
					pw.close();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return false;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
					
				return true;
				
				}
			} 
		
		
			public void observableMethod() {
				writeJSON(false);
				loadQuestions("src/QuestionsFormat.txt");
			}
			
			public void observableMethodForGame() {
				writeJSON(true);
			}
	

	


	
}