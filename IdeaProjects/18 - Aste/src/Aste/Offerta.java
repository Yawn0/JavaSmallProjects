package Aste;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Offerta implements Serializable,Comparable<Offerta> {

	@Serial
	private static final long serialVersionUID = 1L;
	private double prezzo;
	private Prodotto prodotto;
	private Date data;

	public Offerta(Prodotto prodotto,Date data, double prezzo) {
		this.prezzo = prezzo;
		this.prodotto = prodotto;
		this.data = data;
	}
	
	public Date getData() {
		return data;
	}
	
	public Prodotto getProdotto() {
		return prodotto;
	}
	
	public double getPrezzo() {
		return prezzo;
	}

	@Override
	public int hashCode() {
		final int M = 23;
		return M*prodotto.hashCode()+M*data.hashCode();
	}

	@Override
	public boolean equals(Object o) {	// viene utilizzato dal treeSet durante l'inserimento per non inserire valori doppi
		if(!( o instanceof Offerta))	// bisogna fare l'override altrimenti non viene effettuo il cast al tipo Offerta
			return false;
		if(this==o)
			return true;
		Offerta of=(Offerta)o;
		return (of.prezzo==prezzo && of.data.equals(data) && of.prodotto.equals(prodotto));
	}
	
	public int compareTo(Offerta offerta) {
		if(prezzo < offerta.prezzo)
			return -1;
		else if(prezzo == offerta.prezzo)
			return data.compareTo(offerta.data)*(-1);
		else
			return 1;
	}
	
}
