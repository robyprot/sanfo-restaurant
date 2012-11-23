package model.daily;

import java.sql.SQLException;
import java.util.ArrayList;

import start.Conto;

import model.admin.AdminMng;

import database.JavaDBException;

public class DailyMng {

	public ClientMngDaily clientMng;
	public FoodMngDaily foodMng;
	public NearTableMngDaily nearMng;
	public OrderMngDaily orderMng;
	public ReservationMngDaily reservMng;
	public RoomMngDaily roomMng;
	public SingleOrderMngDaily singleMng;
	public TableMngDaily tableMng;
	public TablesCheckMngDaily checkMng;
	public WaiterMngDaily waiterMng;
	
	private static DailyMng instance = null;
	
	
	// constructor
	private DailyMng() throws ClassNotFoundException, SQLException, JavaDBException{
		init();
	}
	
	
	private void init() throws ClassNotFoundException, SQLException, JavaDBException{
		this.clientMng = ClientMngDaily.getIstance();
		this.foodMng = FoodMngDaily.getIstance();
		this.nearMng = NearTableMngDaily.getIstance();
		this.orderMng = OrderMngDaily.getIstance();
		this.reservMng = ReservationMngDaily.getIstance();
		this.roomMng = RoomMngDaily.getIstance();
		this.singleMng = SingleOrderMngDaily.getIstance();
		this.tableMng = TableMngDaily.getIstance();
		this.checkMng = TablesCheckMngDaily.getIstance();
		this.waiterMng = WaiterMngDaily.getIstance();
	}
	
	
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
	
	
	// metodo per trovare un tavolo libero dato il numero di persone 
	//public int searchTable(int people){

	//}

	
	// metodo per stampare il conto di un tavolo
	// se tutte le single orders sono complete stampa tutta la lista
	// altrimenti stampa solo le incomplete
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
				String code = f.getCode();
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
				String code = f.getCode();
				String name = f.getName();
				float price = f.getPrice();
				String type = f.getType();
				Conto c = new Conto(dish, name, price, type, quantity, state);
				result.add(c);
			}
		}
		return result;
	}
	
	
	
	// metodo per calcolare il totale di un tavolo
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
	
	
	
	
	
	
	
	
}
