package com.innovoak.util.webhelpers;

public class PrimitiveConverter {

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}

		catch (NumberFormatException er) {
			return false;
		}
	}

	public static boolean isFloat(String s) {
		try {
			Float.parseFloat(s);
			return true;
		}

		catch (NumberFormatException er) {
			return false;
		}
	}

	public static boolean isLong(String s) {
		try {
			Long.parseLong(s);
			return true;
		}

		catch (NumberFormatException er) {
			return false;
		}
	}

	public static boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		}

		catch (NumberFormatException er) {
			return false;
		}
	}

	public static boolean isBoolean(String s) {
		return "true".equalsIgnoreCase(s) || "false".equalsIgnoreCase(s);
	}
}
