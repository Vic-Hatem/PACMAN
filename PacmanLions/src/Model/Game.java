package Model;

public class Game {
	
	private String Nickname;
	private int level;
	private int score;
	private int live;
	// number of questions answered in total
	private int totalQuest; 
	// number of easy answers the player answer them right   
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
	// contrsuctor
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

	// getters and setters
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

	@Override
	public String toString() {
		return "Game [Nickname=" + Nickname + ", level=" + level + ", score=" + score + ", live=" + live
				+ ", totalQuest=" + totalQuest + ", correctquesteasy=" + correctquesteasy + ", correctquestmedium="
				+ correctquestmedium + ", correctquesthard=" + correctquesthard + "]";
	}

}
