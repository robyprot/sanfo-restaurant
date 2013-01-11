package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

// TODO: Auto-generated Javadoc
/**
 * La classe Server resta in ascolto di ogni richiesta di connessione dai client, per ognuna gli assegna 
 * un'istanza della classe ComunicationMngServer per la gestione della comunicazione
 * 
 * @author Marco
 */
public class Server implements Runnable{ 

	/** The server active flag. */
	public static boolean serverActive = true;
	
	/** The server socket. */
	private ServerSocket serverSocket;
	
	/** The server protocol manager. */
	private ServerProtocolMng mServerProtocolMng;
	
	/** The ip server. */
	public static String ipServer;
	
	/**
	 * Instantiates a new server.
	 */
	public Server(){
		serverInit();
	}
	
	/**
	 * Server initialization.
	 */
	private void serverInit(){
		new Thread(this).start();
		mServerProtocolMng = new ServerProtocolMng();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() { 
		try { 
			System.out.println("S: Connecting..."); 
			serverSocket = new ServerSocket(11111); // cercare porta libera
			InetAddress serverIp = InetAddress.getLocalHost();
			System.out.println(serverIp);
			ipServer = serverIp.toString();
			//serverActive = true;
			while(serverActive) { 
				Socket clientSocket = serverSocket.accept();
				System.out.println("S: Receiving..."); 
				if(clientSocket!=null){
					ComunicationMngServer client = new ComunicationMngServer(clientSocket, mServerProtocolMng);
					Thread clientThread = new Thread(client);
					clientThread.start();
				}
			}
		} catch (IOException e) { 
			// TODO Auto-generated catch block 
			System.out.println("S: Error"); 
			e.printStackTrace(); 
		} finally{
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	} 
	
	/**
	 * Sets the server active flag.
	 *
	 * @param flag the new server active
	 */
	public static void setServerActive(boolean flag){
		serverActive = flag;
	}

}

