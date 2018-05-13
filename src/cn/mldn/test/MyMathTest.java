package cn.mldn.test;

import org.junit.Test;

import cn.mldn.util.MyMath;
import junit.framework.TestCase;

public class MyMathTest {

	@Test
	public void testDiv() {
		try {
			TestCase.assertEquals(MyMath.div(10, 2), 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
