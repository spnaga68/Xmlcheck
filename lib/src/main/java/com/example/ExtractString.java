package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by developer on 14/6/17.
 */

public class ExtractString {

    private static String line;
    private static FileInputStream in2;
    private static FileInputStream in3;

    public static void main(String args[]) throws IOException {
        String s = "";
        ArrayList<String> lay = new ArrayList<String>();
        String declare = "", define = "";
        String listner = "";
        BufferedWriter writer = null;
        FileInputStream in = null;
        FileOutputStream out = null;
        StringBuffer sb = new StringBuffer("");
        StringBuffer sbd = new StringBuffer("");
        ArrayList<String> key = new ArrayList<>();
        ArrayList<String> value = new ArrayList<>();
        ArrayList<String> keyOther = new ArrayList<>();
        ArrayList<String> valueOutput = new ArrayList<>();
        HashMap<String, Integer> keyOtherRem = new HashMap<>();
        File f = new File("input2.txt");

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
       BufferedReader br2 = new BufferedReader(new FileReader("input2.txt"));
//        BufferedReader br3 = new BufferedReader(new FileReader("input3.txt"));
        try {
            in = new FileInputStream("input.txt");
            in2 = new FileInputStream("input2.txt");
//            in3 = new FileInputStream("input3.txt");
            out = new FileOutputStream("output.txt");
            int c = 0;
            while ((line = br.readLine()) != null) {
                // sb.append((char) c);
                if(!line.isEmpty()){
                    key.add(line);
                }

//
//                if (line.contains("<string name=")) {
//                    line = line.trim();
//                    String sd = line.substring(14, line.indexOf("\"", line.indexOf("\"") + 1));
//                   String values = line.substring(line.indexOf("\"", line.indexOf("\">")) + 2, line.indexOf("</string>"));
//                    System.out.println("" + sd);
//
//                    key.add(sd);
//                  //  keyOtherRem.put(sd, c);
////                   value.add(values);
//                    c++;
//                }

            }


            while ((line = br2.readLine()) != null) {
                // sb.append((char) c);
                if(!line.isEmpty()){
                    value.add(line);
                }}

//                if (line.contains("<string name=")) {
//                    line = line.trim();
//                    String sd = line.substring(14, line.indexOf("\"", line.indexOf("\"") + 1));
//                    String values = line.substring(line.indexOf("\"", line.indexOf("\">")) + 2, line.indexOf("</string>"));
//                    System.out.println("_____" + values);
//
//                    keyOther.add(sd);
////                    keyOther.put(sd, c);
////                    value.add(values);
////                    c++;
//                }
//
//                while ((line = br3.readLine()) != null) {
//                    // sb.append((char) c);
//
//                    if (line.trim() != "")
//                        valueOutput.add(line.trim());
////                    if (line.contains("<string name=")) {
////                        line=line.trim();
////                        String sd = line.substring(14, line.indexOf("\"",line.indexOf("\"")+1));
////                        String values=line.substring(line.indexOf("\"",line.indexOf("\">"))+2,line.indexOf("</string>"));
////                        System.out.println("_____" + sd);
////                        keyOther.add(sd);
////
////                        //   value.add(values);
////                    }
//
//                }
//
//            }
            s = sb.toString();
        } finally {
            if (in != null) {
                in.close();
            }
        }


        try {
            writer = new BufferedWriter(new FileWriter("output.txt"));
            //  writer.write(declare);
            //Collections.sort(key, String.CASE_INSENSITIVE_ORDER);
            Collections.sort(keyOther, String.CASE_INSENSITIVE_ORDER);
            System.out.println("_______*****" + key.size() + "___" + keyOther.size());
            key.removeAll(keyOther);
            System.out.println("_______*****" + key.size() + "___" + keyOther.size());

            for (int j = 0; j < key.size(); j++) {
//                  String id[]=keyOtherRem.get(j).split("____");
//                 writer.write("\n" + idd+" "+value.get(idd).trim());


//               int idd = keyOtherRem.get(key.get(j));
//                writer.write(value.get(idd).trim() + "\n");
//
                  //  writer.write("<string name=\""+key.get(j)+"\">"+value.get(j)+"</string>"+"\n");
                //  writer.write("\n" +value.get(idd).trim());
            }
            for (int j=0;j<key.size();j++){
//                if(j<keyOther.size() && key.get(j)!=null)
//            writer.write("\n" + key.get(j).trim()+"          "+keyOther.get(j).trim());
                //To extract value from primary lang

//                 writer.write("\n" +value.get(j).trim());
//                writer.write(value.get(j)+"\n");


                writer.write("<string name=\""+key.get(j)+"\">"+value.get(j)+"</string>"+"\n");
                //source key gen
                //key
                //value
                // writer.write(value.get(j)+"\n");
//
//
//
// writer.write("\n" + key.get(j).trim()+"          "+valueOutput.get(j).trim());
//
//
//
            }
            //  writer.write("\n\n" + listner.replaceAll(",", ".setOnClickListener(this);"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
            }
        }

    }
}
