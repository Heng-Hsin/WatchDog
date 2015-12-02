package WatcherPack;



import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class WatchDogProcess {
	
	
	public static boolean checkCKS(byte[] bytes) {   			// Check the messages CKS	
		int cks=bytes[0]^bytes[1];
		for(int j=2;j<bytes.length-1;j++)
			cks =cks^bytes[j];
		if((byte)cks==bytes[bytes.length-1])
			return true;
		else
			return false;		
			}
	
	public static void listening(String port){
		
		try{
			 int intValue = Integer.valueOf(port);
			 DatagramSocket dsocket = new DatagramSocket(intValue);
 	        System.out.println("ListenPortForIPC UDP UP "+intValue);
 	     
 	        byte[] buffer = new byte[2048];

 	        // Create a packet to receive data into the buffer
 	        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
 	        
 	       while (true) {
 	          
 	          dsocket.receive(packet);
 	          
 	          byte[] newbuffer= new byte[packet.getLength()];
 	          
 	          
 	          for(int i =0;i<packet.getLength();i++)
 	          	newbuffer[i]=buffer[i];
 	          
 	          byte[] recv=newbuffer;
 	          //System.out.println(" UDP from "+packet.getAddress()+" "+bytesToHex(newbuffer)+" "+new java.util.Date());
 	          String from = packet.getAddress().toString().replace("/" , "");
 	          
 	          if( checkCKS(recv))    
 	          {	        	  	     		 	     	
 	     		
 	     		try {
 	     			Date now= new java.util.Date();
 	    			String temp=new String(recv, "UTF-8");
 	    			System.out.println(temp+" >> "+now.toString());
 	    		} catch (UnsupportedEncodingException e) {
 	    			// TODO Auto-generated catch block
 	    			e.printStackTrace();	        	  
 	        	  //System.out.println("Raw Message "+bytesToHex(recv));
 	        	          	  
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
