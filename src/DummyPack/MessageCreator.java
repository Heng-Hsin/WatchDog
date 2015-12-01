package DummyPack;

import java.io.UnsupportedEncodingException;



public class MessageCreator {
	final protected static String header ="AABB";
	final protected static String length ="0000";
	final protected static  String ender  ="AACC00";
	private static String Seq;
	private static String Addr;
	private static String Message;
			 
	public MessageCreator(String seq,String addr, String message){	// seq addr message
		this.Seq=seq;
		this.Addr=addr;
		this.Message=message;		
	}
   	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
public static byte[] CKS(byte[] bytes) {   // calculate message cks		
	int cks=bytes[0]^bytes[1];
	for(int j=2;j<bytes.length-1;j++)
		cks =cks^bytes[j];
	bytes[bytes.length-1]=(byte)cks;
	//System.out.println("CKS ="+bytes[bytes.length-1]);
	  return bytes;
		}

public static byte[] CKS2(byte[] bytes) {   // new message cks needs to add a additional byte to the end of the message		
	
	byte[] newByte = new byte[bytes.length + 1];
	System.arraycopy(bytes, 0, newByte, 0, bytes.length);
	
	int cks=newByte[0]^newByte[1];
	for(int j=2;j<newByte.length-1;j++)
		cks =cks^newByte[j];
	
	newByte[newByte.length-1]=(byte)cks;
	
	  return newByte;
		}

public static boolean checkCKS(byte[] bytes) {   			// Check the messages CKS	
	int cks=bytes[0]^bytes[1];
	for(int j=2;j<bytes.length-1;j++)
		cks =cks^bytes[j];
	if((byte)cks==bytes[bytes.length-1])
		return true;
	else
		return false;		
		}

public static byte[] messagelength(byte[] bytes) {  //calculate message length
	
		bytes[5]=(byte)(bytes.length/256);
		bytes[6]=(byte)(bytes.length%256);
		//System.out.println("length ="+bytes[5]+bytes[6]);
	
	  return bytes;
	}

public static byte[] messagelength2(byte[] bytes) {  //calculate message length
	
	bytes[bytes.length-3]=(byte)(bytes.length/256);
	bytes[bytes.length-2]=(byte)(bytes.length%256);
	//System.out.println("length ="+bytes[5]+bytes[6]);

  return bytes;
}

	public static byte[] create() {
						 		
		
		//AABB011035000D5F4401AACC44 verified message send UDP to IP = 10.122.1.36  port=31035				    	   
	    
	    String combinemessage=header+Seq+Addr+length+Message+ender;	    	    
	    
	    byte[] entiremessage =hexStringToByteArray(combinemessage);
																		
			entiremessage= messagelength(entiremessage);
			entiremessage= CKS(entiremessage);
			
			//System.out.println(bytesToHex(entiremessage));
			
		return entiremessage;								
	
	}
	
	public static byte[] createpackage(String seq,String addr,String msg){
		
		   String combinemessage=header+seq+addr+length+msg+ender;	    	    
		    
		    byte[] entiremessage =hexStringToByteArray(combinemessage);
																			
				entiremessage= messagelength(entiremessage);
				entiremessage= CKS(entiremessage);
				
				//System.out.println(bytesToHex(entiremessage));
				
			return entiremessage;				
		
	}
	
	public static String createpackage_str(String seq,String addr,String msg){
		
		   String combinemessage=header+seq+addr+length+msg+ender;	    	    
		    
		    byte[] entiremessage =hexStringToByteArray(combinemessage);
																			
				entiremessage= messagelength(entiremessage);
				entiremessage= CKS(entiremessage);
				
				//System.out.println(bytesToHex(entiremessage));
				String a =bytesToHex(entiremessage);
			return a;				
		
	}
	
	public static byte[] CreateHeartBeat_Msg(String ID){
		
		try{
		
			byte[] bytes = ID.getBytes("UTF-8");
			
			bytes= CKS2(bytes);
			
			//String doc2 = new String(bytes, "UTF-8");
			return bytes;
			
		}catch(Exception e ){
			System.out.println("Error in  CreateHeartBeat_Msg");
			return null;
		}
		
		
		
	}
	
public static void main(final String args[]) {
   
 		
 		byte[] cmd=createpackage("01","FFFF", "5F1C02014E");  //1.Seq 2.Addr 3.�ʥ]���e
 		System.out.println("A.0 Packet  " + bytesToHex(cmd));    //�]�n�����e ���o�Ӹ�A�����
 		
 		byte[] cmd2=createpackage("01","2062", "5F190000000000");  //1.Seq 2.Addr 3.�ʥ]���e
 		System.out.println("4.0 Packet  " + bytesToHex(cmd2));    //�]�n�����e ���o�Ӹ�A�����
 		
 		byte[] cmd3=CreateHeartBeat_Msg("TestingHeartBeat");
 		
 		System.out.println(checkCKS(cmd3));
 		
 		try {
			String temp=new String(cmd3, "UTF-8");
			System.out.println(temp);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		//�Ь�hexStringToByteArray(String s)   CKS(byte[] bytes)   messagelength(byte[] bytes)  
 		//createpackage(String seq,String addr,String msg)
 		//�o4��function ��L���A���Ӥ��ΰѦ�
	
        
    }
	
	

}