package server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import start.SimpleRunner;

import database.JavaDBException;

import model.login.LoginMng;


// TODO: Auto-generated Javadoc
/**
 * La classe ServerProtocolMng si occupa di interpretare i messaggi ricevuti dai client
 * e elaborare le risposte
 * 
 * @author Marco
 */
public class ServerProtocolMng {
	
	/** The list update. */
	private List<String> listUpdate = new ArrayList<String>();
	
	/** The comunication database manager. */
	private ComunicationDBMng mComunicationDbMng;

	/**
	 * The Enum Tag.
	 */
	private enum Tag { /** The AUTENTICATION. */ AUT, 
					   /** The STOP_COMUNICATION. */ STP,
					   /** The CHECK_FOR_UPDATE. */ UPD, 
					   /** The UPDATE_LOAD. */ UPL, 
					   /** The REQUEST_RECEIVE_ORDER. */ RSO,
					   /** The RECEIVE_NEW_ORDER. */ SNO, 
					   /** The RECEIVE_ORDER_FINISH. */ SOF, 
					   /** The REQUEST_RECEIVE_DELETED_ORDER. */ RDO, 
					   /** The RECEIVE_DELETED_ORDER. */ SDO }
	
	/**
	 * Instantiates a new server protocol manager.
	 */
	public ServerProtocolMng(){
		mComunicationDbMng = new ComunicationDBMng();
	}
	
	/**
	 * Process input packet
	 *
	 * @param packet the packet
	 * @return the string message output
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public String processInput(String packet) throws JavaDBException, SQLException{
		String message;
		String tag = packet.substring(0, 3);
		switch (Tag.valueOf(tag)) {
		case AUT:
			message = autentication(packet);
			break;
		case STP:
			message = RestaurantProtocolServer.STOP_COMUNICATION;
			break;
		case UPD:
			message = checkForUpdate(packet);
			break;
		case UPL:
			message = loadUpdate();
			break;
		case RSO:
			message = RestaurantProtocolServer.AUTORIZATION_SEND_ORDER;
		break;
		case SNO:
			saveNewOrder(packet);
			message = RestaurantProtocolServer.AUTORIZATION_SEND_ORDER;
		break;
		case SOF:
			message = RestaurantProtocolServer.STOP_COMUNICATION;
		break;
		case RDO:
			message = RestaurantProtocolServer.AUTORIZATION_SEND_DELETED_ORDER;
		break;
		case SDO:
			saveDeletedOrder(packet);
			message = RestaurantProtocolServer.AUTORIZATION_SEND_DELETED_ORDER;
		break;
		default:
			message = "invalid";
		}
		
		
		return message;
	}
	
	/**
	 * Richiede l'Autenticazione.
	 *
	 * @param str the credential
	 * @return the string
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public String autentication(String str) throws JavaDBException, SQLException{
		String message;
		String[] msgBlocks = str.split("-");
		String[] credentials = new String[3];
		credentials[RestaurantProtocolServer.NAME_ID] = msgBlocks[1];
		credentials[RestaurantProtocolServer.SURNAME_ID] = msgBlocks[2];
		credentials[RestaurantProtocolServer.PASSWORD_ID] = msgBlocks[3];
		String id = LoginMng.autentication(credentials);
		if(id.equals("null")){
			message = "ERR-Autentication not valid";
		}else{
			message = "AUT-" + id;
		}
		return message;
	}
	
	/**
	 * Check for update.
	 *
	 * @param message the message
	 * @return the string
	 */
	private String checkForUpdate(String message){
		String[] block = new String[4];
		block = message.split("-");
		try {
			listUpdate = mComunicationDbMng.checkForUpdate(block[1]); // farsi restituire una lista con tutto il menu e elenco tavoli
																	  // quando vengono fatte delle modifiche alle tabelle settare un
																	  // campo a true
		} catch (JavaDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		String packet;
		if(listUpdate != null)
			packet = RestaurantProtocolServer.UPDATE_TRUE + "-" + "update presenti";
		else
			packet = RestaurantProtocolServer.UPDATE_FALSE + "-" + "update non presenti";
		return packet;
	}
	
	/**
	 * Load update.
	 *
	 * @return the string
	 */
	private String loadUpdate(){
		String packet;
		if(!listUpdate.isEmpty()){
			packet = RestaurantProtocolServer.UPDATE_LOAD + "-" + listUpdate.get(0);
			listUpdate.remove(0);
		}else{
			packet = RestaurantProtocolServer.UPDATE_FALSE;
		}
		return packet;
	}
	
	/**
	 * Save new order.
	 *
	 * @param packet the packet
	 */
	private void saveNewOrder(String packet){
		//mComunicationDbMng.listNewOrder.add(packet.substring(4));		// al posto della lista di controlDB mettere
																// la lista delle ordinazioni
		SimpleRunner.addNewOrder(packet.substring(4));
	}

	/**
	 * Save deleted order.
	 *
	 * @param packet the packet
	 */
	private void saveDeletedOrder(String packet){
		//mComunicationDbMng.listNewOrder.add(packet.substring(4));		// al posto della lista di controlDB mettere
																// la lista delle ordinazioni
		SimpleRunner.addDeletedOrder(packet.substring(4));
	}

}
