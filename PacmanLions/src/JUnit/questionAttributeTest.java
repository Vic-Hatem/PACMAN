package JUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import Controller.SysData;
import Model.Question;
import Utils.Difficulty;

/**
 * 
 * @author hatemkhater
 *	JUnit 4 
 */
public class questionAttributeTest {
	@Test
	public void test() {
		
		Question testQ=new Question("this is a test?", "no", "maybe", "check for null, pleas!", null, null, Difficulty.EASY, null);
		Boolean output=SysData.getInstance().addQuestion(testQ);
		//check if add question will return false if the object that is passed includes null values in it (Question with missing attributes)
		assertFalse(output);
	}
}
