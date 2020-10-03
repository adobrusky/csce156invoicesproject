package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//Json class will take a list and output it as json
public class Json {
	
	public static <T> void write(String filePath, List<T> list, String header) {
		
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		
		try {
			PrintWriter out = new PrintWriter(new File(filePath));
			out.write("{\n");
			out.write("\"" + header + "\":");
			out.write(gson.toJson(list));
			out.write("}");
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
