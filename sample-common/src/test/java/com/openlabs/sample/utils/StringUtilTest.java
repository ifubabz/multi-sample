package com.openlabs.sample.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author openlabs
 *
 */
class StringUtilTest {

	@Test
	public void containSpace() {
		String[] spaceStrArr = {" ", "\t", "\n", "\r", "\r\n"};
		for(String str : spaceStrArr) {
			boolean result = StringUtil.containSpace(str);
			assertEquals(true, result);
		}
		
		String[] nonSpaceStrArr = {"A", "1", "^", "!"};
		for(String str : nonSpaceStrArr) {
			boolean result = StringUtil.containSpace(str);
			assertEquals(false, result);
		}
		
		String[] mixSpaceStrArr = {"A\tA", "A%1\r", " ^", "\t!12"};
		for(String str : mixSpaceStrArr) {
			boolean result = StringUtil.containSpace(str);
			assertEquals(true, result);
		}
	}

}
