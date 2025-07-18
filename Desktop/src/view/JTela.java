package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Comando;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.io.IOException;

import javax.swing.JButton;

public class JTela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private static final String START = "npm start";
	private static final String DIR_BACKEND = "C:\\contador-acidentes\\backendAcidente";

	private static final String DIR_FRONT = "C:\\contador-acidentes\\acidente-planetaagua";
	
		
	private Comando cmdBack = new Comando(START,DIR_BACKEND,3002);
	private Comando cmdFront = new Comando(START,DIR_FRONT,3001);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTela frame = new JTela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JTela() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 464);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);
		panel.setBounds(80, 11, 473, 404);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BACKEND-ACIDENTES");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(147, 112, 182, 21);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("FRONTEND-ACIDENTES");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(147, 230, 182, 21);
		panel.add(lblNewLabel_1);
		
		JButton btnIniciaBack = new JButton("START");
		btnIniciaBack.setBounds(147, 144, 89, 23);
		panel.add(btnIniciaBack);
		
		btnIniciaBack.addActionListener(e ->{
				
			 
			
			  new Thread(()->{ try { cmdBack.execCMD();
			  		  
			  } catch (IOException | InterruptedException err) {
			  
			  err.printStackTrace(); }
			  
			  }).start();
			 
		
		});
		
		JButton btnStopBack = new JButton("STOP");
		btnStopBack.setBounds(246, 144, 89, 23);
		panel.add(btnStopBack);
		
		btnStopBack.addActionListener(e ->{	
			  new Thread(()->{ cmdBack.parar();
			  	}).start();
		});
		
		
		JButton btnFrontend = new JButton("START");
		btnFrontend.setBounds(147, 262, 89, 23);
		panel.add(btnFrontend);
		
		JButton btnStopFront = new JButton("STOP");
		btnStopFront.setBounds(246, 262, 89, 23);
		panel.add(btnStopFront);
		
		btnFrontend.addActionListener(e ->{
			
			 
			
			  new Thread(()->{ try { cmdFront.execCMD();
			  		  
			  } catch (IOException | InterruptedException err) {
			  
			  err.printStackTrace(); }
			  
			  }).start();
			 
		
		});
		
		btnStopFront.addActionListener(e ->{	
			  new Thread(()->{ cmdFront.parar();
			  	}).start();
		});
		
		
		
		
	}
}
