package com.hrsb.cg.util;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.Perl5Substitution;
import org.apache.oro.text.regex.Util;




public class StringUtil extends StringUtils{
	
	private static Log log = LogFactory.getLog(StringUtil.class);

	/**
	 * 匹配正则表达式中的特殊字符，避免由于此类字符未进行合理转换，导致匹配结果出错 如：如果想表示字符$，需要将其改为\$才能精确匹配。
	 * 
	 * @param str
	 * @return
	 */
	public static String convertRegeSpecChar(String str) {
		str = StringUtil.replaceTextAll(str, "\\", "\\\\");
		str = StringUtil.replaceTextAll(str, "$", "\\$");
		str = StringUtil.replaceTextAll(str, "(", "\\(");
		str = StringUtil.replaceTextAll(str, ")", "\\)");
		str = StringUtil.replaceTextAll(str, "*", "\\*");
		str = StringUtil.replaceTextAll(str, "+", "\\+");
		str = StringUtil.replaceTextAll(str, ".", "\\.");
		str = StringUtil.replaceTextAll(str, "[", "\\[");
		str = StringUtil.replaceTextAll(str, "?", "\\?");
		str = StringUtil.replaceTextAll(str, "^", "\\^");
		str = StringUtil.replaceTextAll(str, "{", "\\{");
		str = StringUtil.replaceTextAll(str, "|", "\\|");
		return str;
	}

	/**
	 * 过滤掉回车字符
	 * 
	 * @param str
	 * @return
	 */
	public static String filterEnter(String str) {
		str = StringUtil.replaceTextAll(str, "\n", "");
		str = StringUtil.replaceTextAll(str, "\r", "");
		return str;
	}

	/**
	 * 对要输出到form上的字符串进行转码处理
	 * （form对字符串中的&amp;标记联用，比如&amp;lt;&amp;gt;等，会直接以&lt;&gt;显示，所以需要对&进行字符替换）
	 * 
	 * @param source
	 * @return
	 */
	public static String formEncode(String source) {
		source = replaceTextAll(source, "&", "&amp;");
		return source;
	}

	/**
	 * 获取指定位数的随机数
	 * 
	 * @param digit
	 * @return
	 */
	public static String getRandom(int digit) {
		return RandomStringUtils.randomNumeric(digit);
	}

	/**
	 * 对字符串中的特殊符号进行html转码处理
	 * 
	 * @param source
	 * @return
	 */
	public static String htmlEncode(String source) {
		source = replaceTextAll(source, "&", "&amp;");
		source = replaceTextAll(source, "<", "&lt;");
		source = replaceTextAll(source, ">", "&gt;");
		source = replaceTextAll(source, " ", "&nbsp;");
		source = replaceTextAll(source, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		source = replaceTextAll(source, "\n", "<br>");
		source = replaceTextAll(source, "\r", "");
		return source;
	}

	/**
	 * 查找指定字符串在源字符串中的第一个出现位置
	 * 
	 * @param str
	 * @param searchStr
	 * @return
	 */
	public static int indexOf(String str, String searchStr) {
		int pos = org.apache.commons.lang.StringUtils.indexOf(str, searchStr);
		return pos;
	}

	/**
	 * 判断数组是否为空
	 * 
	 * @param strArray
	 * @return
	 */
	public static boolean isEmpty(String[] strArray) {
		if (strArray != null && strArray.length > 0)
			return false;
		return true;
	}

	/**
	 * 正则表达式匹配，如果匹配则返回true。常用于字符串的校验
	 * 
	 * @param sourceStr
	 * @param regexp
	 * @return
	 */
	public static boolean isMatch(String sourceStr, String regexp) {
		boolean result = false;
		PatternCompiler compiler = new Perl5Compiler();
		PatternMatcher matcher = new Perl5Matcher();
		try {
			Pattern pattern = compiler.compile(regexp,
					Perl5Compiler.CASE_INSENSITIVE_MASK);
			result = matcher.matches(sourceStr, pattern);
		} catch (MalformedPatternException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查找指定字符串在源字符串中的最后一个出现位置
	 * 
	 * @param str
	 * @param searchStr
	 * @return
	 */
	public static int lastIndexOf(String str, String searchStr) {
		int pos = org.apache.commons.lang.StringUtils.lastIndexOf(str,
				searchStr);
		return pos;
	}

	/**
	 * 字符串替换，支持正则表达式
	 * 
	 * @param str
	 * @param searchStr
	 * @param replaceStr
	 * @param ignoreCase
	 * @param num
	 * @return
	 */
	public static String replace(String str, String searchStr,
			String replaceStr, boolean ignoreCase, int num) {
		String result = str;
		if (num == 0) {
			num = Util.SUBSTITUTE_ALL;
		}
		try {
			String ps1 = searchStr;
			PatternCompiler orocom = new Perl5Compiler();
			Pattern pattern1 = null;
			if (ignoreCase) {
				pattern1 = orocom.compile(ps1,
						Perl5Compiler.CASE_INSENSITIVE_MASK);
			} else {
				pattern1 = orocom.compile(ps1);
			}
			PatternMatcher matcher = new Perl5Matcher();
			result = Util.substitute(matcher, pattern1, new Perl5Substitution(
					replaceStr), str, num);
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	/**
	 * 字符串替换，支持正则表达式（匹配忽略大小写）
	 * 
	 * @param str
	 * @param searchStr
	 * @param replaceStr
	 * @param num
	 * @return
	 */
	public static String replace(String str, String searchStr,
			String replaceStr, int num) {
		return replace(str, searchStr, replaceStr, true, num);
	}

	/**
	 * 字符串替换-忽略大小写（替换所有符合项，支持正则表达式）
	 * 
	 * @param str
	 * @param searchStr
	 * @param replaceStr
	 * @return
	 */
	public static String replaceAll(String str, String searchStr,
			String replaceStr) {
		return replace(str, searchStr, replaceStr, true, 0);
	}

	/**
	 * 字符串替换（替换所有符合项）
	 * 
	 * @param str
	 * @param searchStr
	 * @param replaceStr
	 * @param ignoreCase
	 * @return
	 */
	public static String replaceAll(String str, String searchStr,
			String replaceStr, boolean ignoreCase) {
		return replace(str, searchStr, replaceStr, ignoreCase, 0);
	}

	/**
	 * 字符串替换（单纯的字符串替换，不支持正则表达式）
	 * 
	 * @param str
	 * @param searchStr
	 * @param replaceStr
	 * @param ignoreCase
	 * @param max
	 * @return
	 */
	public static String replaceText(String str, String searchStr,
			String replaceStr, boolean ignoreCase, int max) {
		if (str == null || searchStr == null || replaceStr == null
				|| searchStr.length() == 0 || max == 0)
			return str;
		StringBuffer buf = new StringBuffer(str.length());
		int start = 0;
		int end = 0;
		do {
			if (ignoreCase) {
				if ((end = str.toLowerCase().indexOf(searchStr.toLowerCase(),
						start)) == -1)
					break;
			} else {
				if ((end = str.indexOf(searchStr, start)) == -1)
					break;
			}
			buf.append(str.substring(start, end)).append(replaceStr);
			start = end + searchStr.length();
		} while (--max != 0);
		buf.append(str.substring(start));
		return buf.toString();
	}

	/**
	 * 字符串替换-忽略大小写（替换所有符合项，但只是单纯的替换，不支持正则表达式）
	 * 
	 * @param str
	 * @param searchStr
	 * @param replaceStr
	 * @return
	 */
	public static String replaceTextAll(String str, String searchStr,
			String replaceStr) {
		return replaceText(str, searchStr, replaceStr, true, -1);
	}

	/**
	 * 字符串替换（替换所有符合项，但只是单纯的替换，不支持正则表达式）
	 * 
	 * @param str
	 * @param searchStr
	 * @param replaceStr
	 * @param ignoreCase
	 * @return
	 */
	public static String replaceTextAll(String str, String searchStr,
			String replaceStr, boolean ignoreCase) {
		return replaceText(str, searchStr, replaceStr, ignoreCase, -1);
	}

	/**
	 * 字符串转数组，默认使用逗号进行拆分
	 * 
	 * @param str
	 * @return
	 */
	public static String[] toArray(String str) {
		String[] arraystr = org.apache.commons.lang.StringUtils.split(str, ",");
		return arraystr;
	}

	/**
	 * 字符串转数组。如aa|bb|cc，分隔符输入|，即可拆成aa，bb，cc的数组。
	 * 
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String[] toArray(String str, String separator) {
		String[] arraystr = org.apache.commons.lang.StringUtils.split(str,
				separator);
		return arraystr;
	}

	public static String toString(String[] arraystr) {
		String result = "";
		if (arraystr != null && arraystr.length > 0) {
			for (int i = 0; i < arraystr.length; i++) {
				if (result.equals("")) {
					result = arraystr[i];
				} else {
					result = result + "," + arraystr[i];
				}
			}
		}
		return result;
	}

	/**
	 * 空格过滤
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return org.apache.commons.lang.StringUtils.trimToEmpty(str);
	}

}
