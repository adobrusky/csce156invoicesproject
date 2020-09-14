package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Xml_write {

	public static <T> void printXML(String filePath, List<T> list, String title) {
		switch (title) {
		case "persons":
			XStream xstream1 = new XStream(new DomDriver());
			xstream1.alias(title.substring(0, title.length() - 1), com.bc.Person.class);
			try {
				PrintWriter out = new PrintWriter(new File(filePath));
				out.print("<?xml version=\"1.0\"?>\n");
				String header = "<Persons>\n";
				String closer = "</Persons>";

				out.write(header);
				for (T entry : list) {

					out.write(xstream1.toXML(entry));
					out.write("\n");
				}
				out.write(closer);
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "customers":
			XStream xstream2 = new XStream(new DomDriver());
			xstream2.alias(title.substring(0, title.length() - 1), com.bc.Customer.class);
			try {
				PrintWriter out = new PrintWriter(new File(filePath));
				out.print("<?xml version=\"1.0\"?>\n");
				String header = "<Customers>\n";
				String closer = "</Customers>";

				out.write(header);
				for (T entry : list) {

					out.write(xstream2.toXML(entry));
					out.write("\n");
				}
				out.write(closer);
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "products":
			XStream xstream3 = new XStream(new DomDriver());
			xstream3.alias(title.substring(0, title.length() - 1), com.bc.Product.class);
			try {
				PrintWriter out = new PrintWriter(new File(filePath));
				out.print("<?xml version=\"1.0\"?>\n");
				String header = "<Products>\n";
				String closer = "</Products>";

				out.write(header);
				for (T entry : list) {

					out.write(xstream3.toXML(entry));
					out.write("\n");
				}
				out.write(closer);
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;

		}

	}

}
