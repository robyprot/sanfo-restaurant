package server;

// TODO: Auto-generated Javadoc
/**
 * La classe RestaurantProtocolServer descrive il protocollo di comunicazione utilizzato dal server
 * 
 * @author Marco
 */
public class RestaurantProtocolServer {
	
	/** The Constant AUTENTICATION. */
	public static final String AUTENTICATION = "AUT";
	
	/** The Constant STOP_COMUNICATION. */
	public static final String STOP_COMUNICATION = "STP";
	
	/** The Constant CHECK_FOR_UPDATE. */
	public static final String CHECK_FOR_UPDATE = "UPD";  // controllo se ci sono aggiornamenti
	
	/** The Constant UPDATE_TRUE. */
	public static final String UPDATE_TRUE = "UPT";		  // update presenti
	
	/** The Constant UPDATE_FALSE. */
	public static final String UPDATE_FALSE = "UPF";	  // update non presenti
	
	/** The Constant UPDATE_LOAD. */
	public static final String UPDATE_LOAD = "UPL";		  // caricamento update
	
	/** The Constant REQUEST_RECEIVE_ORDER. */
	public static final String REQUEST_RECEIVE_ORDER = "RSO";
	
	/** The Constant RECEIVE_NEW_ORDER. */
	public static final String RECEIVE_NEW_ORDER  = "SNO";
	
	/** The Constant AUTORIZATION_SEND_ORDER. */
	public static final String AUTORIZATION_SEND_ORDER  = "ASO";
	
	/** The Constant RECEIVE_ORDER_FINISH. */
	public static final String RECEIVE_ORDER_FINISH = "SOF";
	
	/** The Constant REQUEST_RECEIVE_DELETED_ORDER. */
	public static final String REQUEST_RECEIVE_DELETED_ORDER = "RDO";
	
	/** The Constant AUTORIZATION_SEND_DELETED_ORDER. */
	public static final String AUTORIZATION_SEND_DELETED_ORDER  = "ASD";
	
	/** The Constant RECEIVE_DELETED_ORDER. */
	public static final String RECEIVE_DELETED_ORDER  = "SDO";
	
	/*
	 * Credential
	 */
	/** The Constant NAME_ID. */
	public static final int NAME_ID = 0;
	
	/** The Constant SURNAME_ID. */
	public static final int SURNAME_ID = 1;
	
	/** The Constant PASSWORD_ID. */
	public static final int PASSWORD_ID = 2;
	
}
