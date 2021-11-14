package Model;

import Utils.Difficulty;

public class Question {
	//for saving the question
	private String question ;

	private String answer1 ;
	private String answer2 ;
	private String answer3 ;
	private String answer4 ;
	// the number of the currect answer [1,2,3,4]
	private String correct_ans;
	// the questions level/difficulty {EASY , MEDIUM , HARD}
	Difficulty level;
	// the team that added the question
	private String team;
	
	
	
	/**
	 * @param question
	 * @param answer1
	 * @param answer2
	 * @param answer3
	 * @param answer4
	 * @param correct_ans
	 * @param level
	 * @param team
	 */

	// Constructor
	public Question(String question, String answer1, String answer2, String answer3, String answer4,
			String correct_ans, Difficulty level, String team) {
		super();
		this.question = question;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.correct_ans = correct_ans;
		this.level = level;
		this.team = team;
	}
	
	
// getters and setters		

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	
	
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	
	
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	
	
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	
	
	public String getCorrect_ans() {
		return correct_ans;
	}
	public void setCorrect_ans(String correct_ans) {
		this.correct_ans = correct_ans;
	}
	
	
	public Difficulty getLevel() {
		return level;
	}


	public void setLevel(Difficulty level) {
		this.level = level;
	}


	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	




	@Override
	public String toString() {
		return "Question [question=" + question + ", answer1=" + answer1 + ", answer2=" + answer2 + ", answer3="
				+ answer3 + ", answer4=" + answer4 + ", correct_ans=" + correct_ans + ", level=" + level + ", team="
				+ team + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer1 == null) ? 0 : answer1.hashCode());
		result = prime * result + ((answer2 == null) ? 0 : answer2.hashCode());
		result = prime * result + ((answer3 == null) ? 0 : answer3.hashCode());
		result = prime * result + ((answer4 == null) ? 0 : answer4.hashCode());
		result = prime * result + ((correct_ans == null) ? 0 : correct_ans.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (answer1 == null) {
			if (other.answer1 != null)
				return false;
		} else if (!answer1.equals(other.answer1))
			return false;
		if (answer2 == null) {
			if (other.answer2 != null)
				return false;
		} else if (!answer2.equals(other.answer2))
			return false;
		if (answer3 == null) {
			if (other.answer3 != null)
				return false;
		} else if (!answer3.equals(other.answer3))
			return false;
		if (answer4 == null) {
			if (other.answer4 != null)
				return false;
		} else if (!answer4.equals(other.answer4))
			return false;
		if (correct_ans == null) {
			if (other.correct_ans != null)
				return false;
		} else if (!correct_ans.equals(other.correct_ans))
			return false;
		if (level != other.level)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}


	
	//check if the user choose the answer that the user choose is the correct answer!
	public Boolean checkCorrect(String answer) {
		if(answer.equals(this.correct_ans))
		{
			return true; //correct
		}
		return false; // not correct
	}

	
 
}
