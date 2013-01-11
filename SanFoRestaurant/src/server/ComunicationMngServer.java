package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


// TODO: Auto-generated Javadoc
/**
 * La classe ComunicationMngServer viene istanziata ogni volta che un client richiede la connessione
 * con il server, al suo interno è presente il thread che gestisce la connessione.
 * 
 * @author Marco
 */
public class ComunicationMngServer implements Runnable {

	/** The client. */
	private Socket client;
	
	/** The server protocol manager. */
	private ServerProtocolMng mServerProtocolMng;
	
	/** The input buffer reader. */
	private BufferedReader in;
	
	/** The out print writer. */
	private PrintWriter out;
	
	
	/**
	 * Instantiates a new comunication mng server.
	 *
	 * @param client the client
	 * @param mServerProtocolMng the server protocol manager
	 */
	public ComunicationMngServer(Socket client, ServerProtocolMng mServerProtocolMng){
		this.client = client;
		this.mServerProtocolMng = mServerProtocolMng;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String inPacket;
		String outPacket;
		try { 
			in = new BufferedReader(new InputStreamReader(client.getInputStream())); 
			out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(client.getOutputStream())),true); 
			
			while ((inPacket = in.readLine()) != null) {   
			    System.out.println("in    :" + inPacket);
				outPacket = mServerProtocolMng.processInput(inPacket);
				System.out.println("out   :" + outPacket);
			    out.println(outPacket);
			    if (outPacket.equals(RestaurantProtocolServer.STOP_COMUNICATION))
			    	break;
			}
		} catch(Exception e) { 
			System.out.println("S: Error"); 
			e.printStackTrace(); 
		} 
		

		try {
			in.close();
			out.close();
			client.close();
			System.out.println("chiusa connessione");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}

