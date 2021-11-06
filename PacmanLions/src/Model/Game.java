package Model;

public class Game {
	
	private String Nickname;
	private int level;
	private int score;
	private int live;
	private int totalQuest;    
	private int correctquesteasy; 
	private int correctquestmedium;
	private int correctquesthard;

	
	
	/**
	 * @param nickname 
	 * @param level
	 * @param score
	 * @param live
	 * @param totalQuest
	 * @param correctquesteasy
	 * @param correctquestmedium
	 * @param correctquesthard
	 */
	public Game(String nickname, int level, int score, int live, int totalQuest, int correctquesteasy,
			int correctquestmedium, int correctquesthard) {
		super();
		Nickname = nickname;
		this.level = level;
		this.score = score;
		this.live = live;
		this.totalQuest = totalQuest;
		this.correctquesteasy = correctquesteasy;
		this.correctquestmedium = correctquestmedium;
		this.correctquesthard = correctquesthard;
	}
	
	public Game() {
		
	}
	
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLive() {
		return live;
	}
	public void setLive(int live) {
		this.live = live;
	}
	public int getTotalQuest() {
		return totalQuest;
	}
	public void setTotalQuest(int totalQuest) {
		this.totalQuest = totalQuest;
	}
	public int getCorrectquesteasy() {
		return correctquesteasy;
	}
	public void setCorrectquesteasy(int correctquesteasy) {
		this.correctquesteasy = correctquesteasy;
	}
	public int getCorrectquestmedium() {
		return correctquestmedium;
	}
	public void setCorrectquestmedium(int correctquestmedium) {
		this.correctquestmedium = correctquestmedium;
	}
	public int getCorrectquesthard() {
		return correctquesthard;
	}
	public void setCorrectquesthard(int correctquesthard) {
		this.correctquesthard = correctquesthard;
	}

}
