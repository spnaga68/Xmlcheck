package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Failsafe {
	public static void main(String[] args) {
		String s="";
		FileInputStream in = null;
		FileOutputStream out = null;
		StringBuffer sb = new StringBuffer("");
		StringBuffer sbd = new StringBuffer("");

		// sb=null;
		String replacedd="";

		try {
			in = new FileInputStream("input.txt");
			out = new FileOutputStream("output.txt");
			int c;
			while ((c = in.read()) != -1) {
				sb.append((char) c);
			}
			s = sb.toString();
			 replacedd=s;
		//	 replacedd=s.replaceAll("\\s","");
//			if(s.contains( "\"shipping smethod\":"))
//			{
//				replacedd=s.replace("\"shipping_address\": 1,", "\"shipping_address\": true,");
//				System.out.println(s);
//			}
//			if(s.contains( "\"shipping_address\": 0,"))
//			{
//				replacedd=	s.replaceAll("\"shipping_address\": 0,", "\"shipping_address\": false,");
//				System.out.println("sdfhh");
//			}
			System.out.println(replacedd);
			String tres=replacedd.trim();
					tres=tres.replace("\"shipping_address\": 1,", "\"shipping_address\": true,");
//					System.out.println(tres);
//			System.out.println(tres);
			int start=tres.indexOf("\"shipping\":");
			//int one=tres.indexOf("\"shipping_address\":");
			int end=tres.lastIndexOf("}");
		//	int starts=tres.indexOf("\"shipping methods\":");
			
			System.out.println("neeed"+tres.substring(0,start));
			//System.out.println("ssssss"+tres.substring(starts+19));
		}
		
		catch(Exception e){
		}
	}
}
