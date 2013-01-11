package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import model.admin.AdminMng;
import model.daily.DailyMng;
import model.daily.Order;
import model.daily.OrderMngDaily;
import model.daily.SingleOrder;
import model.daily.Table;
import model.daily.TableMngDaily;
import model.daily.TablesCheck;
import server.Server;
import start.Conto;
import start.SimpleRunner;
import view.DailyView;
import database.JavaDBException;

// TODO: Auto-generated Javadoc
/**
 * Classe Controller Serata che intercetta gli eventi della view Daily 
 * e fa da tramite tra quest'ultima e il model Daily Manager.
 * 
 * @author Mauro
 */
public class DailyCtrl implements ActionListener {
	 
	/** The daily mng. */
	public DailyMng dailyMng;
	
	/** The button pressed. */
	public String buttonPressed = null;
	
	/** The table numb. */
	public int tableNumb;
	
	/** The variable annulla*/
	public static boolean isAnnulla = false;

	
	/**
	 * Istanzia un nuovo controllore Daily.
	 *
	 * @param dailyMng il manager Daily
	 */
	public DailyCtrl(DailyMng dailyMng){
		this.dailyMng = dailyMng;
	}
	
	/**
	 * Istanzia un nuovo controllore Daily.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public DailyCtrl() throws ClassNotFoundException, SQLException, JavaDBException {
		this.dailyMng = DailyMng.getIstance();
	}
	
	
	

	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e){
		// estraggo la sorgente dell'evento
		Object sourceObj = e.getSource();	
		
		// se proviene da un BOTTONE, creo un bottone e individuo quale dei bottoni presenti l'ha generato
		if (sourceObj.getClass().toString().equalsIgnoreCase("class javax.swing.JButton")){		
			JButton source = (JButton) e.getSource();											
			
			// Bottone INIZIO SERATA
			if (source.getText().equalsIgnoreCase("Inizio Serata")) {										
				System.out.println("Click Inizio Serata");
				DailyView.writeTextFieldIPServer(Server.ipServer);
				DailyView.start();
				DailyView.disableInizioSerata();
				DailyView.enableAnnulla();
				dailyMng.occupyTables();			// occupo i tavoli presenti nella lista Prenotazioni
				
			}
			
			// Bottone FINE SERATA
			if (source.getText().equalsIgnoreCase("Fine Serata")) {	
				System.out.println("Click Fine Serata");
				try {
					GregorianCalendar c = new GregorianCalendar();
					String day = AdminMng.checkFormat(c.get(Calendar.DAY_OF_MONTH));
					String month = AdminMng.checkFormat(c.get(Calendar.MONTH)+1);
					String year = AdminMng.checkFormat(c.get(Calendar.YEAR));
					String today = year+ "-" +month+ "-" +day;
					dailyMng.deleteReservation(today);				// cancello dal DB le Prenotazioni della giornata appena trascorsa
					dailyMng.freeTables();							// libero e cancello le unioni dei tavoli sul DB
					dailyMng.refresh();								// inserisco le liste Order e SingleOrder su DB
					DailyView.init();
					OrderMngDaily.numOrd = 1;						// azzero il contatore delle ordinazioni
					SimpleRunner.setZeroRefreshCounter();			// azzero il contatore delle righe delle singole ordinazioni
					SimpleRunner.setSimpleRunnerActive(false);
					Server.setServerActive(false);
					System.exit(0);
				} catch (JavaDBException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			// Bottone ANNULLA
			if (source.getText().equalsIgnoreCase("Annulla")) {										
				System.out.println("Click Annulla");
				buttonPressed = "Annulla";
				DailyView.start();  
			}
			
			// Bottone SERVITA
			if (source.getText().equalsIgnoreCase("Ordinazione Servita")) {										
				System.out.println("Click Ordinazione Servita");
				buttonPressed = "Ordinazione Servita";
				int a = DailyView.getSingOrdSelected();				// estraggo l'I della singola ordinazione selezionata
				dailyMng.singleMng.setCompleteSingleOrder(a);		// la setto come servita nella lista SingleOrder
				try {
					SimpleRunner.removeElement(a);					// la elimino dalla lista SingOrd
					DailyView.decrementRows();						// decremento rows di 1
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (JavaDBException e1) {
					e1.printStackTrace();
				}					   
			}
			
			// Bottone CANCELLATA
			if (source.getText().equalsIgnoreCase("Ordinazione Cancellata")) {										
				System.out.println("Click Ordinazione Cancellata");
				buttonPressed = "Ordinazione Cancellata";
				int a = DailyView.getSingOrdSelected();				// estraggo l'I della singola ordinazione selezionata
				dailyMng.singleMng.removeSingleOrder(a);			// la elimino dalla lista SingleOrder
				try {
					SimpleRunner.removeElement(a);					// la elimino dalla lista SingOrd
					DailyView.decrementRows();						// decremento rows di 1
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (JavaDBException e1) {
					e1.printStackTrace();
				}					   
			}
			
			// Bottone SALVATAGGIO SU DB
			if (source.getText().equalsIgnoreCase("Salvataggio su DB")) {										
				System.out.println("Click Salvataggio su DB");
				buttonPressed = "Salvataggio su DB";
				try {
					dailyMng.saveIntoDB();		
				} catch (JavaDBException e1) {
					e1.printStackTrace();
				}
			}
			
			
			// Bottone VISUALIZZA TAVOLI
			if (source.getText().equalsIgnoreCase("Visualizza Tavoli")) {										
				System.out.println("Click Visualizza Tavoli");
				buttonPressed = "Visualizza Tavoli";
				DailyView.viewTablesIntoJTable(dailyMng.tableMng.getTableList());  
				DailyView.enableTextTavolo();
				DailyView.enableLabelNuovoTavolo();
				DailyView.disableStampaConto();
				DailyView.disableCombobox();
				DailyView.disableScegliTavolo();
				DailyView.disableTextTavolo2();
				DailyView.disableTextPersone2();
				DailyView.disableNuovoTavolo();
				DailyView.disableTextTavolo();
				DailyView.writeTextFieldLabel("Tavoli:");
			}
			
			// Bottone VISUALIZZA ORDINAZIONI
			if (source.getText().equalsIgnoreCase("Visualizza Ordinazioni")) {										
				System.out.println("Click Visualizza Ordinazioni");
				buttonPressed = "Visualizza Ordinazioni";
				DailyView.viewOrdersIntoJTable(dailyMng.orderMng.getOrderList());  
				DailyView.enableTextTavolo();
				DailyView.enableLabelNuovoTavolo();
				DailyView.disableStampaConto();
				DailyView.disableCombobox();
				DailyView.disableScegliTavolo();
				DailyView.disableTextTavolo2();
				DailyView.disableTextPersone2();
				DailyView.disableNuovoTavolo();
				DailyView.disableTextTavolo();
				DailyView.writeTextFieldLabel("Ordinazioni:");
			}
			
			// Bottone VISUALIZZA SINGOLE ORDINAZIONI
			if (source.getText().equalsIgnoreCase("Visualizza Singole Ord")) {										
				System.out.println("Click Visualizza Singole Ordinazioni");
				buttonPressed = "Visualizza Singole Ordinazioni";
				DailyView.viewSingleOrdersIntoJTable(dailyMng.singleMng.getSingleOrderList());  
				DailyView.enableTextTavolo();
				DailyView.enableLabelNuovoTavolo();
				DailyView.disableStampaConto();
				DailyView.disableCombobox();
				DailyView.disableScegliTavolo();
				DailyView.disableTextTavolo2();
				DailyView.disableTextPersone2();
				DailyView.disableNuovoTavolo();
				DailyView.disableTextTavolo();
				DailyView.writeTextFieldLabel("Singole Ordinazioni:");
			}
			
			
			// Bottone NUOVO TAVOLO
			if (source.getText().equalsIgnoreCase("Nuovo Tavolo")) {										
				System.out.println("Click Nuovo Tavolo");
				buttonPressed = "Nuovo_Tavolo";
				DailyView.disableStampaConto();
				DailyView.disableCombobox();
				DailyView.disableScegliTavolo();
				DailyView.disableTextTavolo2();
				DailyView.disableTextPersone2();
				DailyView.disableNuovoTavolo();
				DailyView.enableTextTavolo();
				DailyView.disableVisTavoli();
				DailyView.disableVisOrdinazioni();
				DailyView.disableVisSingle();
			}
			
			// Bottone SCEGLI TAVOLO
			if (source.getText().equalsIgnoreCase("Scegli Tavolo")) {										
				System.out.println("Click Scegli Tavolo");
				buttonPressed = "Scegli_Tavolo";
				DailyView.enableTextTavolo2();
				DailyView.enableTextPersone2();
				DailyView.enableLabelScegliTavolo_tavolo();
				DailyView.enableLabelScegliTavolo_persone();
				DailyView.disableStampaConto();
				DailyView.disableCombobox();
				DailyView.disableNuovoTavolo();
				DailyView.disableTextTavolo();
				DailyView.disableVisTavoli();
				DailyView.disableVisOrdinazioni();
				DailyView.disableVisSingle();
				DailyView.disableScegliTavolo();
			}
				
				
			// Bottone STAMPA CONTO
			if (source.getText().equalsIgnoreCase("Stampa Conto")) {										
				System.out.println("Click Stampa Conto");
				buttonPressed = "Stampa_Conto";
				DailyView.enableCombobox();
				DailyView.enableLabelStampaConto();
				DailyView.disableNuovoTavolo();
				DailyView.disableTextTavolo();
				DailyView.disableScegliTavolo();
				DailyView.disableTextTavolo2();
				DailyView.disableTextPersone2();
				DailyView.disableVisTavoli();
				DailyView.disableVisOrdinazioni();
				DailyView.disableVisSingle();
				DailyView.disableStampaConto();
				try {
					DailyMng d = DailyMng.getIstance();							// istanza di DailyMng
					ArrayList<Table> occup = d.tableMng.getOccupiedTables();	// ritorno i tavoli occupati con Joined vuoto o nullo
					DailyView.writeTablesIntoCombobox(occup);					// scrivo nella combobox
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (JavaDBException e1) {
					e1.printStackTrace();
				}
			}
			
			// Bottone OK
			if (source.getText().equalsIgnoreCase("OK")) {
				System.out.println("Click OK");
				
				// OK - Nuovo Tavolo
				if (DailyView.isEnableTextTavolo()==true){			
					int people = DailyView.getPeopleNumb();
					if(people==0){ 																	// se l'utente inserisce 0 persone
						DailyView.writeTextFieldLabel("Inserisci il numero di persone");
						DailyView.writeTextFieldResult("ERRORE!!!");
					}
					else {																			// altrimenti
						try {
							String tables = TableMngDaily.getIstance().searchFreeTable(people);		// tavolo o lista di tavoli
							DailyView.writeTextFieldLabel("Tavoli numero:");						
							DailyView.writeTextFieldResult(tables);
							if(!tables.equalsIgnoreCase("non c'è")){
								String[] blocchi = new String[3];
								blocchi[0] = "";
								blocchi[1] = "";
								blocchi[2] = "";
								blocchi = tables.split("-");
								// 1 solo tavolo
								if(blocchi.length==1){
									int table1 = Integer.parseInt(blocchi[0]);
									GregorianCalendar calend = new GregorianCalendar();											// estraggo la data di oggi
									int month = calend.get(Calendar.MONTH)+1;													// sistemo il problema del mese
									calend.set(Calendar.MONTH, month);
									Order order = new Order(0, OrderMngDaily.numOrd++, calend, table1, people, "InCorso");		// creo la nuova ordinazione
									dailyMng.orderMng.getOrderList().add(order);												// e la inserisco nella lista ordinazioni
								}
								// 2 tavoli
								else if(blocchi.length==2){
									int table1 = Integer.parseInt(blocchi[0]);
									int table2 = Integer.parseInt(blocchi[1]);
									GregorianCalendar calend = new GregorianCalendar();											// estraggo la data di oggi
									int month = calend.get(Calendar.MONTH)+1;													// sistemo il problema del mese
									calend.set(Calendar.MONTH, month);
									Order order = new Order(0, OrderMngDaily.numOrd++, calend, table1, people, "InCorso");		// creo la nuova ordinazione
									dailyMng.orderMng.getOrderList().add(order);												// e la inserisco nella lista ordinazioni
									String joined = String.valueOf(table1);														
									dailyMng.tableMng.setTableJoined(table2, joined);											// modifico il campo Joined del tavolo 2		
								}
								// 3 tavoli
								else {
									int table1 = Integer.parseInt(blocchi[0]);
									int table2 = Integer.parseInt(blocchi[1]);
									int table3 = Integer.parseInt(blocchi[2]);
									GregorianCalendar calend = new GregorianCalendar();											// estraggo la data di oggi
									int month = calend.get(Calendar.MONTH)+1;													// sistemo il problema del mese
									calend.set(Calendar.MONTH, month);
									Order order = new Order(0, OrderMngDaily.numOrd++, calend, table1, people, "InCorso");		// creo la nuova ordinazione
									dailyMng.orderMng.getOrderList().add(order);												// e la inserisco nella lista ordinazioni
									String joined = String.valueOf(table1);														
									dailyMng.tableMng.setTableJoined(table2, joined);											// modifico il campo Joined del tavolo 2
									dailyMng.tableMng.setTableJoined(table3, joined);											// modifico il campo Joined del tavolo 3		
								}
							}
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						}
						//SimpleRunner.setflag(true);
					}
					
				}
				
				// OK - Scegli Tavolo
				if (DailyView.isEnableTextTavolo2()==true && DailyView.isEnableTextPersone2()==true){     
					boolean correct1=true, correct2=true;
					int people = DailyView.getPeopleNumb2();				// estraggo numero di persone
					if(people==0){ 
						correct1 = false;
					}
					int table = DailyView.getTableNumb2();					// estraggo numero di tavolo
					if(table==0){
						correct2 = false;
					}
					if((correct1 & correct2)==true){  						// se non ci sono stati errori
						ArrayList<Table> tables = null;
						try {
							tables = dailyMng.tableMng.getFreeTables();		// tavoli liberi
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						for(int i=0; i<tables.size(); i++){
							Table t = tables.get(i);
							if (t.getTableNumb()==table){
								if (t.getSeats()>=people && t.getState().equalsIgnoreCase("Libero")){		// verifico se il mio tavolo è libero ed ha un numero di posti sufficiente
									DailyView.writeTextFieldLabel("Confermato per il tavolo: ");
									DailyView.writeTextFieldResult(Integer.toString(table));;
									try {
										dailyMng.tableMng.setTableState(table, "Occupato");					// setto il tavolo occupato
									} catch (ClassNotFoundException e1) {
										e1.printStackTrace();
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
									GregorianCalendar calend = new GregorianCalendar();											// estraggo la data di oggi
									int month = calend.get(Calendar.MONTH)+1;													// sistemo il problema del mese
									calend.set(Calendar.MONTH, month);
									Order order = new Order(0, OrderMngDaily.numOrd++, calend, table, people, "InCorso");		// creo la nuova ordinazione
									dailyMng.orderMng.getOrderList().add(order);												// e la inserisco nella lista ordinazioni	
								}
								else {
									DailyView.writeTextFieldLabel("Non disponibile");
								}
							}
						}
					}
					else{
						DailyView.writeTextFieldLabel("Errore inserimento !!!");
					}
					
				}
				
				// OK - Stampa Conto
				if (DailyView.isEnableCombobox()==true){			
					int table = tableNumb;
					ArrayList<Conto> conto = new ArrayList<Conto>();
					float total = 0;
					int idClient = 0;
					int sconto = 0;
					int people1 = 0;
					int coperto = 0;
					try {
						conto = dailyMng.printCheckTable(table);	// lista conto di un tavolo
						total = dailyMng.printTotal(conto);			// totale		
						idClient = dailyMng.getIdClient(table);		// cliente a cui si riferisce
						sconto = dailyMng.getSconto(idClient);		// sconto da applicare
						people1 = dailyMng.orderMng.getCurrentOrderTable(table).getSeatsNumb();  // numero di persone sedute
						coperto = dailyMng.getCoveredPrice();									 // prezzo coperto
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					}
	
					DailyView.viewCheckIntoTable(conto, total, sconto, people1, coperto);		// stampo lista conto
					if (total > 0){
						total = total - sconto*total/100 + coperto*people1;							// totale - sconto + coperto
					}
					DailyView.writeTextFieldLabel("Conto tavolo " +table+ " :");	
					DailyView.writeTextFieldResult(Float.toString(total));
					
					if(total>0){
						// inserisco nella tabella TablesCheck nel DB il conto
						Order o = dailyMng.orderMng.getCurrentOrderTable(table);
						int orderNumb = o.getOrderNumb();
						GregorianCalendar cal = o.getCalend();
						int people = o.getSeatsNumb();
						TablesCheck check = new TablesCheck(0, cal, total, people);
						ArrayList<TablesCheck> arr = new ArrayList<TablesCheck>();
						arr.add(check);
						try {
							dailyMng.insertTablesCheckIntoDB(arr);		
						} catch (JavaDBException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// archivio l'ordinazione riferita a quel tavolo
						dailyMng.orderMng.setTableStoredOrder(table);
						// libero il tavolo
						try {
							dailyMng.tableMng.setTableState(table, "Libero");
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						/*
						// cancello le singole ordinazioni riferite all'ordinazione appena archiviata
						ArrayList<SingleOrder> singOrders = dailyMng.singleMng.getSingleOrderList();
						for(int i=0; i<singOrders.size(); i++){
							SingleOrder s = singOrders.get(i);
							if(s.getOrdNumb()==orderNumb){
								singOrders.remove(i);
							}
						}*/
					}
				}
			}
		}
		
		
		// se proviene da un COMBOBOX
		if (sourceObj.getClass().toString().equalsIgnoreCase("class javax.swing.JComboBox")){
			JComboBox source = (JComboBox) sourceObj;
			// se è il ComboBoxConto
			if (source.getName().equalsIgnoreCase("ComboBoxConto") && isAnnulla == false) {
				int number = Integer.parseInt(source.getSelectedItem().toString());
				System.out.println("---COMBOBOX---");
				System.out.println(number);
				System.out.println("---COMBOBOX---");
				tableNumb = number;
				DailyView.enableOK();
			}	
		}
		
		
		// se proviene da una TEXTFIELD
		if (sourceObj.getClass().toString().equalsIgnoreCase("class javax.swing.JTextField")){
			JTextField source = (JTextField) sourceObj;
			// se è la TextField di Nuovo Tavolo
			if (source.getName().equalsIgnoreCase("TextFieldTavolo")) {
				DailyView.enableOK();
			}
			// se è la TextField di Scegli Tavolo
			if (source.getName().equalsIgnoreCase("TextField2Persone")) {
				DailyView.enableOK();
			}
		}
	}
	
	
}
