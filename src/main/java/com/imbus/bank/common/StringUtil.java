/**
 * Pinganfu.com Inc. Copyright (c) 2003-2013 All Rights Reserved.
 */
package com.imbus.bank.common;


/**
 * 增强 StringUtil的填充字符串的功能，专门处理一些包含中文的字符串
 */
public class StringUtil {

	/**  */
	@SuppressWarnings("unused")
	private static int[] HEXADECIMAL;

	/** 空字符串 "" */
	public static final String EMPTY_STRING = "";
	/**
	 * 逗号 ,
	 */
	public static final String SEPARATOR_COMMA = ",";

	/**
	 * "" - true<br>
	 * " " - true<br>
	 * null - true<br>
	 * "1" - false<br>
	 * 2015年11月30日
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isBlank(final String str) {
		int length;
		if ((str == null) || ((length = str.length()) == 0)) {
			return true;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 2015年11月30日
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isNotBlank(final String str) {
		int length;
		if ((str == null) || ((length = str.length()) == 0)) {
			return false;
		}
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}

		return false;
	}

}
