package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class JsontoJava {
	public static void main(String args[]) throws IOException {
		String s = "";
		FileInputStream in = null;
		FileOutputStream out = null;
		StringBuffer sb = new StringBuffer("");

		try {
			in = new FileInputStream("input.txt");
			out = new FileOutputStream("output.txt");
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
				sb.append((char) c);
				// System.out.print((char)c);
			}
			s = sb.toString().trim().replaceAll("\\s", "");
			occc(s, "jobj", "object");
			// System.out.println("tttt-->"+s);
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}

		}
	}

	public static void occc(String s, String fn, String type) {
		ArrayList<HashMap<Integer, String>> symbol = new ArrayList<HashMap<Integer, String>>();
		HashMap<Integer, String> Parent;
		ArrayList<Integer> occurance = new ArrayList<Integer>();
		for (int i = -1; (i = s.indexOf("{", i + 1)) != -1;) {
			// System.out.println("--->"+i);
			Parent = new HashMap<Integer, String>();
			Parent.put(i, "{");
			symbol.add(Parent);
			// System.out.print("{");
			occurance.add(i);
			// int ssd=s.lastIndexOf(",",i+1);
			// System.out.print("dfs"+ssd);
			// System.out.println("objectname-->"+s.substring(ssd+2,i-2));

		}
		for (int i = -1; (i = s.indexOf("}", i + 1)) != -1;) {

			// System.out.println("--->"+i);

			Parent = new HashMap<Integer, String>();
			Parent.put(i, "}");
			symbol.add(Parent);
			// System.out.print("}");
			occurance.add(i);

		}
		for (int i = -1; (i = s.indexOf("[", i + 1)) != -1;) {

			// System.out.println("--->"+i);

			Parent = new HashMap<Integer, String>();
			Parent.put(i, "[");
			symbol.add(Parent);
			// System.out.print("[");
			occurance.add(i);

		}

		for (int i = -1; (i = s.indexOf("]", i + 1)) != -1;) {
			// System.out.println("--->"+i);
			Parent = new HashMap<Integer, String>();
			Parent.put(i, "]");
			symbol.add(Parent);
			// System.out.print("]");
			occurance.add(i);
		}
		// System.out.println("vennila" + symbol.size());
		// for (int i = 0; i < symbol.size(); i++) {
		// if (symbol.get(i).get("{") != null)
		// System.out.println("SSs-->" + symbol.get(i).get("{"));
		// }
		// System.out.println("before sort" + occurance.size());
		Collections.sort(occurance);
		for (int i = 0; i < occurance.size(); i++) {
			// System.out.print(s.charAt(occurance.get(i)));
		}
		for (int i = 0; i < occurance.size(); i++) {
			for (int id = 0; id < symbol.size(); id++) {
				// if (symbol.get(id).get(occurance.get(i)) != null)
				// System.out.println(occurance.get(i)
				// + "      ---->     "
				// + symbol.get(id).get(occurance.get(i)));
			}
		}
		// System.out.println("\n");
		addparents(occurance, s, fn, type);
	}

	public static void addparents(ArrayList<Integer> occurance, String s,
			String fn, String type) {
		int parent[] = new int[occurance.size()];
		int brack = 0;
		int arry = 0;
		for (int i = 1; i < occurance.size(); i++) {
			brack = 0;
			arry = 0;
			if (s.charAt(occurance.get(i)) == ']'
					|| s.charAt(occurance.get(i)) == '}') {
				parent[i] = -1;
			} else {
				for (int j = i; j >= 0; j--) {
					if (brack != -953) {
						if (s.charAt(occurance.get(j - 1)) == ']') {
							arry = arry - 1;
						} else if (s.charAt(occurance.get(j - 1)) == '}') {
							brack = brack - 1;
						} else if (s.charAt(occurance.get(j - 1)) == '{') {
							brack = brack + 1;
							if (brack == 1) {
								parent[i] = occurance.get(j - 1);
								brack = -953;
							}
						} else if (s.charAt(occurance.get(j - 1)) == '[') {
							arry = arry + 1;
							if (arry == 1) {
								parent[i] = occurance.get(j - 1);
								brack = -953;
							}
						}
					} else {
					}
				}
			}
		}
		ArrayList<String> ssparent = new ArrayList<String>();
		ArrayList<String> sssparent = new ArrayList<String>();
		for (int i = 0; i < occurance.size(); i++) {
			if (i != 0) {
				int sss = s.lastIndexOf("\"", (occurance.get(i) - 3));
				char check = s.replaceAll("\\s", "").charAt(sss + 1);
				if ((s.charAt(occurance.get(i)) == '{' || s.charAt(occurance
						.get(i)) == '[')) {
					// System.out.println(" --- " + check);
					if (check != ':')
						ssparent.add(s.substring(sss + 1, occurance.get(i) - 2));
					else {
						ssparent.add("");
					}
				} else {
					ssparent.add("--");
				}
			} else {
				ssparent.add(fn);
			}
			if (parent[i] != 0) {
				if ((s.charAt(occurance.get(i)) == '{' || s.charAt(occurance
						.get(i)) == '[') && s.charAt(parent[i]) != '[') {
					int sss = s.lastIndexOf("\"", (parent[i] - 3));
					sssparent.add(s.substring(sss + 1, parent[i] - 2));
				} else {
					sssparent.add("--");
				}
			} else {
				sssparent.add(fn);
			}
			 System.out.println(occurance.get(i) + "      ---->     "
			 + s.charAt(occurance.get(i)) + "  ---- >   " + parent[i]
			 + "  ---- >   " + ssparent.get(i) + "  ---- >   "
			 + sssparent.get(i));
		}
		for (int i = 0; i < ssparent.size(); i++) {
			if (i == 0) {
				if (type.equals("object")) {
					System.out.println("JSONObject " + fn
							+ "= new JSONObject(result);");
					System.out.println("HashMap<String,String> "
							+ ssparent.get(i) + "hm= "
							+ "new HashMap<String,String>();");
				} else {
					System.out.println("for(int i=0;i<" + fn
							+ ".length();i++){");
					System.out.println("JSONObject " + fn + "array=" + fn
							+ ".getJSONObject(i);");
				}
			} else {

				if (!sssparent.get(i).equals("--")
						&& !(sssparent.get(i).trim().equals(""))) {
					if (s.charAt(occurance.get(i)) == '{') {
						System.out.println("JSONObject " + ssparent.get(i)
								+ "= " + sssparent.get(i) + ".getJSONObject(\""
								+ ssparent.get(i) + "\");");
					} else if (s.charAt(occurance.get(i)) == '[') {
						System.out.println("JSONArray " + ssparent.get(i)
								+ "= " + sssparent.get(i) + ".getJSONArray(\""
								+ ssparent.get(i) + "\");");
					}
					System.out.println("HashMap<String,String> "
							+ ssparent.get(i) + "hm= "
							+ "new HashMap<String,String>();");
				}
			}
		}
		String forallstring;
		forallstring = s;
		for (int i = -1; (i = forallstring.indexOf("\":", i + 1)) != -1;) {
			if ((forallstring.charAt(i + 2) != '[' && forallstring
					.charAt(i + 2) != '{')) {
				int charpar = 0;
				int sss = forallstring.lastIndexOf("\"", (i - 3));
				int cc = getparr(i, occurance, s);
				if (!ssparent.get(cc).equals("")) {
					if (type.equals("array")) {
						System.out.println(ssparent.get(cc) + "hm.put(\""
								+ forallstring.substring(sss + 1, i) + "\","
								+ ssparent.get(cc) + "array.getString(" + "\""
								+ forallstring.substring(sss + 1, i) + "\"));");
					} else
						System.out.println(ssparent.get(cc) + "hm.put(\""
								+ forallstring.substring(sss + 1, i) + "\","
								+ ssparent.get(cc) + ".getString(" + "\""
								+ forallstring.substring(sss + 1, i) + "\"));");
				}
			}
		}
		if (type.equals("array")) {
			System.out.print("}");
		}
		for (int i = 0; i < occurance.size(); i++) {
			if (s.charAt(occurance.get(i)) == '[') {
				if (s.charAt(occurance.get(i + 1)) == ']') {
//arrrrrrr
//					System.out.println("for(int i=0;i<" + ssparent.get(i)
//							+ ".length();i++){");
//					// System.out.println("JSONObject " + ssparent.get(i) +
//					// "array=" + ssparent.get(i)
//					// + ".getJSONString(i);");
//					System.out.println(ssparent.get(i) + "hm.put(\""
//							+ ssparent.get(i) + "array\"," + ssparent.get(i)
//							+ ".getString(i));}");

				} else {
					String rr = s.substring(occurance.get(i));
					int last = rr.indexOf('}');
					String v = rr.substring(1, last + 1);
					occc(v, ssparent.get(i), "array");
				}
			}
		}
	}

	public static int getparr(int a, ArrayList<Integer> oc, String s) {
		int res = 99;
		int brack, ary;
		brack = 1;
		ary = 1;
		for (int i = oc.size() - 1; i >= 0; i--) {
			if (a > oc.get(i)) {

				if ((s.charAt(oc.get(i)) == '{')) {
					if (brack == 1) {
						res = i;
						i = -1;
					} else {
						brack = brack + 1;
					}
				} else if (s.charAt(oc.get(i)) == '}') {
					brack = brack - 1;
				} else if ((s.charAt(oc.get(i)) == '[')) {
					if (ary == 1) {
						res = i;
						i = -1;
					} else {
						ary = ary + 1;
					}
				} else if (s.charAt(oc.get(i)) == ']') {
					ary = ary - 1;
				}
			}
		}
		return res;
	}
}
