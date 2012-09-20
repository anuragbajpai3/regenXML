import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;




public class regenerateXML {
	  public static void main(String args[])
	   {
		  List <String> FinalNodeInfo = new ArrayList <String> ();
	   try{
		   
		   /*Getting Node Information*/
		   FinalNodeInfo = getNodeInfo("C:\\Users\\abajpai\\git\\regenXML\\regenerateXML\\result_save.grs");
		   int k=0;
		    while(k<(FinalNodeInfo.size())){
			   System.out.println(FinalNodeInfo.get(k));
			   System.out.println(FinalNodeInfo.get(k+1));
			   System.out.println(FinalNodeInfo.get(k+2));
			   System.out.println(FinalNodeInfo.get(k+3));
			   k=k+4;
			  }/*End of while*/
		    String inFile = "C:\\Users\\abajpai\\git\\regenXML\\regenerateXML\\saveLivingstone-complex_copy.xml";
		    String OutFile = "C:\\Users\\abajpai\\git\\regenXML\\regenerateXML\\out.txt";
		    readnwriteXML(inFile,OutFile,FinalNodeInfo);
		    /*String srFile = "C:\\Users\\abajpai\\git\\regenXML\\regenerateXML\\saveLivingstone-complex.xml";
		    String dtFile = "C:\\Users\\abajpai\\git\\regenXML\\regenerateXML\\saveLivingstone-complex_copy.xml";
		    rewriteXML(srFile,dtFile,FinalNodeInfo);*/
		   
		    /*Copying of XML File*/
		    
		    //copyfile(srFile,dtFile);*/
	   }catch (Exception e){//Catch exception if any
	   System.err.println("Error: " + e.getMessage());
	   }
	   }
	
	private static String str_piece(String str, char separator, int index) {
	      String str_result = "";
	      int count = 0;
	      for(int i = 0; i < str.length(); i++) {
	          if(str.charAt(i) == separator) {
	              count++;
	              if(count == index) {
	                  break;
	              }
	          }
	          else {
	              if(count == index-1) {
	                  str_result += str.charAt(i);
	              }
	          }
	      }
	      return str_result;
	  }
	
	// Method for reading file and returning source node and destinations with classes
	private static List<String>getNodeInfo(String FileName) {
		// Open the file that is the first 
		   // command line parameter
		   FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(FileName);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		   // Get the object of DataInputStream
		   DataInputStream in = new DataInputStream(fstream);
		   BufferedReader br  = new BufferedReader(new InputStreamReader(in));
		   String strLine;
		   List <String> NodeName         = new ArrayList <String> ();
		   List <String> NodeClass        = new ArrayList <String> ();
		   List <String> SourceNodeName   = new ArrayList <String> ();
		   List <String> DestNodeName     = new ArrayList <String> ();
		   List <String> SourceNodeClass  = new ArrayList <String> ();
		   List <String> DestNodeClass    = new ArrayList <String> ();
		   List <String> ResultNode       = new ArrayList <String> ();
	//Read File Line By Line
		   try {
			while ((strLine = br.readLine()) != null)   {
			   // Print the content on the console
			   //System.out.println (strLine);
			   String str_nodesep = str_piece(strLine, '#', 1);
			   if (strLine.equals(str_nodesep)){
				  // System.out.println("The given string is not a hash line");// No hash present
				   String str_nodesep1 = str_piece(str_nodesep, ':', 1);
				   //System.out.println (str_nodesep1);
				   if (str_nodesep.equals(str_nodesep1)){
					  //System.out.println("The given string is not a node line");
				   }
				   else{
					 // System.out.println("The given string is a node/edge line");
					   String str_nodesep2 = str_piece(str_nodesep1, '@', 1);
					  if ((str_nodesep1.equals(str_nodesep2)))
					   {	
						  //System.out.println("The given string is a node line");
						    String str_resultn1 = str_piece(strLine, ':', 2);
						    //System.out.println(str_resultn1);
						    //System.out.println(str_piece(str_resultn1, '(', 1));
						   
						    NodeClass.add(str_piece(str_resultn1, '(', 1));
					        NodeName.add(str_piece(str_resultn1, '"', 2));
					        //System.out.println(str_piece(str_resultn1, '"', 5));
					   }
					  else
					  {
						    String str_result1 = str_piece(strLine, ':', 1);
					        String str_result2 = str_piece(strLine, ':', 2);
					        String str_result3 = str_piece(str_result2, '-', 2);
					        String str_destNode = str_piece(str_result3, '"', 2);
					        String str_srcNode = str_piece(str_result1, '"', 2);
					        SourceNodeName.add(str_piece(str_result1, '"', 2));
					        DestNodeName.add(str_piece(str_result3, '"', 2));
					        
					        
					   }
					 }
				   }
				   else{
				   //System.out.println("The given string is a hash line");
				   }
				 }
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   if(NodeName.size()==NodeClass.size()){
				for(int p = 0;p<NodeClass.size();p++){
					 for(int m = 0;m<SourceNodeName.size();m++){
						 if ((NodeName.get(p).equals(SourceNodeName.get(m)))){
							 SourceNodeClass.add(NodeClass.get(p));
						 }
					 }
					 for(int n = 0;n<SourceNodeName.size();n++){
						 if ((NodeName.get(p).equals(DestNodeName.get(n)))){
							 DestNodeClass.add(NodeClass.get(p));
						 } 
					 }
					 
			 }
		 }
		   for(int k = 0;k<SourceNodeName.size();k++){
			   //System.out.println(SourceNodeName.get(k));
			   ResultNode.add(SourceNodeName.get(k));
		       //System.out.println(DestNodeName.get(k));
		       ResultNode.add(DestNodeName.get(k));
		       //System.out.println(SourceNodeClass.get(k));
		       ResultNode.add(SourceNodeClass.get(k));
		       //System.out.println(DestNodeClass.get(k));
		       ResultNode.add(DestNodeClass.get(k));
		   }
		   
		   //Close the input stream
		   try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		     
	
		return ResultNode;
		
	}
	private static void copyfile(String srFile, String dtFile){
		  try{
		  File f1 = new File(srFile);
		  File f2 = new File(dtFile);
		  InputStream in = new FileInputStream(f1);
		  
		  //For Append the file.
		//  OutputStream out = new FileOutputStream(f2,true);

		  //For Overwrite the file.
		  OutputStream out = new FileOutputStream(f2);

		  byte[] buf = new byte[102400];
		  int len;
		  while ((len = in.read(buf)) > 0){
		  out.write(buf, 0, len);
		  }
		  in.close();
		  out.close();
		  System.out.println("File copied.");
		  }
		  catch(FileNotFoundException ex){
		  System.out.println(ex.getMessage() + " in the specified directory.");
		  System.exit(0);
		  }
		  catch(IOException e){
		  System.out.println(e.getMessage());  
		  }
		  }

	private static void readnwriteXML(String inFile,String OutFile,List <String> NodeInfo){
		try {        
			
			BufferedReader in = new BufferedReader(new FileReader(inFile)); 
		    PrintWriter out = new PrintWriter(new File(OutFile));   
		    String line; //a line in the file        
		    String params[]; //holds the name:number:color parameters of each line       
		   
		    while ((line = in.readLine()) != null) {                
		    	 
		    	//System.out.println(line);
		    	params = line.split("<processor", 2); //split the line into the 3 parameters seperated by :                
		    	if(params[0].equals(line)){
		    		//System.out.println("Not a Processor Line");
		    	}
		    	else{
		    		String ProcName = str_piece(line, '"', 2);
		    		System.out.println(ProcName);
		    		for (int i = 0;i<NodeInfo.size();i++){
		    			
		    		}
		    	}
		    	
		    	
		    	
		    	if ("Paul".equals(params[0]) && "746".equals(params[1])) { //find the line we want to replace    
				out.println(params[0] + ":" + params[1] + ":" + "Orange"); //output the new line      
				} else {                        
					out.println(line); //if it's not the line, just output it as-is   
				}        }        in.close();       
				out.flush();        
				out.close();
				} 
		catch (Exception e) {   
					e.printStackTrace();
					} 
			}
		
		
	
	
	
		private static void rewriteXML(String srFile, String dtFile,List <String> NodeInfo){
		copyfile(srFile, dtFile);
		FileWriter fstream ;
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory
				.newInstance();// obtain a Parser
		//Defines the API to obtain DOM Document instances from an XML document
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//The Document interface represents the entire XML document
		// provides the primary access to the document's data
		org.w3c.dom.Document doc = null;
		try {
			doc = dBuilder.parse(dtFile);
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//direct access to the child node that is the document element of the document.
		doc.getDocumentElement().normalize();
		
		System.out.println("Root Element: "
				+ doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("*");
		System.out.println("=============================");
		
		// Keep reading elements till you reach the end of subNode i.e - end of </operations> tag
		for (int i = 0; i < nList.getLength(); i++) {
			org.w3c.dom.Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				// Pass the tagName to method so that it can be populated
				
				if(eElement.hasChildNodes()){
					System.out.print("Tagname :'"+eElement.getNodeName()+"'\n Value:'"+eElement.getFirstChild().getNodeValue()+"'");
					
						if (eElement.hasAttributes()){
						      NamedNodeMap attributes = (NamedNodeMap)eElement.getAttributes();
				                for (int g = 0; g < attributes.getLength(); g++) {
				                    Attr attribute = (Attr)attributes.item(g);
				                    System.out.print(" Attribute: '" + attribute.getName() +
				                    "' with value '" +attribute.getValue()+"'\n\n");
				                }
							System.out.println("_______________________________________________");
						}
					}
				else{
					System.out.println("Tagname :'"+eElement.getNodeName()+"'\n Value:'"+eElement.getNextSibling().getNodeValue()+"'");
					
					if (eElement.hasAttributes()){
				      NamedNodeMap attributes = (NamedNodeMap)eElement.getAttributes();
		                for (int g = 0; g < attributes.getLength(); g++) {
		                    Attr attribute = (Attr)attributes.item(g);
		                    System.out.print("   Attribute: '" + attribute.getName() +
		                    "' with value '" +attribute.getValue()+"'\n\n");
		                }
		                System.out.println("_______________________________________________");
				} }
			}
		}
		
	
}
		
		
		  }

