package br.com.projeto.util;

public class StringUtils {
	public static boolean isEmptyOrNull(String str){
		return str == null || "".equals(str);
	}
}
