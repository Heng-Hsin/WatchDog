package DummyPack;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Dummy_UI extends JFrame {
	
	public static String JavaPID="";
	public static JLabel lblNewLabel_1 ;
	private JPanel contentPane;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dummy_UI frame = new Dummy_UI();
					frame.setVisible(true);
					JavaPID=ManagementFactory.getRuntimeMXBean().getName();
					lblNewLabel_1.setText(JavaPID);
					initHeartBeat();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void initHeartBeat() {
		Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
            		            		
            		try{		            									
            		
            			String msg="";
            			
            		while(true){
            			
            			Thread.sleep(3000);
            			byte[] cmd3=MessageCreator.CreateHeartBeat_Msg(JavaPID);
            			UDPSender.Send("127.0.0.1",20000,cmd3);
                                    	    
            		}
       	            			
	            	}catch(Exception e){
	            		e.printStackTrace();
	            		System.out.println("Error in initHeartBeat ");			            				            		
	            	}
            	
            }
        });
        thread.start();
	}

	/**
	 * Create the frame.
	 */
	public Dummy_UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("0");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 17));
		lblNewLabel.setBounds(24, 57, 110, 36);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread thread = new Thread(new Runnable() {
		            @Override
		            public void run() {
		            		            		
		            		try{		            									
		            			int counter=0;
		            		while(true){
		            			String counter_str=String.valueOf(counter);
		            			lblNewLabel.setText(counter_str);
		            			counter++;
		            			Thread.sleep(1000);
		            		}
		       	            			
			            	}catch(Exception e){
			            		e.printStackTrace();
			            		System.out.println("Error in Dummy Counter ");			            				            		
			            	}
		            	
		            }
		        });
		        thread.start();
				
			}
		});
		btnNewButton.setBounds(156, 162, 110, 28);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("PID");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(24, 117, 186, 36);
		contentPane.add(lblNewLabel_1);
	}
}
