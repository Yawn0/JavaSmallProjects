package Aste;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Client implements Runnable {
	
	public void run() {
		try {
			Calendar oCalendar = Calendar.getInstance();

			Random oRandom = new Random();
			oCalendar.set(oRandom.nextInt(2031-2020)+2020, oRandom.nextInt(12), oRandom.nextInt(28)+1);

			Prodotto oProdotto = new Prodotto("Jaguar",new Date(oCalendar.getTimeInMillis()));

			double dPrezzo = oRandom.nextInt(70001 - 37450) + 37450;

			Offerta oOfferta = new Offerta(oProdotto, new Date(), dPrezzo);

			Socket oSocket = new Socket(InetAddress.getLocalHost(), 1111);

			ObjectOutputStream oOutputStream = new ObjectOutputStream(oSocket.getOutputStream());
			oOutputStream.writeObject(oOfferta);
//			oOutputStream.close();

			ObjectInputStream oReader = new ObjectInputStream(oSocket.getInputStream());
			String sConferma = (String) oReader.readObject();	//legge la risposta dal server (perché il messaggio dal client é inviato in TCP)
//			oReader.close();

			if(sConferma.equals("Offerta accettata")) {

				System.out.println(sConferma);

				Thread.sleep(oProdotto.getData().getTime()- oOfferta.getData().getTime()-10000);	//orario di scadenza - l'orario dell'offerta

				MulticastSocket oMulticastSocket = new MulticastSocket(2222);
				oMulticastSocket.joinGroup(InetAddress.getByName("230.0.0.1"));

				byte[] abBuffer = new byte[256];

				DatagramPacket oDatagramPacket = new DatagramPacket(abBuffer, abBuffer.length);
				oMulticastSocket.receive(oDatagramPacket);

				ByteArrayInputStream oData = new ByteArrayInputStream(oDatagramPacket.getData());
				ObjectInputStream oInputStream = new ObjectInputStream(oData);
				Offerta oBestOfferta = (Offerta) oInputStream.readObject();

				if(oBestOfferta.equals(oOfferta))
					System.out.println("Abbiamo vinto l' asta per il prodotto "+ oProdotto.getNome());
				//oInputStream.close();
				oMulticastSocket.close();
			}

			oSocket.close();

		} catch (Exception oException) {
			oException.printStackTrace();
		}
	}
	
}
