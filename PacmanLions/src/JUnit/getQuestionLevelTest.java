package JUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Controller.SysData;
import Utils.Difficulty;
/**
 * 
 * @author hatemkhater
 * JUnit 4
 */
public class getQuestionLevelTest {
	@Test
	public void test() {
		// check if the methods returns medium as a default value for questions difficulty (given non-Difficulty value "IMPOSSIBLE")
		assertEquals(Difficulty.MEDIUM, SysData.getInstance().getQuestionLevel("IMPOSSIBLE"));
	}
}	
