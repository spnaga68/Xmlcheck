package com.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by developer on 12/4/18.
 */

public class XmlToJson {

    private static String line;
    private static FileInputStream in2;
    private static FileInputStream in3;

    public static void main(String args[]) throws IOException {
        BufferedWriter writer = null;
        FileInputStream in = null;
        ArrayList<String> key = new ArrayList<>();
        ArrayList<String> value = new ArrayList<>();
        ArrayList<String> keyOther = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("input2.txt"));
        try {
            in = new FileInputStream("input.txt");
           /* in = new FileInputStream("input.txt");
//            in2 = new FileInputStream("input2.txt");
            while ((line = br.readLine()) != null) {
                // sb.append((char) c);
                if (!line.isEmpty()) {*/

                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(in);
                    doc.getDocumentElement().normalize();
                    System.out.println("Root element :" + doc.getDocumentElement().getLocalName());
                    NodeList nList = doc.getElementsByTagName("string");
                    for (int temp = 0; temp < nList.getLength(); temp++) {
                        Node nNode = nList.item(temp);
                        System.out.println("\nCurrent Element :" + nNode.getNodeName());

                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            System.out.println("Student roll no : "
                                    + eElement.getAttribute("name") + "__" + eElement.getElementsByTagName("string"));
                            key.add(eElement.getAttribute("name"));
                            value.add(eElement.getTextContent());
                        }
                    }

//                }
//            }
//            while ((line = br2.readLine()) != null) {
//                if (!line.isEmpty()) {
//                    value.add(line);
//                }
//            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }


        try {
            writer = new BufferedWriter(new FileWriter("output.txt"));
            Collections.sort(keyOther, String.CASE_INSENSITIVE_ORDER);
            System.out.println("_______*****" + key.size() + "___" + keyOther.size());
            key.removeAll(keyOther);
            System.out.println("_______*****" + key.size() + "___" + keyOther.size());
            JsonObject jsonObject = new JsonObject();
            for (int j = 0; j < key.size(); j++) {

                jsonObject.addProperty(key.get(j), value.get(j));
                // writer.write("<string name=\"" + key.get(j) + "\">" + value.get(j) + "</string>" + "\n");
            }
            writer.write(jsonObject.toString());
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