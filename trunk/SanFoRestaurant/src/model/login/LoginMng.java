package model.login;

import java.sql.ResultSet;
import java.sql.SQLException;

import server.RestaurantProtocolServer;

import database.Database;
import database.JavaDBException;


// TODO: Auto-generated Javadoc
/**
 * Classe che gestisce l'interazione e l'autenticazione dell'utente 
 * con il database che riguarda la parte Login.
 * 
 * @author Mauro
 */
public class LoginMng {

	/** The instance. */
	private static LoginMng instance = null;

	
	/**
	 * Istanzia un nuovo Login Manager.
	 */
	private LoginMng(){}

	
	/**
	 * Ritorna l'istanza.
	 *
	 * @return l'istanza
	 */
	public static LoginMng getIstance() {
		if (instance == null) {
			synchronized (LoginMng.class) {
				if (instance == null) {
					instance = new LoginMng();
				}
			}
		}
		return instance;
	}
	
	
	
	// Metodo che controlla Username e Password
	// Se presenti nel DB --> ritorna se l'accesso è effettuato da un amministratore o da un cameriere
	// se non presenti	  --> stringa Negative
	/**
	 * Verifica dell'identità.
	 *
	 * @param username l'username
	 * @param password la password
	 * @return una stringa che contiene il tipo di utente oppure se l'accesso è negato
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public String identityVerification(String username, String password) throws SQLException, JavaDBException {
		Database.connect();
		String result = new String();
		String qry = "SELECT Type FROM APP.Users WHERE Name = '" +username+ "' and Password = '" +password+ "'";
		ResultSet res = Database.interrogate(qry);
		if(res.next()) {
			result = res.getString("Type");
		}
		else {
			result = "Negative";
		}
		Database.disconnect();
		return result;	
	}
	
	
	
	
	/**
	 * Autenticazione.
	 *
	 * @param credentials le credenziali
	 * @return una stringa
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public static String autentication(String[] credentials) throws JavaDBException, SQLException{
		String id;
		Database.connect();
		String qry = "SELECT ID FROM APP.Users WHERE Name = '" +credentials[RestaurantProtocolServer.NAME_ID]+
				credentials[RestaurantProtocolServer.SURNAME_ID]+ "' and Password = '" +
				credentials[RestaurantProtocolServer.PASSWORD_ID]+ "'";
		
		ResultSet res = Database.interrogate(qry);
		if(res.next()) {
			id = res.getString("ID");
		}
		else {
			id = "null" ;
		}
		Database.disconnect();
		return id;	
	}
	
	
	
	public String checkIsFirst() throws JavaDBException, SQLException{
		String string = null;
		Database.connect();
		String qry = "SELECT Value FROM APP.Settings WHERE NameParam = 'IsFirst'";
		ResultSet res = Database.interrogate(qry);
		while (res.next()){
			string = res.getString("Value");
		}
		return string;
	}

	
	
	
}
