package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Xml_write {
	
	public static <T> void printXML(String filePath, List<T> list, String title) {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("person", com.bc.Person.class);
		xstream.alias("customer", com.bc.Customer.class);
		xstream.alias("product", com.bc.Rental.class);
		xstream.alias("product", com.bc.Repair.class);
		xstream.alias("product", com.bc.Towing.class);
		xstream.alias("product", com.bc.Concession.class);
		try {
			PrintWriter out = new PrintWriter(new File(filePath));
			out.print("<?xml version=\"1.0\"?>\n");
			String header = "<" + title + ">\n";
			String closer = "</" + title + ">";

			out.write(header);
			for (T entry : list) {

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
