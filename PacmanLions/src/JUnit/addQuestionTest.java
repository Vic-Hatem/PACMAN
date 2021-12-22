package JUnit;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import Controller.SysData;
/**
 * 
 * @author hatemkhater
 * JUnit 4
 */
public class addQuestionTest {
	
	@Test
	public void test() {
		//checks if the add question method return false when passing a null object
		assertFalse(SysData.getInstance().addQuestion(null));
	}
}
