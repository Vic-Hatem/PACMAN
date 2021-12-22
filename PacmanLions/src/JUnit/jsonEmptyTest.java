package JUnit;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import Controller.SysData;

/**
 * 
 * @author hatemkhater
 * JUnit 4
 */

public class jsonEmptyTest {
	@Test
	public void test() {
		//checks if the json file is empty/has no questions in it
		assertFalse(SysData.getInstance().loadQuestions("src/QuestionsFormat.txt"));
	}
}
