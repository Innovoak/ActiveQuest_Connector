package com.innovoak.test;

import com.innovoak.util.webhelpers.data.DDLGenerator;

// USE THIS CLASS TO GENERATE THE MODELS ON YOUR END!!!
public class DDLGeneratorMain {

	public static void main(String[] args) throws Exception {
		DDLGenerator generator = new DDLGenerator("jdbc:mysql://localhost:3306/homework_database", "root", "root");

		generator.generate();
	}

}
