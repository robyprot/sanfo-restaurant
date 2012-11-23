package database;

public class JavaDBException extends Exception {

	int errorCode;
	
	// constructors
	public JavaDBException() {
		super();
	}
	
	public JavaDBException(int errorCode){
		exceptionDBManager(errorCode);
	}
	
	
	public void exceptionDBManager(int errorCode){
		switch(errorCode){
		case 1: 
			System.out.println("Errore nel caricamento del driver");
			break;
		case 2:	
			System.out.println("Connessione fallita: database già connesso");
			break;
		case 3:
			System.out.println("Errore creazione Statement");
			break;
		case 4:
			System.out.println("Errore nella esecuzione della query");
			break;
		case 5: 
			System.out.println("Errore nella esecuzione della modifica");
			break;
		case 6: 
			System.out.println("Errore chiusura ResultSet");
			break;
		case 7: 
			System.out.println("Errore chiusura Statement");
			break;
		case 8: 
			System.out.println("Errore chiusura Connessione");
			break;
		case 20: 
			System.out.println("Istanza non ritornata");
			break;	
		}
	}
	

}
