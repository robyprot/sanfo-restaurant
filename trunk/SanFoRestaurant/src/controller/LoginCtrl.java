package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import model.login.LoginMng;
import start.SimpleRunner;
import view.AdminView;
import view.DailyView;
import view.LoginView;
import database.JavaDBException;

// TODO: Auto-generated Javadoc
/**
 * Classe Controller Login che intercetta gli eventi della view Login 
 * e fa da tramite tra quest'ultima e il model Login Manager.
 * 
 * @author Mauro
 */
public class LoginCtrl implements ActionListener {

	/** The login mng. */
	public LoginMng loginMng;
	
	/** The view admin. */
	public AdminView viewAdmin;
	
	/** The view daily. */
	public DailyView viewDaily; 
	
	
	
	/**
	 * Istanzia un nuovo controllore Login.
	 *
	 * @param loginMng il manager Login
	 */
	public LoginCtrl(LoginMng loginMng){
		this.loginMng = loginMng;
	}
	
	/**
	 * Istanzia un nuovo controllore Login.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public LoginCtrl() throws ClassNotFoundException, SQLException, JavaDBException {
		this.loginMng = LoginMng.getIstance();
	}
	
	
	
	
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		// estraggo la sorgente dell'evento
		Object sourceObj = e.getSource();	
		
		// se proviene da un BOTTONE, creo un bottone e individuo quale dei bottoni presenti l'ha generato
		if (sourceObj.getClass().toString().equalsIgnoreCase("class javax.swing.JButton")){		
			JButton source = (JButton) e.getSource();											
		
			// Bottone OK
			if (source.getText().equalsIgnoreCase("OK")) {
				System.out.println("Click OK Login");
				try {
					String isFirst = loginMng.checkIsFirst();
				} catch (JavaDBException e2) {
					e2.printStackTrace();
				} catch (SQLException e2) {
					e2.printStackTrace();
					System.out.println("Database CREATO");
				}
				String username = LoginView.getUsername();
				String password = LoginView.getPassword();
				String type = new String();
				try {
					type = loginMng.identityVerification(username, password);		// identificazione
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (JavaDBException e1) {
					e1.printStackTrace();					
				}
				if (type.equalsIgnoreCase("Admin")) {						// Caso: Admin
					try {
						LoginView.getIstance().closeWindow();				// chiudo il frame Login
						viewAdmin = new AdminView();						// apro il frame Admin
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					}
				}
				else if (type.equalsIgnoreCase("Waiter")) {					// Caso: Waiter
					try {
						LoginView.getIstance().closeWindow();				// chiudo il frame Login
						viewDaily = new DailyView();						// apro il frame Daily
						
						SimpleRunner runner = new SimpleRunner();
						Thread orderListener = new Thread(runner);
						orderListener.start();
						SimpleRunner.setSimpleRunnerActive(true);
					
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					}
				}
				else {														// Caso: Dati errati
					try {
						LoginView.getIstance().deleteText();				// cancello testo nelle caselle di testo
						LoginView.getIstance().textError();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					}
				}	
			}
			
			// Bottone PASSA A SERATA
			if (source.getText().equalsIgnoreCase("Passa a serata")) {
				System.out.println("Click PASSA A SERATA");
				try {
					AdminView.disablePassaASerata();
					viewDaily = new DailyView();				// apro il frame Daily
					
					SimpleRunner runner = new SimpleRunner();
					Thread orderListener = new Thread(runner);
					orderListener.start();
					SimpleRunner.setSimpleRunnerActive(true);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (JavaDBException e1) {
					e1.printStackTrace();
				}
				
				
			}
		}
	}
	
	
}
