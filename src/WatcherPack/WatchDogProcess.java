package WatcherPack;


import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WatchDogProcess {
	
	private static HeartBeat CurrentPulse=null;
	
	public static boolean checkCKS(byte[] bytes) {   			// Check the messages CKS	
		int cks=bytes[0]^bytes[1];
		for(int j=2;j<bytes.length-1;j++)
			cks =cks^bytes[j];
		if((byte)cks==bytes[bytes.length-1])
			return true;
		else
			return false;		
			}
	
	public static byte[] pureMessage(byte[] message){
		byte[] purebyte = new byte[message.length-1]; //AA BB 01 00 3F 00 0E 5FC41700 AACCCB
		int count=0;

		for(int i=0;i<message.length-1;i++){	
		purebyte[count]=message[i];
		count++;
		}
		return purebyte;
		}
	
	public static boolean TimeDateInterval_Seconds(Date date1,Date date2,int threshold){
		 boolean result=false;
		 
		    long diff = date1.getTime() - date2.getTime();	    
			long diffSeconds = diff / 1000 ;
			
			if(diffSeconds>threshold){
				result=true;
			}
	        
			
			//System.out.print(diffSeconds + " seconds.");
			return result;
	}
	
	
	
	private static void Monitor_process(){
		Thread thread = new Thread(new Runnable() {
            @Override
            public void run() { 
            	while(true){
            		
            		try{
            			
            			if( WatchDog_UI.Dog_switch && CurrentPulse!=null ){
            				HeartBeat ProcessPulse=CurrentPulse;
            				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            			    Date date = new Date();
            			    String time=dateFormat.format(date);
            		    		   	
            			   if( TimeDateInterval_Seconds(date,ProcessPulse.pulse,30)){
            				   
            				   System.out.println(" Boom!! ");
            			   }
            			                 		
            				 HeartBeat LastPulse=ProcessPulse;
            				 
                		}
            			
            			Thread.sleep(1000);
            		}catch(Exception e){
            			e.printStackTrace();
            		}
            		
            		            		
            	}
            	            	
            	}
        });
        thread.start();
		
	}
	
	
	public static void listening(String port){
		
		try{
			 int intValue = Integer.valueOf(port);
			 DatagramSocket dsocket = new DatagramSocket(intValue);
 	        System.out.println("ListenPortForIPC UDP UP "+intValue);
 	     
 	        byte[] buffer = new byte[2048];

 	        // Create a packet to receive data into the buffer
 	        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
 	       Monitor_process();
 	       
 	       while (true) {
 	          
 	          dsocket.receive(packet);
 	          
 	          byte[] newbuffer= new byte[packet.getLength()];
 	          
 	          
 	          for(int i =0;i<packet.getLength();i++)
 	          	newbuffer[i]=buffer[i];
 	          
 	          byte[] recv=newbuffer;
 	          //System.out.println(" UDP from "+packet.getAddress()+" "+bytesToHex(newbuffer)+" "+new java.util.Date());
 	          String from = packet.getAddress().toString().replace("/" , "");
 	          
 	          if( checkCKS(recv) && WatchDog_UI.Dog_switch)    
 	          {	        	  	     		 	     	
 	        	 recv=pureMessage(recv);
 	        	 
 	     		try {
 	     			Date now= new java.util.Date();
 	    			String temp=new String(recv, "UTF-8");
 	    			System.out.println(temp+" >> "+now.toString());
 	    			String[] newtemp=temp.split("@");
 	    			System.out.println(newtemp[0]+" >> PID");
 	    			 CurrentPulse=new HeartBeat(newtemp[0],now);
 	    			
 	    		} catch (UnsupportedEncodingException e) {
 	    			// TODO Auto-generated catch block
 	    			e.printStackTrace();	        	  	        	  
 	        	          	  
 	          }
 	          
 	       }
 	          
 	       }
 	       
		}catch(Exception e){
			System.out.println("Error in WatchDogProcess listening");
			e.printStackTrace();
			
			
		}
		
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
