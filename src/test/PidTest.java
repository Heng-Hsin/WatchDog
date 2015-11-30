package test;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.io.FileWriter;


public class PidTest {
	
	public static void Tasklist() {
		  
		try {
			String command="tasklist" ;
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(p.getInputStream()));

			String s = "";
			// reading output stream of the command
			while ((s = inputStream.readLine()) != null) {
				
				if(s.contains("java"))
				System.out.println(s);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			
		}
		
	}
	
	public static void Print(String filename,String pid){
		
		try{
			FileWriter writer = new FileWriter(filename);
			writer.append(pid);
			 writer.append("\r\n");
			 writer.flush();
			    writer.close();
			
		} catch (IOException e){
			e.printStackTrace();		
		}
		
		
	}
	
	public static String Readfile(String filename){
		String a="" ;
		try{
			BufferedReader br = new BufferedReader(new FileReader(filename)) ;
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    a = sb.toString();
		} catch (IOException e){
			e.printStackTrace();		
		}

		return a;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String temp=ManagementFactory.getRuntimeMXBean().getName();
		
		String[] parts = temp.split("@");
		System.out.println(temp);
		
		System.out.println(parts[0]);
		Print("C:\\Users\\Ted\\Desktop\\dot1\\PID.txt",parts[0]);
		 Tasklist();
		 String temp2=Readfile("C:\\Users\\Ted\\Desktop\\dot1\\PID.txt");
		 System.out.println(temp2);
		
	}

}
