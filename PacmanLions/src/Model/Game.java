package Model;

/*@authors Hatem, Moran, Nahawand and Grace*/
public class Game {
	
	//player nickname in the game
	private String Nickname;
	private int level;
	private int score=1;
	private int live;
	// questions answered in total
	private int totalQuest; 
	// easy questions answered correctly by the player
	private int correctquesteasy; 
	// medium questions answered correctly by the player
	private int correctquestmedium;
	// hard questions answered correctly by the player
	private int correctquesthard;
	// total questions answered correctly by the player
	private int totalCorrectAnswers=0;
	
	//gameover is false as long as lives > 1
	public boolean gameover;
	// gamestarted sets game started to true when we start a new game
	public boolean gamestarted;
	String AnsRatio;
	
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
		this.Nickname = nickname;
		this.level = level;
		this.score = score;
		this.live = live;
		this.totalQuest = totalQuest;
		this.correctquesteasy = correctquesteasy;
		this.correctquestmedium = correctquestmedium;
		this.correctquesthard = correctquesthard;
		System.out.println("Game Started- \nNickName: "+nickname+"Level: "+level);
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
	public void addScore(int score) {
		this.score = this.score + score;
	}
	public void setScore(int sc) 
	{
		this.score = sc;
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
	public int getTotalCorrectAnswer() 
	{
		return totalCorrectAnswers;
	}
	public void setTotalCorrectAnswer(int CorctAnswers) 
	{
		totalCorrectAnswers = CorctAnswers;
	}
	@Override
	public String toString() {
		AnsRatio = ""+totalCorrectAnswers+"/"+ totalQuest+"";
		return "Game [Nickname=" + Nickname + ", level=" + level + ", score=" + score + ", live=" + live
				+ ", totalQuest=" +AnsRatio+", correctquesteasy=" + correctquesteasy + ", correctquestmedium="
				+ correctquestmedium + ", correctquesthard=" + correctquesthard + "]";
	}

}
