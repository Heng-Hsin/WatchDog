package WatcherPack;

import java.awt.EventQueue;
import java.io.PrintStream;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class WatchDog_UI extends JFrame {

	private JPanel contentPane;
	private PrintStream standardOut;
	private static JTextField textField;
	public static boolean Dog_switch =false;
	private static JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WatchDog_UI frame = new WatchDog_UI();
					frame.setVisible(true);
					Listen_process();
					Showing_Time_process();
					//System.out.println("Yeah!~");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void Listen_process(){
		Thread thread = new Thread(new Runnable() {
            @Override
            public void run() { 
            	
            	WatchDogProcess.listening(textField.getText());

            	}
        });
        thread.start();
		
	}
	
	public static void Showing_Time_process(){
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run(){
				
				while(true){
					try{
						Date now= new java.util.Date();
						lblNewLabel.setText(now.toString());
						Thread.sleep(1000);
					}catch(Exception e){
						e.printStackTrace();
						System.out.println("Error in Showing_Time_process");
					}					
				}
			}
		});
		thread.start();
		
		}
			

	/**
	 * Create the frame.
	 */
	public WatchDog_UI() {
		super(" WatchDog Prototype ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 22, 638, 388);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("System.out", null, scrollPane, null);
		
		// keeps reference of standard output stream
        standardOut = System.out;
         
        // re-assigns standard output stream and error output stream     
		
		final JTextArea textArea = new JTextArea();
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		scrollPane.setViewportView(textArea);		
		textArea.setEditable(false);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Settings", null, panel, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Listening Port :");
		label.setBounds(10, 10, 94, 33);
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(114, 16, 77, 21);
		panel.add(textField);
		textField.setColumns(10);
		textField.setText("20000");
		
		 System.setOut(printStream);
	     System.setErr(printStream);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Thread thread = new Thread(new Runnable() {
		            @Override
		            public void run() { 
		            	Dog_switch=true;		            	

		            	}
		        });
		        thread.start();
			}
		});
		btnNewButton.setBounds(658, 96, 95, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Stop");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dog_switch=false;
				
			}
		});
		btnNewButton_1.setBounds(658, 300, 95, 29);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel = new JLabel("Time");
		lblNewLabel.setBounds(386, 10, 234, 29);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread thread = new Thread(new Runnable() {
		            @Override
		            public void run() { 
		            	
		            	textArea.setText("");

		            	}
		        });
		        thread.start();
				
			}
		});
		btnNewButton_2.setBounds(658, 247, 95, 29);
		contentPane.add(btnNewButton_2);
	}
}
