package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Xmlintialize {
	public static void main(String args[]) throws IOException {
		String s = "";
		/*
		 * System.out.print(("nagaaaa")+args.length); for (int i = 0; i <
		 * args.length; i++) System.out.print(("nagaaaa")+(i == 0 ? args[i] :
		 * " " + args[i])); System.out.println();
		 */
		File myFile = new File("input.txt");
		if (myFile.createNewFile()){
			System.out.println("File is created!");
		}else{
			System.out.println("File already exists.");
		}
		ArrayList<String> lay = new ArrayList<String>();
		String declare = "", define = "";
		String listner="";
		BufferedWriter writer = null;
		// InputStreamReader cin = null;
		FileInputStream in = null;
		FileOutputStream out = null;
		StringBuffer sb = new StringBuffer("");
		StringBuffer sbd = new StringBuffer("");

		// sb=null;

		try {
			in = new FileInputStream("input.txt");
			out = new FileOutputStream("output.txt");
			int c;
			while ((c = in.read()) != -1) {
				sb.append((char) c);
			}
			s = sb.toString();
		} finally {
			if (in != null) {
				in.close();
			}
		}

		String vvvl = "";
		for (int i = -1; (i = s.indexOf("<", i + 1)) != -1;) {

			if (!(s.charAt(i + 1) == "/".charAt(0)
					|| (s.charAt(i + 1) == "!".charAt(0)) || (s.charAt(i + 1) == "?"
					.charAt(0)))) {

				int ssdd = s.indexOf("android:id=", i);
				int abcd = s.indexOf(">", i);
				if (ssdd < abcd) {
					int ssd = s.indexOf(" ", i);
					String layout = s.substring(i + 1, ssd);
					int dd = s.indexOf(" ", ssdd);
					String viewid = s.substring(ssdd + 17, dd - 2);
					vvvl = viewid;
					if (lay.size() == 0) {
						lay.add(layout + " " + viewid);
					} else if (lay.size() > 0) {
						int had = 0;
						// System.out.println("laysize"+lay.size());
						for (int is = 0; is < lay.size(); is++) {						
							if (lay.get(is).contains(layout)) {
								had = is;
							//	System.out.println("had"+is);
								break;
							} else {
								had = -1;
							}
						}
						if (had != -1) {
							String h = lay.get(had) + "," + viewid;
							lay.set(had, h);
						} else
							lay.add(layout + " " + viewid);
					}				
					String v =  viewid + "=" + "(" + layout + ")"
							+ "findViewById(R.id." + viewid + ");";
				//	System.out.println(v);
					sbd.append(v);
				} 
			}

		}
		define = sbd.toString();

		for (Iterator a=lay.iterator();a.hasNext();) {
			String sd = ( a.next() + ";").replaceAll("//s", "");
			declare += sd;
			System.out.println("====" + sd);
			
		}
	
//		for (Iterator a=lay.iterator();a.hasNext();) {
//			String sd = ( a.next() + ";").replaceAll("//s", "");
//			listner += sd+".setonClickListner(this)";
//			System.out.println("====" + sd);
//			
//		}
		for(int i=0;i<lay.size();i++)
		{
			System.out.println("---->"+lay.get(i));
			String sd[] = ( lay.get(i) ).split(" ");
			listner+=sd[1]+".setOnClickListener(this); \n";
			System.out.println("====" + sd[1]);
		
		}
		System.out.println("----"+listner);
		listner.replaceAll(",", ".setOnClickListener(this);");
		// PrintWriter outs = new PrintWriter("output.txt");

		try {
			writer = new BufferedWriter(new FileWriter("output.txt"));
			writer.write(declare);
			writer.write("\n\n" + define);
			writer.write("\n\n" + listner.replaceAll(",", ".setOnClickListener(this);"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
