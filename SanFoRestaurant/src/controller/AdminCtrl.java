package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;

import model.admin.AdminMng;
import model.daily.Client;
import model.daily.Food;
import model.daily.NearTable;
import model.daily.Reservation;
import model.daily.Room;
import model.daily.Settings;
import model.daily.Table;
import model.daily.TablesCheck;
import model.daily.UsersLogin;
import model.daily.Waiter;
import server.Server;
import view.AdminView;
import database.Database;
import database.JavaDBException;


// TODO: Auto-generated Javadoc
/**
 * Classe Controller Amministrazione che intercetta gli eventi della view Admin 
 * e fa da tramite tra quest'ultima e il model Admin Manager.
 * 
 * @author Mauro
 */
public class AdminCtrl implements ActionListener {

	/** The admin mng. */
	public AdminMng adminMng;
	
	/** The status combo box1. */
	public String statusComboBox1 = null;
	
	/** The status combo box2. */
	public String statusComboBox2 = null;
	
	/** The first login. */
	private boolean firstLogin;
	
	

	/**
	 * Istanzia un nuovo controllore Admin.
	 *
	 * @param adminMng il manager Admin
	 */
	public AdminCtrl(AdminMng adminMng){
		this.adminMng = adminMng;
	}
	
	/**
	 * Istanzia un nuovo controllore Admin.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public AdminCtrl() throws ClassNotFoundException, SQLException, JavaDBException {
		this.adminMng = AdminMng.getIstance();
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
			
			// Bottone SALVA
			if (source.getText().equalsIgnoreCase("Salva")) {										
				System.out.println("Click Salva");
				
				// SALVA Clienti
				if (statusComboBox2.equalsIgnoreCase("Clienti")){
					String translated = AdminView.translator(statusComboBox2);   	// nome tabella DB
					ArrayList<Client> cl = AdminView.getClientFromJTable();			// recupero i clienti visualizzati nella JTable
					try {
						adminMng.insertClientIntoDB(cl);			// li aggiorno nel DB
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}							
				}
				// SALVA Cibi
				if (statusComboBox2.equalsIgnoreCase("Cibi")){
					String translated = AdminView.translator(statusComboBox2);   	// nome tabella DB
					ArrayList<Food> food = AdminView.getFoodFromJTable();			// recupero i cibi visualizzati nella JTable
					try {
						adminMng.insertFoodIntoDB(food);		// li aggiorno nel DB
						adminMng.setTrueUsers();				// segnalo la modifica in Users
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}												
				}
				// SALVA Sale
				if (statusComboBox2.equalsIgnoreCase("Sale")){
					String translated = AdminView.translator(statusComboBox2);   	// nome tabella DB
					ArrayList<Room> room = AdminView.getRoomFromJTable();			// recupero le sale visualizzate nella JTable
					try {
						adminMng.insertRoomIntoDB(room);			// li aggiorno nel DB
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}							
								
				}
				// SALVA Tavoli
				if (statusComboBox2.equalsIgnoreCase("Tavoli")){
					String translated = AdminView.translator(statusComboBox2);   	// nome tabella DB
					ArrayList<Table> table = AdminView.getTableFromJTable();		// recupero i tavoli visualizzati nella JTable
					try {
						adminMng.insertTableIntoDB(table);			// li aggiorno nel DB
						adminMng.setTrueUsers();					// segnalo la modifica in Users
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}					
				}
				// SALVA Camerieri
				if (statusComboBox2.equalsIgnoreCase("Camerieri")){
					String translated = AdminView.translator(statusComboBox2);   	// nome tabella DB
					ArrayList<Waiter> waiters = AdminView.getWaiterFromJTable();	// recupero i camerieri visualizzati nella JTable
					try {
						adminMng.insertWaiterIntoDB(waiters);		// li aggiorno nel DB
						// aggiornamento della tabella Users in seguito ad una modifica della tabella Waiters
						Database.connect();
						ResultSet res = adminMng.getAllWaiters();	// estraggo tutti i camerieri
						adminMng.deleteWaitersIntoUsers();			// cancello tutti i camerieri dalla tabella Users
						adminMng.insertWaitersIntoUsers(res);		// aggiorno la tabella Users con tutti i camerieri
						Database.disconnect();
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}							
				}
				// SALVA Prenotazioni
				if (statusComboBox2.equalsIgnoreCase("Prenotazioni")){
					String translated = AdminView.translator(statusComboBox2);   		// nome tabella DB
					ArrayList<Reservation> res = new ArrayList<Reservation>();
					try {
						res = AdminView.getReservationFromJTable();						// recupero le prenotazioni visualizzate nella JTable
					} catch (ParseException e2) {
						e2.printStackTrace();
					}												
					try {
						adminMng.insertReservationIntoDB(res);		// li aggiorno nel DB
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}														
				}
				// SALVA Conti Tavoli
				if (statusComboBox2.equalsIgnoreCase("Conti Tavoli")){
					String translated = AdminView.translator(statusComboBox2);   		// nome tabella DB
					ArrayList<TablesCheck> check = new ArrayList<TablesCheck>();
					try {
						check = AdminView.getTablesCheckFromJTable();					// recupero i conti tavoli visualizzati nella JTable
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					try {
						adminMng.insertTablesCheckIntoDB(check);		// li aggiorno nel DB
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}			
				}
				// SALVA Tavoli Vicini
				if (statusComboBox2.equalsIgnoreCase("Tavoli Vicini")){
					String translated = AdminView.translator(statusComboBox2);   		// nome tabella DB
					ArrayList<NearTable> near = AdminView.getNearTableFromJTable();		// recupero i tavoli vicini visualizzati nella JTable
					try {
						adminMng.insertNearTableIntoDB(near);			// li aggiorno nel DB
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}												
				}
				// SALVA Users Login
				if (statusComboBox2.equalsIgnoreCase("Utenti Login")){
					String translated = AdminView.translator(statusComboBox2);   		// nome tabella DB
					ArrayList<UsersLogin> user = AdminView.getUsersLoginFromJTable();	// recupero gli utenti visualizzati nella JTable
					try {
						adminMng.insertUsersLoginIntoDB(user);			// li aggiorno nel DB
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}											
				}
				// SALVA Settings
				if (statusComboBox2.equalsIgnoreCase("Settaggi")){
					String translated = AdminView.translator(statusComboBox2);   		// nome tabella DB
					ArrayList<Settings> settings = AdminView.getSettingsFromJTable();	// recupero i settaggi visualizzati nella JTable
					try {
						adminMng.insertSettingsIntoDB(settings);			// li aggiorno nel DB
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}											
				}
			}
			
			
			// Bottone INSERISCI
			if (source.getText().equalsIgnoreCase("Inserisci")){
				System.out.println("Click Inserisci");
				AdminView.insertEmptyRow();
			}
			
			// Bottone ELIMINA
			if (source.getText().equalsIgnoreCase("Elimina")){
				System.out.println("Click Elimina");
				AdminView.deleteSelectedRow();
			}
			
			// Bottone ANNULLA
			if (source.getText().equalsIgnoreCase("Annulla")){
				System.out.println("Click Annulla");
				AdminView.init();
			}
			
			// Bottone INSERISCI NUOVO AMMINISTRATORE
			if (source.getText().equalsIgnoreCase("Inserisci Nuovo Amministratore")){
				System.out.println("Click Inserisci nuovo amministratore");
				int numAdmin = 0;
				try {
					numAdmin = adminMng.getNumbAdmin();			// estraggo il numero di amministratori
				} catch (JavaDBException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				numAdmin++;
				AdminView.insertAdmin(numAdmin);
			}
			
			// Bottone LOGOUT
			if (source.getText().equalsIgnoreCase("Logout")){
				System.out.println("Click Logout");
				Server.setServerActive(false);
				System.exit(0);
			}
			
			// Bottone OK
			if (source.getText().equalsIgnoreCase("OK")) {
				System.out.println("Click OK");
				// OK - Visualizza
				if (statusComboBox1.equalsIgnoreCase("Visualizza")){
					// OK - Visualizza - Clienti
					if (statusComboBox2.equalsIgnoreCase("Clienti")){
						try {
							Database.connect();
							ResultSet res = adminMng.getAllClients();
							AdminView.viewClientTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.disableTable();
					}
					// OK - Visualizza - Cibi
					if (statusComboBox2.equalsIgnoreCase("Cibi")){
						try {
							Database.connect();
							ResultSet res = adminMng.getAllFood();
							AdminView.viewFoodTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.disableTable();
					}
					// OK - Visualizza - Sale
					if (statusComboBox2.equalsIgnoreCase("Sale")){
						try {
							Database.connect();
							ResultSet res = adminMng.getAllRooms();
							AdminView.viewRoomTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.disableTable();
					}
					// OK - Visualizza - Tavoli 
					if (statusComboBox2.equalsIgnoreCase("Tavoli")){
						try {
							Database.connect();
							ResultSet res = adminMng.getAllTables();
							AdminView.viewTablesTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.disableTable();
					}
					// OK - Visualizza - Camerieri
					if (statusComboBox2.equalsIgnoreCase("Camerieri")){
						try {
							Database.connect();
							ResultSet res = adminMng.getAllWaiters();
							AdminView.viewWaiterTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.disableTable();
					}
					// OK - Visualizza - Prenotazioni
					if (statusComboBox2.equalsIgnoreCase("Prenotazioni")){
						try {
							Database.connect();
							ResultSet res = adminMng.getAllReservations();
							AdminView.viewReservationTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.disableTable();
					}
					// OK - Visualizza - Conti Tavoli
					if (statusComboBox2.equalsIgnoreCase("Conti Tavoli")){
						try {
							Database.connect();
							ResultSet res = adminMng.getAllTablesCheck();
							AdminView.viewCheckTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.disableTable();
					}
					// OK - Visualizza - Tavoli Vicini
					if (statusComboBox2.equalsIgnoreCase("Tavoli Vicini")){
						try {
							Database.connect();
							ResultSet res = adminMng.getAllNearTable();
							AdminView.viewNearTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.disableTable();
					}
					// OK - Visualizza - Utenti Login
					if (statusComboBox2.equalsIgnoreCase("Utenti Login")){
						try {
							Database.connect();
							firstLogin = adminMng.getFirstLogin();		// controllo se è la prima volta che effettuo il login
							System.out.println(firstLogin);
							if (firstLogin==true){
								// carica camerieri e inseriscili nella tabella Users su DB
								ResultSet waiters = adminMng.getWaitersForLogin();
								/*while(waiters.next()){
									int numb = waiters.getInt("Numb");
									String surname = waiters.getString("Surname");
									String name = waiters.getString("Name");
									System.out.println(numb+ " " +surname+ " " +name);
								}*/
								waiters.beforeFirst();
								adminMng.insertUsersLogin(waiters);
								adminMng.setFirstLogin(false);
								firstLogin = false;
							}
							ResultSet res = adminMng.getAllUsersLogin();
							AdminView.viewUsersLogin(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}						
						AdminView.disableTable();
					}
					// OK - Visualizza - Settaggi
					if (statusComboBox2.equalsIgnoreCase("Settaggi")){
						try {
							Database.connect();
							ResultSet res = adminMng.getAllSettings();
							AdminView.viewSettings(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.disableTable();
					}
					
				}
				
				// OK - Modifica (Fa solo visualizzare - per modificare devo cliccare SALVA dopo la modifica)
				if (statusComboBox1.equalsIgnoreCase("Modifica")){
					// OK - Modifica - Clienti
					if (statusComboBox2.equalsIgnoreCase("Clienti")){
						AdminView.enableSalva();
						AdminView.enableInserisci();
						AdminView.enableElimina();
						try {
							Database.connect();
							ResultSet res = adminMng.getAllClients();
							AdminView.viewClientTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.enableTable();
					}
					// OK - Modifica - Cibi
					if (statusComboBox2.equalsIgnoreCase("Cibi")){
						AdminView.enableSalva();
						AdminView.enableInserisci();
						AdminView.enableElimina();
						try {
							Database.connect();
							ResultSet res = adminMng.getAllFood();
							AdminView.viewFoodTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.enableTable();
					}
					// OK - Modifica - Sale
					if (statusComboBox2.equalsIgnoreCase("Sale")){
						AdminView.enableSalva();
						AdminView.enableInserisci();
						AdminView.enableElimina();
						try {
							Database.connect();
							ResultSet res = adminMng.getAllRooms();
							AdminView.viewRoomTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.enableTable();
					}
					// OK - Modifica - Tavoli
					if (statusComboBox2.equalsIgnoreCase("Tavoli")){
						AdminView.enableSalva();
						AdminView.enableInserisci();
						AdminView.enableElimina();
						try {
							Database.connect();
							ResultSet res = adminMng.getAllTables();
							AdminView.viewTablesTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.enableTable();
					}
					// OK - Modifica - Camerieri
					if (statusComboBox2.equalsIgnoreCase("Camerieri")){
						AdminView.enableSalva();
						AdminView.enableInserisci();
						AdminView.enableElimina();
						try {
							Database.connect();
							ResultSet res = adminMng.getAllWaiters();
							AdminView.viewWaiterTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.enableTable();
					}
					// OK - Modifica - Prenotazioni
					if (statusComboBox2.equalsIgnoreCase("Prenotazioni")){
						AdminView.enableSalva();
						AdminView.enableInserisci();
						AdminView.enableElimina();
						try {
							Database.connect();
							ResultSet res = adminMng.getAllReservations();
							AdminView.viewReservationTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.enableTable();
					}
					// OK - Modifica - Conti Tavoli
					if (statusComboBox2.equalsIgnoreCase("Conti Tavoli")){
						AdminView.enableSalva();
						AdminView.enableInserisci();
						AdminView.enableElimina();
						try {
							Database.connect();
							ResultSet res = adminMng.getAllTablesCheck();
							AdminView.viewCheckTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.enableTable();
					}
					// OK - Modifica - Tavoli Vicini
					if (statusComboBox2.equalsIgnoreCase("Tavoli Vicini")){
						AdminView.enableSalva();
						AdminView.enableInserisci();
						AdminView.enableElimina();
						try {
							Database.connect();
							ResultSet res = adminMng.getAllNearTable();
							AdminView.viewNearTable(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.enableTable();
					}
					// OK - Modifica - Utenti Login
					if (statusComboBox2.equalsIgnoreCase("Utenti Login")){
						try {
							Database.connect();
							firstLogin = adminMng.getFirstLogin();		// controllo se è la prima volta che effettuo il login
							if (firstLogin==true){
								// carica camerieri e inseriscili nella tabella Users su DB
								ResultSet waiters = adminMng.getWaitersForLogin();
								/*while(waiters.next()){
									int numb = waiters.getInt("Numb");
									String surname = waiters.getString("Surname");
									String name = waiters.getString("Name");
									System.out.println(numb+ " " +surname+ " " +name);
								}*/
								waiters.beforeFirst();
								adminMng.insertUsersLogin(waiters);
								adminMng.setFirstLogin(false);
								firstLogin = false;
							}
							ResultSet res = adminMng.getAllUsersLogin();
							AdminView.viewUsersLogin(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}	
						AdminView.enableSalva();
						AdminView.enableTable();
						AdminView.enableNuovoAdmin();
						AdminView.enableElimina();
						AdminView.disableInserisci();
					}
					// OK - Modifica - Settaggi
					if (statusComboBox2.equalsIgnoreCase("Settaggi")){
						AdminView.enableSalva();
						try {
							Database.connect();
							ResultSet res = adminMng.getAllSettings();
							AdminView.viewSettings(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.enableTable();
					}
				}
				
				// OK - Statistiche
				if (statusComboBox1.equalsIgnoreCase("Statistiche")){
					// OK - Statistiche - Guadagno
					if (statusComboBox2.equalsIgnoreCase("Guadagno")){
						try {
							String dataStart = AdminView.getDataStart();	//estraggo la data start come stringa
							String dataStop = AdminView.getDataStop();		//estraggo la data stop come stringa
							String qry = "SELECT * " +
										 "FROM APP.TABLESCHECK " +
										 "WHERE Date >= '" +dataStart+ "' and Date <= '" +dataStop+ "'";
							ResultSet res;
							Database.connect();
							res = Database.interrogate(qry);
							AdminView.viewProfit(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
								e1.printStackTrace();
						} catch (SQLException e1) {
								e1.printStackTrace();
						} 
						AdminView.disableTable();
					}
					// OK - Statistiche - Numero Clienti
					if (statusComboBox2.equalsIgnoreCase("Numero Clienti")){
						try {
							String dataStart = AdminView.getDataStart();	//estraggo la data start come stringa
							String dataStop = AdminView.getDataStop();		//estraggo la data stop come stringa
							String qry = "SELECT * " +
										 "FROM APP.TABLESCHECK " +
										 "WHERE Date >= '" +dataStart+ "' and Date <= '" +dataStop+ "'";
							ResultSet res;
							Database.connect();
							res = Database.interrogate(qry);
							AdminView.viewClients(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
								e1.printStackTrace();
						} catch (SQLException e1) {
								e1.printStackTrace();
						}
						AdminView.disableTable();
					}
					// OK - Statistiche - Cliente più fedele
					if (statusComboBox2.equalsIgnoreCase("Cliente più fedele")){
						String qry = " SELECT Name, Surname, Counter" +
									 " FROM APP.Client" +
									 " WHERE Counter = (SELECT max(Counter) FROM APP.Client)";
						ResultSet res;
						try {
							Database.connect();
							res = Database.interrogate(qry);
							AdminView.viewBestClient(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.disableTable();
					}
					// OK - Statistiche - Clienti Vips
					if (statusComboBox2.equalsIgnoreCase("Clienti Vips")){
						String qry = " SELECT Name, Surname, Counter" +
			 			 " FROM APP.Client" +
			 			 " WHERE Counter>=5";
						ResultSet res;
						try {
							Database.connect();
							res = Database.interrogate(qry);
							AdminView.viewVipsClient(res);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.disableTable();
					}
					// OK - Statistiche - Piatto più ordinato
					if (statusComboBox2.equalsIgnoreCase("Piatto più ordinato")){
						String qry = "select max(NUMBER) " +
									 "from ( select sum(Quantity) as NUMBER " +
									 "from APP.SingleOrder " +
									 "group by Dish " +
									 "order by sum(Quantity)) tab";
						ResultSet res;
						int max = 0;
						try {
							Database.connect();
							res = Database.interrogate(qry);
							while(res.next()){
								max = res.getInt(1);
							}
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						String qry2 = "select * " +
								"from APP.Food " +
								"where Code in (select Dish	" +
								"from ( select SO.Dish, sum(Quantity) as NUMBER " +
									   "from APP.SingleOrder SO join APP.Food F on (SO.Dish=F.Code) " +
									   "group by SO.Dish " +
									   "order by sum(SO.Quantity)) tab1 " +
									   "where NUMBER = " +max+ ")";
						ResultSet res2;
						try {
							Database.connect();
							res2 = Database.interrogate(qry2);
							AdminView.viewBestDishes(res2);
							Database.disconnect();
						} catch (JavaDBException e2) {
							e2.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.disableTable();
					}
					// OK - Statistiche - Tavolo preferito
					if (statusComboBox2.equalsIgnoreCase("Tavolo Preferito")){
						String qry = "select max(NUMBER) " +
									 "from ( select RelativeTo, count(RelativeTo) as NUMBER " +
									 "from APP.ORDERTABLE " +
									 "group by RelativeTo " +
									 "order by count(RelativeTo)) tab";						
						ResultSet res;
						int max = 0;
						try {
							Database.connect();
							res = Database.interrogate(qry);
							while(res.next()){
								max = res.getInt(1);
							}
							System.out.println(max);
							Database.disconnect();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						String qry2 = "select TableNumb, Seats, Content " +
								"from APP.TABLER " +
								"where TableNumb = (select RelativeTo " +
								"from APP.ORDERTABLE " +
								"group by RelativeTo " +
								"having count(*) = " +max+ ")";				
						ResultSet res2;
						try {
							Database.connect();
							res2 = Database.interrogate(qry2);
							AdminView.viewBestTables(res2);
							Database.disconnect();
						} catch (JavaDBException e2) {
							e2.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						AdminView.disableTable();	
					}
				}
			}
			AdminView.disableOK();
		}
		
		
		
		// se proviene da un COMBOBOX
		if (sourceObj.getClass().toString().equalsIgnoreCase("class javax.swing.JComboBox")){
			JComboBox source = (JComboBox) sourceObj;
			
			// se è il ComboBox1
			if (source.getName().equalsIgnoreCase("ComboBox1")) {
				if (source.getSelectedIndex()==0){						// Visualizza
					System.out.println("Visualizza");
					statusComboBox1 = "Visualizza";
					AdminView.disableSalva();
					AdminView.disableInserisci();
					AdminView.disableElimina();
					AdminView.cancText();
					AdminView.disableDataStart();
					AdminView.disableDataStop();
					AdminView.disableNuovoAdmin();
					AdminView.disableErrorDate();
					String[] model = 
						{"Clienti", "Cibi", "Sale", "Tavoli", "Camerieri", "Prenotazioni", "Conti Tavoli", "Tavoli Vicini", "Utenti Login", "Settaggi"};
					AdminView.modifyComboBox2(model);
				}
				if (source.getSelectedIndex()==1){						// Modifica
					AdminView.cancText();
					AdminView.disableDataStart();
					AdminView.disableDataStop();
					AdminView.disableNuovoAdmin();
					AdminView.disableErrorDate();
					System.out.println("Modifica");
					statusComboBox1 = "Modifica";
					String[] model = 
						{"Clienti", "Cibi", "Sale", "Tavoli", "Camerieri", "Prenotazioni", "Conti Tavoli", "Tavoli Vicini", "Utenti Login", "Settaggi"};
					AdminView.modifyComboBox2(model);
				}
				if (source.getSelectedIndex()==2){						// Statistiche
					System.out.println("Statistiche");
					statusComboBox1 = "Statistiche";
					AdminView.disableSalva();
					AdminView.disableInserisci();
					AdminView.disableElimina();
					AdminView.disableNuovoAdmin();
					AdminView.disableErrorDate();
					String[] model = 
						{ "Guadagno", "Numero Clienti", "Cliente più fedele", "Clienti Vips", "Piatto più ordinato", "Tavolo preferito"};
					AdminView.modifyComboBox2(model);
				}
			}
			
			// se è il ComboBox2 e ComboBox1=Visualizza
			if (source.getName().equalsIgnoreCase("ComboBox2") && statusComboBox1.equalsIgnoreCase("Visualizza")){
				AdminView.enableOK();
				if (source.getSelectedIndex()==0){
					System.out.println("Clienti_Visualizza");
					statusComboBox2 = "Clienti";
				}
				if (source.getSelectedIndex()==1){
					System.out.println("Cibi_Visualizza");
					statusComboBox2 = "Cibi";
				}
				if (source.getSelectedIndex()==2){
					System.out.println("Sale_Visualizza");
					statusComboBox2 = "Sale";
				}
				if (source.getSelectedIndex()==3){
					System.out.println("Tavoli_Visualizza");
					statusComboBox2 = "Tavoli";
				}
				if (source.getSelectedIndex()==4){
					System.out.println("Camerieri_Visualizza");
					statusComboBox2 = "Camerieri";
				}
				if (source.getSelectedIndex()==5){
					System.out.println("Prenotazioni_Visualizza");
					statusComboBox2 = "Prenotazioni";
				}
				if (source.getSelectedIndex()==6){
					System.out.println("Conti Tavoli_Visualizza");
					statusComboBox2 = "Conti Tavoli";
				}
				if (source.getSelectedIndex()==7){
					System.out.println("Tavoli Vicini_Visualizza");
					statusComboBox2 = "Tavoli Vicini";
				}
				if (source.getSelectedIndex()==8){
					System.out.println("Utenti Login_Visualizza");
					statusComboBox2 = "Utenti Login";			
				}
				if (source.getSelectedIndex()==9){
					System.out.println("Settings_Visualizza");
					statusComboBox2 = "Settaggi";
				}
			}
			
			// se è il ComboBox2 e ComboBox1=Modifica
			if (source.getName().equalsIgnoreCase("ComboBox2") && statusComboBox1.equalsIgnoreCase("Modifica")){
				AdminView.enableOK();
				AdminView.disableNuovoAdmin();
				if (source.getSelectedIndex()==0){	
					System.out.println("Clienti_Modifica");
					statusComboBox2 = "Clienti";
				}
				if (source.getSelectedIndex()==1){
					System.out.println("Cibi_Modifica");
					statusComboBox2 = "Cibi";
				}
				if (source.getSelectedIndex()==2){
					System.out.println("Sale_Modifica");
					statusComboBox2 = "Sale";
				}
				if (source.getSelectedIndex()==3){
					System.out.println("Tavoli_Modifica");
					statusComboBox2 = "Tavoli";
				}
				if (source.getSelectedIndex()==4){
					System.out.println("Camerieri_Modifica");
					statusComboBox2 = "Camerieri";
				}
				if (source.getSelectedIndex()==5){
					System.out.println("Prenotazioni_Modifica");
					statusComboBox2 = "Prenotazioni";
				}
				if (source.getSelectedIndex()==6){
					System.out.println("Conti Tavoli_Modifica");
					statusComboBox2 = "Conti Tavoli";
				}
				if (source.getSelectedIndex()==7){
					System.out.println("Tavoli Vicini_Modifica");
					statusComboBox2 = "Tavoli Vicini";
				}
				if (source.getSelectedIndex()==8){
					System.out.println("Utenti Login_Modifica");
					statusComboBox2 = "Utenti Login";
				}
				if (source.getSelectedIndex()==9){
					System.out.println("Settings_Visualizza");
					statusComboBox2 = "Settaggi";
				}
			}
			
			// se è il ComboBox2 e ComboBox1=Statistiche
			if (source.getName().equalsIgnoreCase("ComboBox2") && statusComboBox1.equalsIgnoreCase("Statistiche")){
				AdminView.enableOK();
				if (source.getSelectedIndex()==0){	
					System.out.println("Guadagno");
					statusComboBox2 = "Guadagno";
					AdminView.enableDataStart();
					AdminView.enableDataStop();	
					AdminView.disableErrorDate();
				}
				if (source.getSelectedIndex()==1){
					System.out.println("Numero Clienti");
					statusComboBox2 = "Numero Clienti";
					AdminView.enableDataStart();
					AdminView.enableDataStop();
					AdminView.disableErrorDate();
				}
				if (source.getSelectedIndex()==2){
					System.out.println("Cliente più fedele");
					statusComboBox2 = "Cliente più fedele";
					AdminView.cancText();
					AdminView.disableDataStart();
					AdminView.disableDataStop();
					AdminView.disableErrorDate();
				}
				if (source.getSelectedIndex()==3){
					System.out.println("Clienti Vips");
					statusComboBox2 = "Clienti Vips";
					AdminView.cancText();
					AdminView.disableDataStart();
					AdminView.disableDataStop();
					AdminView.disableErrorDate();
				}
				if (source.getSelectedIndex()==4){
					System.out.println("Piatto più ordinato");
					statusComboBox2 = "Piatto più ordinato";
					AdminView.cancText();
					AdminView.disableDataStart();
					AdminView.disableDataStop();
					AdminView.disableErrorDate();
				}
				if (source.getSelectedIndex()==5){
					System.out.println("Tavolo preferito");
					statusComboBox2 = "Tavolo preferito";
					AdminView.cancText();
					AdminView.disableDataStart();
					AdminView.disableDataStop();
					AdminView.disableErrorDate();
				}
			}
		}

	}
	
}
	