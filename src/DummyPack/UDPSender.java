package DummyPack;



import java.net.*;


public class UDPSender {
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();	
	
		private static String UDPIP;
		private static int UDPport;
		private static byte[] Message;
				
		public UDPSender(String ip,int port,byte[] message){
			this.UDPIP=ip;
			this.UDPport=port;
			this.Message=message;
			
		}
		
		public static byte[] hexStringToByteArray(String s) {				//Convert String to byte array
		    int len = s.length();
		    byte[] data = new byte[len / 2];
		    for (int i = 0; i < len; i += 2) {
		        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
		                             + Character.digit(s.charAt(i+1), 16));
		    }
		    return data;
		}
		
		public static String bytesToHex(byte[] bytes) {
		    char[] hexChars = new char[bytes.length * 2];
		    for ( int j = 0; j < bytes.length; j++ ) {
		        int v = bytes[j] & 0xFF;
		        hexChars[j * 2] = hexArray[v >>> 4];
		        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		    }
		    return new String(hexChars);
		}
		
		


	
	public static void Send(String destinationIP, String destinationPort, String UDPMessage ) {
		try{
			// Get the internet address of the specified host
			InetAddress address = InetAddress.getByName(destinationIP);
			int PortValue = Integer.parseInt(destinationPort);
			// Initialize a datagram packet with data and address
			byte[] UDPbyteMessage=hexStringToByteArray(UDPMessage);
			
			DatagramPacket packet = new DatagramPacket(UDPbyteMessage,UDPbyteMessage.length, address, PortValue);
			
			//System.out.println("Address "+address+" portvalue "+PortValue +" Time "+new java.util.Date());
			//System.out.println("UDPMessage "+UDPMessage);
			
			// Create a datagram socket, send the packet through it, close it.
			DatagramSocket dsocket = new DatagramSocket();
			dsocket.send(packet);
			dsocket.close();
			} catch (Exception e) {
				System.out.println("Error UDPSender");
			System.err.println(e);
			}

		
		}
	
	public static void Send(String destinationIP, int PortValue, String UDPMessage ) {
		try{
			// Get the internet address of the specified host
			InetAddress address = InetAddress.getByName(destinationIP);
			
			// Initialize a datagram packet with data and address
			byte[] UDPbyteMessage=hexStringToByteArray(UDPMessage);
			
			DatagramPacket packet = new DatagramPacket(UDPbyteMessage,UDPbyteMessage.length, address, PortValue);
			
			//System.out.println("Address "+address+" portvalue "+PortValue +" Time "+new java.util.Date());
			//System.out.println("UDPMessage "+UDPMessage);
			
			// Create a datagram socket, send the packet through it, close it.
			DatagramSocket dsocket = new DatagramSocket();
			dsocket.send(packet);
			dsocket.close();
			} catch (Exception e) {
				System.out.println("Error UDPSender");
			System.err.println(e);
			}

		}
	
	public static void Send(String destinationIP, String destinationPort, byte[] UDPMessage ) {
		try{
			// Get the internet address of the specified host
			InetAddress address = InetAddress.getByName(destinationIP);
			int PortValue = Integer.parseInt(destinationPort);
			// Initialize a datagram packet with data and address
			
			
			DatagramPacket packet = new DatagramPacket(UDPMessage,UDPMessage.length, address, PortValue);
			
			//System.out.println("Address "+address+" portvalue "+PortValue +" Time "+new java.util.Date());
			//System.out.println("UDPMessage "+bytesToHex(UDPMessage));
			
			// Create a datagram socket, send the packet through it, close it.
			DatagramSocket dsocket = new DatagramSocket();
			dsocket.send(packet);
			dsocket.close();
			} catch (Exception e) {
				System.out.println("Error UDPSender");
			System.err.println(e);
			}

		}
	
	public static void Send(String destinationIP, int PortValue, byte[] UDPMessage ) {
		try{
			// Get the internet address of the specified host
			InetAddress address = InetAddress.getByName(destinationIP);
			
			// Initialize a datagram packet with data and address
			
			
			DatagramPacket packet = new DatagramPacket(UDPMessage,UDPMessage.length, address, PortValue);
			
			//System.out.println("Address "+address+" portvalue "+PortValue +" Time "+new java.util.Date());
			//System.out.println("UDPMessage "+bytesToHex(UDPMessage));
			
			// Create a datagram socket, send the packet through it, close it.
			DatagramSocket dsocket = new DatagramSocket();
			dsocket.send(packet);
			dsocket.close();
			} catch (Exception e) {
				System.out.println("Error UDPSender");
			System.err.println(e);
			}

		}
	
	public static void main(String args[]) {
		
		
		byte[] cmd=hexStringToByteArray("AABB0100A0000D5F8501AACC00");
		UDPSender.Send("10.36.90.73","1003","AABB0100A0000D5F8501AACC00");
		UDPSender.Send("127.0.0.1","1003",cmd);
	}
	}

