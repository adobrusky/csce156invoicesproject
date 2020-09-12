package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json_write {
	
	public static <T> void printJSON(String filePath, List<T> list) {
		
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		
		try {
			PrintWriter out = new PrintWriter(new File(filePath));
			String header = "Person";
			
			out.write("{\n");
			out.write("\""+header+"\":");
			out.write(gson.toJson(list));
			out.write("}");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
