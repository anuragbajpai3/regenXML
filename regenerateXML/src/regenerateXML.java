import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class regenerateXML {
	  public static void main(String args[])
	   {
		  List <String> FinalNodeInfo = new ArrayList <String> ();
	   try{
		   
		   /*Getting Node Information*/
		   FinalNodeInfo = getNodeInfo("//Users//Anurag//Documents//workspace//regenerateXML//result.grs");
		   int k=0;
		    while(k<(FinalNodeInfo.size())){
			   System.out.println(FinalNodeInfo.get(k));
			   System.out.println(FinalNodeInfo.get(k+1));
			   System.out.println(FinalNodeInfo.get(k+2));
			   System.out.println(FinalNodeInfo.get(k+3));
			   k=k+4;
			  }/*End of while*/
		   /*Copying of XML File*/
		    String srFile = "//Users//Anurag//Documents//workspace//regenerateXML//example.xml";
		    String dtFile = "//Users//Anurag//Documents//workspace//regenerateXML//example_regen.xml";
		    copyfile(srFile,dtFile);
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
					  // System.out.println("The given string is not a node line");
				   }
				   else{
					  // System.out.println("The given string is a node/edge line");
					   String str_nodesep2 = str_piece(str_nodesep1, '@', 1);
					  if ((str_nodesep1.equals(str_nodesep2)))
					   {	
						  //System.out.println("The given string is a node line");
						    String str_resultn1 = str_piece(strLine, ':', 2);
						    NodeClass.add(str_piece(str_resultn1, '(', 1));
					        NodeName.add(str_piece(str_resultn1, '"', 2));
					       
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
}
