package model.daily;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.crypto.Data;

import model.admin.AdminMng;
import start.Conto;
import start.SingOrd;
import controller.DailyCtrl;
import database.Database;
import database.JavaDBException;

// TODO: Auto-generated Javadoc
/**
 * Classe che gestisce le interazioni con le liste che riguardano la parte Serata.
 * 
 * @author Mauro
 */
public class DailyMng {

	/** The client mng. */
	public ClientMngDaily clientMng;
	
	/** The food mng. */
	public FoodMngDaily foodMng;
	
	/** The near mng. */
	public NearTableMngDaily nearMng;
	
	/** The order mng. */
	public OrderMngDaily orderMng;
	
	/** The reserv mng. */
	public ReservationMngDaily reservMng;
	
	/** The room mng. */
	public RoomMngDaily roomMng;
	
	/** The single mng. */
	public SingleOrderMngDaily singleMng;
	
	/** The table mng. */
	public TableMngDaily tableMng;
	
	/** The check mng. */
	public TablesCheckMngDaily checkMng;
	
	/** The waiter mng. */
	public WaiterMngDaily waiterMng;
	
	/** The settings. */
	public SettingsMngDaily settings;
	
	/** The instance. */
	private static DailyMng instance = null;
	
	/** The row deleted daily. */
	public static ArrayList<Integer> rowDeletedDaily= new ArrayList<Integer>();	// array che contiene le righe eliminate
	
	
	/**
	 * Istanzia un nuovo Manager Daily.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	private DailyMng() throws ClassNotFoundException, SQLException, JavaDBException{
		init();
	}
	
	
	/**
	 * Inizializza le liste.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	private void init() throws ClassNotFoundException, SQLException, JavaDBException{
		this.clientMng = ClientMngDaily.getIstance();
		this.foodMng = FoodMngDaily.getIstance();
		this.nearMng = NearTableMngDaily.getIstance();
		this.reservMng = ReservationMngDaily.getIstance();
		this.orderMng = OrderMngDaily.getIstance();
		this.roomMng = RoomMngDaily.getIstance();
		this.singleMng = SingleOrderMngDaily.getIstance();
		this.tableMng = TableMngDaily.getIstance();
		//this.checkMng = TablesCheckMngDaily.getIstance();
		this.waiterMng = WaiterMngDaily.getIstance();
		//this.settings = SettingsMngDaily.getIstance();
	}
	
	
	/**
	 * Ritorna l'istanza.
	 *
	 * @return the istance
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public static DailyMng getIstance() throws ClassNotFoundException, SQLException, JavaDBException {
		if (instance == null) {
			synchronized (DailyMng.class) {
				if (instance == null) {
					instance = new DailyMng();
				}
			}
		}
		return instance;
	}
	

		
	/**
	 * Metodo per stampare il conto di un tavolo
	 * se tutte le single orders sono complete stampa tutta la lista
	 * altrimenti stampa solo le incomplete.
	 *
	 * @param table il numero del tavolo
	 * @return la tabella conto per quel tavolo
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public ArrayList<Conto> printCheckTable(int table) throws ClassNotFoundException, SQLException, JavaDBException{
		// dato il tavolo, trovare il numero di ordinazione
		Order ord = OrderMngDaily.getIstance().getCurrentOrderTable(table);
		int numOrd = ord.getOrderNumb();
		// controllare che non ci siano singole ordinazioni incomplete
		SingleOrderMngDaily sOrdIstance = SingleOrderMngDaily.getIstance(); 
		FoodMngDaily foodIstance = FoodMngDaily.getIstance();
		ArrayList<Conto> result = new ArrayList<Conto>();
		boolean isComplete = sOrdIstance.isComplete(numOrd);
		if (isComplete==true){		// se tutte complete
			ArrayList<SingleOrder> sord = sOrdIstance.getSingleOrders(numOrd);
			for (int i=0; i<sord.size(); i++){
				String dish = sord.get(i).getDish();
				int quantity = sord.get(i).getQuantity();
				String state = sord.get(i).getState();
				Food f = foodIstance.getFoodFromCode(dish);
				String name = f.getName();
				float price = f.getPrice();
				String type = f.getType();
				Conto c = new Conto(dish, name, price, type, quantity, state);
				result.add(c);
			}
		}
		else{    					// stampo le incomplete
			ArrayList<SingleOrder> incomplete = sOrdIstance.getIncompleteSingleOrders(numOrd);
			for (int i=0; i<incomplete.size(); i++){
				String dish = incomplete.get(i).getDish();
				int quantity = incomplete.get(i).getQuantity();
				String state = incomplete.get(i).getState();
				Food f = foodIstance.getFoodFromCode(dish);
				String name = f.getName();
				float price = f.getPrice();
				String type = f.getType();
				Conto c = new Conto(dish, name, price, type, quantity, state);
				result.add(c);
			}
		}
		return result;
	}
	
	
	
	
	/**
	 * Metodo per calcolare il totale di un tavolo.
	 *
	 * @param contoTable il conto del tavolo 
	 * @return the float
	 */
	public float printTotal(ArrayList<Conto> contoTable){
		float total = 0;
		for (int i=0; i<contoTable.size(); i++){
			if (contoTable.get(i).getState().equalsIgnoreCase("DaCompletare")){
				return 0;
			}
			float price = contoTable.get(i).getPrice();
			int quantity = contoTable.get(i).getQuantity();
			total = total + price*quantity;
		}
		return total;
	}
	
	
	
	
	/**
	 * Metodo per occupare i tavoli nella lista prenotazioni ad inizio serata.
	 */
	public void occupyTables(){
		for(int i=0; i<reservMng.getReservationList().size(); i++){
			int tableReserv = reservMng.getReservationList().get(i).getReservedTable();
			for(int j=0; j<tableMng.getTableList().size(); j++){
				Table table = tableMng.getTableList().get(j);
				if(tableReserv==table.getTableNumb()){
					table.setState("Occupato");
					tableMng.getTableList().set(j, table);
				}
			}
		}
	}
	
	
	
	
	/**
	 * Metodo per estrarre parametri da una stringa formattata (usato nel Thread).
	 *
	 * @param sing la stringa
	 * @return the param from string
	 */
	public SingOrd getParamFromString(String sing) {
		String[] ord = new String[6];					// contiene i 6 campi contenuti nella stringa
		int num=0;										// per selezionare un elemento dell'array
		boolean isFirst = true;
		for(int i=0; i<sing.length(); i++) {
			char a = sing.charAt(i);					// estraggo un carattere
			if (a!='-' && isFirst) {
				ord[num] = String.valueOf(a);
				isFirst = false;
				//System.out.println(ord[num]);
			}
			else if (a!='-') {								// se non è il -, lo inserisco
				ord[num] = ord[num] + String.valueOf(a); 
				//System.out.println(ord[num]);
			}
			else {
				num++;									// altrimenti il parametro è finito e passo al successivo
				isFirst = true;
				//System.out.println("-");
			}		
		}
		int a = Integer.parseInt(ord[0]);
		return new SingOrd(a, ord[1], ord[2], ord[3], ord[4], ord[5]);
	}
	
	
	
	/**
	 * Metodo per passare da un oggetto SingOrd ad un oggetto SingleOrder (usato nel Thread).
	 *
	 * @param sing l'oggetto SingOrd
	 * @return the single order
	 */
	public SingleOrder convertSingOrd(SingOrd sing){
		int a = sing.getA();
		int ordNumb = orderMng.getCurrentOrderTable(Integer.parseInt(sing.getNum())).getOrderNumb(); 
		int quantity = Integer.parseInt(sing.getQuantity());
		String dish = sing.getCode();
		String state = "DaCompletare";
		String notes = sing.getNote();
		SingleOrder s = new SingleOrder(a, ordNumb, quantity, dish, state, notes);
		return s;
	}
	
	
	
	/**
	 * Metodo che a fine serata aggiorna le liste Order e SingleOrder su DB.
	 *
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public void refresh() throws JavaDBException, SQLException{
		Database.connect();
		// salvataggio lista ordinazioni e singoleOrdinazioni
		String qry2 = "truncate table APP.SingleOrder";				// elimino tutto il contenuto della tabella SingoleOrdinazioni
		Database.update(qry2);
		String qrya = "drop table APP.SingleOrder";					// cancello la tabella SingleOrder
		Database.update(qrya);
		String qry3 = "truncate table APP.OrderTable";				// elimino tutto il contenuto della tabella Ordinazioni
		Database.update(qry3);
		ArrayList<Order> orders = this.orderMng.getOrderList();
		for(int i=0; i<orders.size(); i++){							// inserisco l'intera lista Ordinazioni nel DB
			Order o = orders.get(i);
			int orderNumb = o.getOrderNumb();
			GregorianCalendar calend = o.getCalend();
			int year = calend.get(Calendar.YEAR);
			String month = AdminMng.checkFormat(calend.get(Calendar.MONTH));
			String day = AdminMng.checkFormat(calend.get(Calendar.DAY_OF_MONTH));
			String dateOrd = year+ "-" +month+ "-" +day; 
			String hour = AdminMng.checkFormat(calend.get(Calendar.HOUR_OF_DAY));
			String min = AdminMng.checkFormat(calend.get(Calendar.MINUTE));
			String sec = AdminMng.checkFormat(calend.get(Calendar.SECOND));
			String timeOrd = hour+ ":" +min+ ":" +sec;
			int relativeTo = o.getRelativeTo();
			int seatsNumb = o.getSeatsNumb();
			String stateOrd = o.getStateOrd();
			String qry = "insert into APP.OrderTable (OrderNumb, DateOrd, TimeOrd, RelativeTo, SeatsNumb, StateOrd) values " +
						 "(" +orderNumb+ ", '" +dateOrd+ "', '" +timeOrd+ "', " +relativeTo+ ", " +seatsNumb+ ", '" +stateOrd+ "')";
			System.out.println(qry);
			Database.update(qry);
		}								
		String qryb = "create table APP.SingleOrder ( " +				// ricreo la tabella Singole Ordinazioni sul DB
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"OrdNumb integer references APP.OrderTable(OrderNumb) on delete set null on update restrict, " +
				"Quantity integer not null, " +
				"Dish varchar(10) references APP.Food(Code) on delete set null on update restrict, " +
				"State varchar(15) not null check (State like 'DaCompletare' or State like 'Servita' or State like '-----'), " +
				"Notes varchar(50), " +
				"primary key (I))";
		System.out.println(qryb);
		Database.update(qryb);
		ArrayList<SingleOrder> singles = this.singleMng.getSingleOrderList();
		for(int i=0; i<singles.size(); i++){							// inserisco l'intera lista SingoleOrdinazioni nel DB
			SingleOrder s = singles.get(i);
			int ordNumb = s.getOrdNumb();
			int quantity = s.getQuantity();
			String dish = s.getDish();
			String state = s.getState();
			String notes = s.getNotes();
			String qry = "insert into APP.SingleOrder (OrdNumb, Quantity, Dish, State, Notes) values " +
						 "(" +ordNumb+ ", " +quantity+ ", '" +dish+ "', '" +state+ "', '" +notes+ "')";	
			Database.update(qry);
		}
		Database.disconnect();
	}
	
	
	
	/**
	 * Dato un tavolo ritorna l'id del cliente attualmente seduto.
	 *
	 * @param table il numero del tavolo
	 * @return l'id del cliente
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
	public int getIdClient(int table) throws ClassNotFoundException, SQLException{
		GregorianCalendar gr = new GregorianCalendar();						// data attuale
		ArrayList<Reservation> reserv = new ArrayList<Reservation>();
		reserv = reservMng.getReservationsTableOnDate(table, gr);   		// prenotazioni di un tavolo in un certo giorno
		for (int i=0; i<reserv.size(); i++){
			Reservation r = reserv.get(i);
			GregorianCalendar g = r.getCalend();							// data prenotazione
			int comp = gr.compareTo(g);										// le comparo
			if (comp>0){								// se ora attuale seguente a prenotazione ho trovato ciò che cerco
				return r.getIdClient();
			}
		}
		return -1;
	}
	
	

	/**
	 * Dato l'id del cliente mi ritorna la percentuale che gli spetta di sconto.
	 *
	 * @param idClient l'id cliente
	 * @return lo sconto 
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public int getSconto(int idClient) throws ClassNotFoundException, SQLException, JavaDBException{
		ArrayList<Client> cl = clientMng.getClientList();    			// lista dei clienti
		int percentage = DailyMng.getIstance().getPercentageSale();		// percentuale di sconto
		int counter = DailyMng.getIstance().getCounterNumb();			// contatore di presenza oltre il quale si applica lo sconto
		for (int i=0; i<cl.size(); i++){
			Client c = cl.get(i);
			int cont = c.getCounter();				// estraggo contatore
			int id = c.getId();						// estraggo id
			if (cont>=counter && id==idClient){		// se contatore>=counter ed è il mio cliente applica sconto
				return percentage;
			}
		}
		return 0;
	}
	
	
	
	/**
	 * Metodo per estrarre la percentuale di sconto.
	 *
	 * @return la percentuale di sconto
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public int getPercentageSale() throws ClassNotFoundException, SQLException, JavaDBException{
		Settings s = SettingsMngDaily.getIstance().getSettingsList().get(0);
		return Integer.parseInt(s.getValue());
	}
	
	
	/**
	 * Metodo per estrarre il contatore.
	 *
	 * @return il contatore
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public int getCounterNumb() throws ClassNotFoundException, SQLException, JavaDBException{
		Settings s = SettingsMngDaily.getIstance().getSettingsList().get(1);
		return Integer.parseInt(s.getValue());
	}
	
	
	/**
	 * Metodo per estrarre il prezzo del coperto.
	 *
	 * @return il prezzo del coperto
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public int getCoveredPrice() throws ClassNotFoundException, SQLException, JavaDBException{
		Settings s = SettingsMngDaily.getIstance().getSettingsList().get(4);
		return Integer.parseInt(s.getValue());
	}
	

	/**
	 * Metodo che inserisce l'id della riga da cancellare nell'array.
	 *
	 * @param numb il numero della riga
	 */
	public static void insertRowDeleted(int numb){
		rowDeletedDaily.add(numb);
	}


	
	/**
	 * Metodo che inserisce un ArrayList di TablesCheck (provenienti dalla JTable) nel DB.
	 *
	 * @param arr l'array dei conti
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public void insertTablesCheckIntoDB(ArrayList<TablesCheck> arr) throws JavaDBException, SQLException{
		Database.connect();
		for(int i=0; i<arr.size(); i++){
			TablesCheck c = arr.get(i);
			int a = c.getA();
			GregorianCalendar calend = c.getCalend();
			int year = calend.get(Calendar.YEAR);
			String month = AdminMng.checkFormat(calend.get(Calendar.MONTH));
			String day = AdminMng.checkFormat(calend.get(Calendar.DAY_OF_MONTH));
			String date = year+ "-" +month+ "-" +day; 
			String hour = AdminMng.checkFormat(calend.get(Calendar.HOUR_OF_DAY));
			String min = AdminMng.checkFormat(calend.get(Calendar.MINUTE));
			String sec = AdminMng.checkFormat(calend.get(Calendar.SECOND));
			String time = hour+ ":" +min+ ":" +sec;
			float amount = c.getAmount();
			int peopleNumb = c.getPeopleNumb();
			String qry;
			// Inserimento
			if (a==0){
				qry = "insert into APP.TablesCheck (Date, Time, Amount, PeopleNumb) values " +
			   		  "('" +date+ "', '" +time+ "', " +amount+ ", " +peopleNumb+ ")";	
				Database.update(qry);
			}
			// Aggiornamento
			else {
				qry = "update APP.TablesCheck set Date = '" +date+ "', Time = '" +time+ 
					  "', Amount = " +amount+ ", PeopleNumb = " +peopleNumb+ 
					  " where I = " +a; 
				Database.update(qry);
			}
		}
		// Cancellazione
		for(int i=0; i<rowDeletedDaily.size(); i++){
			int nb = rowDeletedDaily.get(i);
			String qry = "delete from APP.TablesCheck where I = " +nb;
			Database.update(qry);
		}
		rowDeletedDaily.clear();	
		System.out.println("Salvataggio effettuato");
		Database.disconnect();
	}
	

	
	/**
	 * Metodo per aggiungere lo 0 ai componenti della data.
	 *
	 * @param a l'intero che indica giorno o mese di una data
	 * @return the string
	 */
	public static String checkFormat(int a){
		String res = null;
		if (a>=0 && a<=9) res = "0" + a;
		else res = String.valueOf(a);
		return res;
	}
	
	
	
	/**
	 * Metodo che cancella dal DB le prenotazioni del giorno appena trascorso.
	 *
	 * @param date la data
	 * @throws JavaDBException the java db exception
	 */
	public void deleteReservation(String date) throws JavaDBException{
		Database.connect();
		String qry = "delete from APP.Reservation where Date = '" +date+ "'";
		Database.update(qry);
		Database.disconnect();
	}
	
	
	
	/**
	 * Metodo che libera tutti i tavoli ed elimina le unioni sul DB.
	 *
	 * @throws JavaDBException the java db exception
	 */
	public void freeTables() throws JavaDBException{
		Database.connect();
		String qry = "UPDATE APP.TABLER SET State = 'Libero', Joined = null";
		Database.update(qry);
		Database.disconnect();
	}

	

	/**
	 * Metodo che salva la lista Ordinazioni e la lista Tavoli sul DB.
	 *
	 * @throws JavaDBException the java db exception
	 */
	public void saveIntoDB() throws JavaDBException{
		Database.connect();
		// salvataggio lista tavoli
		ArrayList<Table> tables = this.tableMng.getTableList();
		for(int i=0; i<tables.size(); i++){
			Table t = tables.get(i);
			if(t.getState().equalsIgnoreCase("Occupato")){
				String joined = t.getJoined();
				int tableNumb = t.getTableNumb();
				String qry = "UPDATE APP.TABLER SET State = 'Occupato', Joined = '" +joined+ "' WHERE TableNumb = " +tableNumb;
				Database.update(qry);
			}			
		}
		// salvataggio lista ordinazioni e singoleOrdinazioni
		String qry2 = "truncate table APP.SingleOrder";				// elimino tutto il contenuto della tabella SingoleOrdinazioni
		Database.update(qry2);
		String qrya = "drop table APP.SingleOrder";					// cancello la tabella SingleOrder
		Database.update(qrya);
		String qry3 = "truncate table APP.OrderTable";				// elimino tutto il contenuto della tabella Ordinazioni
		Database.update(qry3);
		ArrayList<Order> orders = this.orderMng.getOrderList();
		for(int i=0; i<orders.size(); i++){							// inserisco l'intera lista Ordinazioni nel DB
			Order o = orders.get(i);
			int orderNumb = o.getOrderNumb();
			GregorianCalendar calend = o.getCalend();
			int year = calend.get(Calendar.YEAR);
			String month = AdminMng.checkFormat(calend.get(Calendar.MONTH));
			String day = AdminMng.checkFormat(calend.get(Calendar.DAY_OF_MONTH));
			String dateOrd = year+ "-" +month+ "-" +day; 
			String hour = AdminMng.checkFormat(calend.get(Calendar.HOUR_OF_DAY));
			String min = AdminMng.checkFormat(calend.get(Calendar.MINUTE));
			String sec = AdminMng.checkFormat(calend.get(Calendar.SECOND));
			String timeOrd = hour+ ":" +min+ ":" +sec;
			int relativeTo = o.getRelativeTo();
			int seatsNumb = o.getSeatsNumb();
			String stateOrd = o.getStateOrd();
			String qry = "insert into APP.OrderTable (OrderNumb, DateOrd, TimeOrd, RelativeTo, SeatsNumb, StateOrd) values " +
						 "(" +orderNumb+ ", '" +dateOrd+ "', '" +timeOrd+ "', " +relativeTo+ ", " +seatsNumb+ ", '" +stateOrd+ "')";	
			Database.update(qry);
		}								
		String qryb = "create table APP.SingleOrder ( " +				// ricreo la tabella Singole Ordinazioni sul DB
				"I integer not null generated always as identity(start with 1, increment by 1), " +
				"OrdNumb integer references APP.OrderTable(OrderNumb) on delete set null on update restrict, " +
				"Quantity integer not null, " +
				"Dish varchar(10) references APP.Food(Code) on delete set null on update restrict, " +
				"State varchar(15) not null check (State like 'DaCompletare' or State like 'Servita' or State like '-----'), " +
				"Notes varchar(50), " +
				"primary key (I))";
		System.out.println(qryb);
		Database.update(qryb);
		ArrayList<SingleOrder> singles = this.singleMng.getSingleOrderList();
		for(int i=0; i<singles.size(); i++){							// inserisco l'intera lista SingoleOrdinazioni nel DB
			SingleOrder s = singles.get(i);
			int ordNumb = s.getOrdNumb();
			int quantity = s.getQuantity();
			String dish = s.getDish();
			String state = s.getState();
			String notes = s.getNotes();
			String qry = "insert into APP.SingleOrder (OrdNumb, Quantity, Dish, State, Notes) values " +
						 "(" +ordNumb+ ", " +quantity+ ", '" +dish+ "', '" +state+ "', '" +notes+ "')";	
			Database.update(qry);
		}
		Database.disconnect();
	}
	
}
