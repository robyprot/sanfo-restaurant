package start;
import java.sql.SQLException;

import javax.swing.UIManager;

import view.AdminView;
import database.JavaDBException;


public class EntryPoint {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, JavaDBException {
	
		// Java GUI Windows Style
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception e){
			System.out.println("Unable to load Windows look and feel");
		}
		
		
		//DailyView view = new DailyView();
		AdminView view = new AdminView();
		
		// PER STAMPARE RESERVATION con le cose di Gregorian Calendar
		/*for(int i=0; i<reservationList.size(); i++){
			System.out.println("AAAAAAAAAAA");
			Reservation r = reservationList.get(i);
			System.out.println(r.getReservationNumb());
			GregorianCalendar gr = r.getCalend();
			System.out.println(gr.get(Calendar.YEAR));
			System.out.println(gr.get(Calendar.MONTH)+1);
			System.out.println(gr.get(Calendar.DAY_OF_MONTH));
			System.out.println(gr.get(Calendar.HOUR_OF_DAY));
			System.out.println(gr.get(Calendar.MINUTE));
			System.out.println(gr.get(Calendar.SECOND));
			System.out.println(r.getReservedTable());
			System.out.println(r.getIdClient());
			System.out.println(r.getPeopleNumb());
			System.out.println(r.getSmokingRoom());
		}*/
		
	}
				
}


