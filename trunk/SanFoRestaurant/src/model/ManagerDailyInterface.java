package model;

import java.sql.SQLException;

import database.JavaDBException;

/**Interfaccia che dichiare il metodo di inizializzazione per tutti le classi manager di Daily
 * 
 * @author Marco
 *
 */
public interface ManagerDailyInterface {

	/**
	 * Inizializazin for manager.
	 */
	public void init() throws ClassNotFoundException, SQLException, JavaDBException;
}
