package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Xml_write {
	
	public static <T> void printXML(String filePath, List<T> list) {
		
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("Person", com.bc.Person.class);
		
		try {
			PrintWriter out = new PrintWriter(new File(filePath));
			out.print("<?xml version=\"1.0\"?>\n");
			String header = "<Person>";
			String closer = "</Person>";
			
			out.write(header);
			for(T entry : list) {
				
				out.write(xstream.toXML(entry));
				out.write("\n");
			}
			out.write(closer);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
