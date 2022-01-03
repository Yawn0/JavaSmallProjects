package Aste;

import java.io.Serializable;
import java.util.*;

public class Prodotto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private Date data;
	
	public Prodotto(String nome, Date data) {
		this.nome = nome;
		this.data = data; 
	}
	
	public Date getData() {
		return data;
	}
	
	public String getNome() {
		return nome;
	}

	@Override
	public int hashCode() {
		return 181*nome.hashCode()+11*data.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Prodotto))		// controlla che sia dello stesso tipo
			return false;
		if(o == this)						// controlla che gli oggetti siano la stessa istanza
			return true;
		Prodotto p = (Prodotto)o;
		return (p.nome.equals(nome) && p.data.equals(data));
	}

}
