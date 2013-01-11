package start;

import java.sql.SQLException;
import java.util.ArrayList;

import view.DailyView;

import database.JavaDBException;
import model.daily.DailyMng;
import model.daily.SingleOrder;

// TODO: Auto-generated Javadoc
/**
 * Classe che gestisce il thread che ascolta l'arrivo di nuove singole ordinazioni dai camerieri.
 * 
 * @author Marco, Mauro
 */
public class SimpleRunner implements Runnable {
	
		/** The prova. */
	public String[] prova = {"1-P1-10-aaaaa", "2-P2-12-/", "3-S1-5-/", "4-S2-1-ddddd", "5-P1-7-eeeee"};
	
	/** The single order. */
	public static ArrayList<String> singOrdList = new ArrayList<String>();
	
	/** Contatore che serve per le modifiche delle liste */
	public static int refreshCounter = 0;
	
	/** OrderCount è un id per le ordinazioni ricevute */
	public static int orderCount = 1;
	
	/** simpleRunnerActive è il flag di attivazione del thread */
	public static boolean simpleRunnerActive = true;
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (simpleRunnerActive) {
			synchronized(singOrdList){
				for(int i=0; i<singOrdList.size(); i++){
					try {
						SingOrd sing = DailyMng.getIstance().getParamFromString(singOrdList.get(i));		// estraggo parametri dalla stringa i-esima
						int item = sing.getA();
						if(item > refreshCounter){															// se è nuova
							DailyView.viewSingOrd(sing);													// la visualizzo nella JTable in aggiunta alle altre		
							refreshCounter = item;	
							SingleOrder s = DailyMng.getIstance().convertSingOrd(sing);						// converto da SingOrd a SingleOrder
							DailyMng.getIstance().singleMng.getSingleOrderList().add(s);					// e la aggiungo nella lista
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (JavaDBException e) {
						e.printStackTrace();
					}	
				}
				
			}	
		}
	}

	
	/**
	 * Azzera il contatore del refresh.
	 */
	public static void setZeroRefreshCounter(){
		refreshCounter = 0;
	}
	
	
	/**
	 * Metodo che rimuove l'elemento con indice a.
	 *
	 * @param a l'indice dell'elemento da eliminare
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public static void removeElement(int a) throws ClassNotFoundException, SQLException, JavaDBException{
		synchronized(singOrdList){
			for(int i=0; i<singOrdList.size(); i++){
				SingOrd sing = DailyMng.getIstance().getParamFromString(singOrdList.get(i));	// estraggo parametri dalla stringa i-esima
				if(sing.getA()==a){
					singOrdList.remove(i);
					break;
				}
			}
		}
	}

	/**
	 * Questo metodo viene utilizzato per aggiungere una nuova ordinazione ricevuta 
	 * alla lista delle ordinazioni
	 * 
	 * @param order è l'ordinazione ricevuta
	 */
	public static void addNewOrder(String order){
		synchronized(singOrdList){
			singOrdList.add(String.valueOf(orderCount) + "-" + order + "-" + "inCorso");
			orderCount++;
			System.out.println("ordinazione aggiuntaaaaaaa");
		}
	}
	
	/**
	 * Questo metodo viene utilizzato per aggiungere un'ordinazione da cancellare 
	 * alla lista delle ordinazioni
	 * 
	 * @param order è l'ordinazione da cancellare
	 */
	public static void addDeletedOrder(String order){
		synchronized(singOrdList){
			singOrdList.add(String.valueOf(orderCount) + "-" + order + "-" + "deleted");
			orderCount++;
		}
	}
	
	/**
	 * Serve a settare il flag che abilita o disabilita il thread
	 * 
	 * @param flag è il flag per attivare/disattivare il thread
	 */
	public static void setSimpleRunnerActive(boolean flag){
		simpleRunnerActive = flag;
	}
	
}
