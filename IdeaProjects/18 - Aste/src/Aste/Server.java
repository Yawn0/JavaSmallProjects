package Aste;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;


public class Server implements Runnable{
	
	private static final String ADDRESSG = "230.0.0.1";
	/*
		LinkedList impements    List<T> . Queue<T> . Dequeue<T>
		ArrayList implements    List<T>

		quindi una List è pratica per essere utilizzata anche come una coda mentre un ArrayList no
	 */
	// una SkipList viene utilizzata quando c'è necessità di avere una lista ordinata
	// una SkipListSet viene utilizzata quando c'è necessità di avere un set ordinato
	private static ConcurrentSkipListSet<Date> lstDate = new ConcurrentSkipListSet<>();
	private static final int PORTAC=1111;
	private static final int PORTAM=2222;
	private static ConcurrentHashMap<Prodotto,TreeSet<Offerta>> hmOfferte = new ConcurrentHashMap<>();	// un TreeSet viene utilizzato per non avere valori doppi
	private static boolean bGestione =false;

	private static HashMap prova; 	// una HashMap è più efficiente di una semplice Map
	
	public void run() {
		try {
			ServerSocket oServerSocket = new ServerSocket(PORTAC);

			while(true) {

				Socket oSocket = oServerSocket.accept();
				ObjectInputStream oInputStream = new ObjectInputStream(oSocket.getInputStream());
				Offerta oOfferta = (Offerta) oInputStream.readObject();
				String sOutput;

				if(oOfferta.getData().compareTo(oOfferta.getProdotto().getData())<=0) {

					TreeSet<Offerta> tsOfferte = hmOfferte.get(oOfferta.getProdotto());

					if(tsOfferte == null)
						tsOfferte = new TreeSet<>();
					else {

						tsOfferte.add(oOfferta);

						lstDate.add(oOfferta.getProdotto().getData());

						hmOfferte.put(oOfferta.getProdotto(), tsOfferte);

						if(!bGestione) {
							new Thread(new Gestore()).start();
							bGestione =true;
						}
					}
					sOutput = "Offerta accettata";
				}
				else{
					sOutput = "Offerta non accettata";
				}

				ObjectOutputStream oOutputStream = new ObjectOutputStream(oSocket.getOutputStream());
				oOutputStream.writeObject(sOutput);
			}
		} catch (Exception oException) {
			oException.printStackTrace();
		}
	}
	
	private static class Gestore implements Runnable{
		
		public void run() {
			try {
				while(true) {
					if(lstDate.isEmpty()) {
						bGestione =false;
						break;
					}
					Date dToday = new Date();

					Thread.sleep(lstDate.first().getTime() - dToday.getTime());

					LinkedList<Prodotto> lstInScadenza = new LinkedList<>();
					for(Prodotto oProdotto : hmOfferte.keySet())
						if(oProdotto.getData().equals(dToday))
							lstInScadenza.add(oProdotto);

					MulticastSocket oMulticastSocket = new MulticastSocket(PORTAM);	// invio mesaggi ai client
					DatagramPacket oDatagramPacket;		// invio messaggio TCP
					ObjectOutputStream oOutputStream;
					ByteArrayOutputStream abBuffer;

					for(Prodotto oProdotto : lstInScadenza) {

						abBuffer = new ByteArrayOutputStream();
						oOutputStream = new ObjectOutputStream(abBuffer);

						oOutputStream.writeObject(hmOfferte.get(oProdotto).last());	// ultimo perch� il set � ordinato per offerte e si prende l'iporto pi� alto

						oDatagramPacket = new DatagramPacket(abBuffer.toByteArray(), abBuffer.toByteArray().length,InetAddress.getByName(ADDRESSG),PORTAM);

						oMulticastSocket.send(oDatagramPacket);

						hmOfferte.remove(oProdotto);
					}

					oMulticastSocket.close();
				}
			} catch (Exception oException) {
				oException.printStackTrace();
			}
		}
	}
}
