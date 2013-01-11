package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.daily.ClientMngDaily;

import controller.LoginCtrl;
import database.JavaDBException;
import javax.swing.JEditorPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

// TODO: Auto-generated Javadoc
/**
 * Classe Login view che contiene tutti i componenti grafici per creare l'interfaccia iniziale di Login
 * e tutti i metodi per gestirla.
 * 
 * @author Mauro
 */
public class LoginView extends JFrame {

	/** The instance. */
	private static LoginView instance = null;
	
	/** The controller. */
	private LoginCtrl controller;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The text username. */
	private static JTextField textUsername;
	
	/** The text password. */
	private static JPasswordField textPassword;
	
	/** The btn ok. */
	private static JButton btnOk;
	
	/** The text field. */
	private static JTextField textField;
	
	/** The label Caricamento */
	private static JLabel lblCaricamento;
	
	/**
	 * Create the frame.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	
	// constructor
	private LoginView() throws ClassNotFoundException, SQLException, JavaDBException {
		setTitle("Login");
		init();
	}
	
	
	/**
	 * Inits the.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	private void init() throws ClassNotFoundException, SQLException, JavaDBException {
		
		this.controller = new LoginCtrl();
	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(screenSize.width/2, screenSize.height/2, 400, 300);

		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblUsername.setBounds(46, 53, 140, 30);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblPassword.setBounds(46, 102, 119, 30);
		contentPane.add(lblPassword);
		
		textUsername = new JTextField();
		textUsername.setBounds(196, 53, 140, 30);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(196, 102, 140, 30);
		contentPane.add(textPassword);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(196, 174, 140, 46);
		contentPane.add(btnOk);
		
		textField = new JTextField();
		textField.setForeground(Color.RED);
		textField.setBounds(196, 143, 140, 20);
		textField.setColumns(10);
		textField.setVisible(false);
		contentPane.add(textField);
	
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("logoRistorante.png")));
		lblNewLabel.setBounds(54, 164, 100, 83);
		contentPane.add(lblNewLabel);
		
		lblCaricamento = new JLabel("Caricamento Database in corso ..... Attendere Prego");
		lblCaricamento.setForeground(Color.RED);
		lblCaricamento.setBounds(10, 11, 307, 20);
		contentPane.add(lblCaricamento);
		
		
		
		btnOk.addActionListener(controller);
		this.setVisible(true);
	}

	
	
	/**
	 * Gets the istance.
	 *
	 * @return the istance
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public static LoginView getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (LoginView.class) {
				if (instance == null) {
					instance = new LoginView();
				}
			}
		}
		return instance;
	}
	

	
	/**
	 * Metodo per chiudere il frame.
	 */
	public void closeWindow() {
		this.dispose();
	}
	
	 
	/**
	 * Metodo per scrivere nella label Caricamento
	 */
	public void writeLabelCaricamento(String s){
		lblCaricamento.setText(s);
	}
		
	
    /**
	 * Metodo per centrare la finestra Login.
	 *
	 * @param frame the frame
	 */
	public static void centerTheGUIFrame(JFrame frame) {
        int widthWindow = frame.getWidth();
        int heightWindow = frame.getHeight();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int X = (screen.width / 2) - (widthWindow / 2); // Center horizontally.
        int Y = (screen.height / 2) - (heightWindow / 2); // Center vertically.
        frame.setBounds(X, Y, widthWindow, heightWindow);
    }
    
    
   
    /**
     * Metodo per recuperare il contenuto di textUsername.
     *
     * @return l'username
     */
    public static String getUsername(){
    	return textUsername.getText();
    }
    
    
    /**
     * Metodo per recuperare il contenuto di textPassword.
     *
     * @return la password
     */
    public static String getPassword(){
    	return textPassword.getText();
    }
    
    
    
    /**
     * Metodo per cancellare il contenuto di textUsername e textPassword.
     */
    public static void deleteText(){
    	textUsername.setText("");
    	textPassword.setText("");
    }
    
    
    
    /**
     * Metodo per settare la text in caso di errore.
     */
    public static void textError(){
    	textField.setVisible(true);
    	textField.setText("Autenticazione Errata");
    	textField.setEditable(false);
    }
}
	
