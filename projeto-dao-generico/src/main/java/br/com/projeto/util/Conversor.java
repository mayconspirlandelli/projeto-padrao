package br.com.projeto.util;

import java.lang.reflect.Field;

public class Conversor {
	public static void converterAtributosMaiusculo(Object obj) {
		Field[] campos = obj.getClass().getDeclaredFields();
		for (int i = 0; i < campos.length; i++) {
			Field campo = campos[i];
			if (campo.getType().equals(String.class)) {
				campo.setAccessible(true);
				try {
					if (campo.get(obj) != null) {
						campo.set(obj, campo.get(obj).toString().toUpperCase());
					}
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
//					Logger.getRootLogger().error(e.getMessage(), e);
				} catch (IllegalAccessException e) {
					System.out.println(e.getMessage());
//					Logger.getRootLogger().error(e.getMessage(), e);
				}
			}
		}
	}
}
