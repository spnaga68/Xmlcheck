package com.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JsonString {

	public static void main(String args[]) {
		String inputstr = null;
		try {

			FileInputStream fi = new FileInputStream("input.txt");
			FileOutputStream fo = new FileOutputStream("ouput.txt");
			StringBuffer input = new StringBuffer();
			int c = -1;
			try {
				while ((c = fi.read()) != -1) {
					input.append((char) c);
				}
				inputstr = input.toString();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = -1; (i = inputstr.indexOf("\":", i + 1)) != -1;) {
			// System.out.println(
			// inputstr.substring((inputstr.lastIndexOf("\"", i - 1))+1, i )
			// );
			String s = inputstr.substring(
					(inputstr.lastIndexOf("\"", i - 1)) + 1, i);
			// hm.put("",dealslist.getString(""));
			System.out.println("hm.put(\"" + s + "\"" + ",jsonobj.getString(\""
					+ s + "\"));");
		}

	}
}
