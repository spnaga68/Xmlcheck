package com.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Xmlstring {

	
	public static void main(String s[])
	{
//		 <string name="Signwith">Sign in with :</string>
		String start="<string name=\"";
		String sec="\">";
		String end="</string>";
		BufferedReader br=null;
		try {
			br= new BufferedReader(new FileReader("input.txt"));		
			String ss;
			StringBuffer sv=new StringBuffer();
			while ((ss=br.readLine())!=null)
			{
			//sv.append(ss);
				System.out.println(start+ss.replaceAll("\\s","")+sec+ss+end);
			}
//			String svv=sv.toString();
//			String ssd=svv.replaceAll("\\s", "");
//			System.out.println(ssd);
//			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(br!=null)
			{
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
