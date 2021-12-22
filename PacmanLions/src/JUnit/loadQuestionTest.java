package JUnit;

import java.util.ArrayList;

import java.util.HashMap;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import Controller.SysData;
import Model.Question;
import Utils.Difficulty;

/**
 * 
 * @author hatemkhater
 *
 */
public class loadQuestionTest {
	@Test
	public void test() {
		//read json into string after load with null as a file path name
				SysData.getInstance().loadQuestions(null);
				HashMap<Difficulty, ArrayList<Question>> questions = SysData.getInstance().getQuestions();
			
//				//read json into output after save
				SysData.getInstance().loadQuestions(null);
				HashMap<Difficulty, ArrayList<Question>> questions2 = SysData.getInstance().getQuestions();


				
				//check if both question lists are equal (null)
				assertTrue("Successful", questions.equals(questions2));
	}
}
