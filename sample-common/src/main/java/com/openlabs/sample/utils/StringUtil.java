package com.openlabs.sample.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * String Utility
 * 
 * @author openlabs 
 *
 */
public class StringUtil {

	/**
	 * <p>문자열(str)이 공백이나 NULL인지 검사한다.</p> 
	 * 
	 * <p>Defined by {@link StringUtils#isEmpty(CharSequence)}.</p>
	 * 
	 * @param str the String to check, may be null
	 * @return boolean true if the String is empty or null
	 */
	public static boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}
	
	/**
	 * <p>문자열(str)이 공백이 포함되어 있는지 검사한다.</p> 
	 * 
	 * <p>Defined by {@link StringUtils#containsWhitespace(CharSequence)}.</p>
	 * 
	 * @param str the String to check, may be null
	 * @return boolean true if the String is not empty and contains at least 1 (breaking) whitespace character
	 */
	public static boolean containSpace(String str) {
		return StringUtils.containsWhitespace(str);
	}
	
	/**
	 * <p>문자열(str)이 숫자인지 검사한다.</p>
	 * <p>Defined by {@link StringUtils#isNumeric(CharSequence)}.</p>
	 * 
	 * @param str the String to check, may be null
	 * @return true if only contains digits, and is non-null
	 */
	public static boolean isDigit(String str) {
		return StringUtils.isNumeric(str);
	}
	
	/**
	 * <p>문자열(str)에 숫자가 포함되어 있는지 검사한다.</p>
	 * <p>Digit is defined by {@link Character#isDigit(CharSequence)}.</p>
	 * 
	 * @param str the String to check, may be null
	 * @return boolean true if the String is not empty and contains at least 1 (breaking) Digit character
	 */
	public static boolean containDigit(String str) {
		if (str == null) {
            return false;
        }
		int len = str.length();
		for(int i=0; i<len; i++) {
			if(Character.isDigit(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
	
}
