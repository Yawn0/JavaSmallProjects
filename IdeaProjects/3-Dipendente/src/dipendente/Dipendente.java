package dipendente;

/**
 * Esercizio sulla classe "Dipendente"
 * Classe principale del programma
 *
 * @author Leonardo Tocchet
 * @version 1.0.0.0
 */

public class Dipendente {

    private String nome;
    private double stipendio;

    /**
     * Costruttore di default che
     * crea un'istanza della classe Dipendente, a cui assegna i valori di default nome=anonimo,
     * stipendio=0.0
     */

    public Dipendente(){
        setNome("anonimo");     //nome = "anonimo"
        setStipendio(0.0);      //stipendio = "0.0"
    }

    /**
     * Crea un'istanza della classe Dipendente, a cui assegna i valori passati come parametri
     * @param nome Nome del dipendente
     * @param stipendio Stipendio del dipendente
     */

    public Dipendente(String nome ,double stipendio){
        setNome(nome);
        setStipendio(stipendio);
    }

    /**
     * Restituisce il nome del dipendente
     * @return Nome del dipendente
     * @deprecated :)
     */

    public String getNome(){ return nome; }

    /**
     * Restituisce lo stipendio del dipendente
     * @return Stipendio del dipendente
     * @deprecated :)
     */

    public double getStipendio(){ return stipendio; }

    /**
     * Imposta il nome del dipendente, passato come parametro
     * @param nome Nome del dipendente
     */

    public void setNome(String nome) { this.nome = nome; }

    /**
     * Imposta il valore dello stipendio, passato come parametro
     * @param stipendio Stipendio del dipendente
     */

    public void setStipendio(double stipendio) { this.stipendio = stipendio; }

    /**
     * Cambia il valore dello stipendio in base al valore in percentuale passato come
     * parametro
     * @param percentuale Valore percentuale per modificare il valore dello stipendio
     */

    public void aumenta(double percentuale){
        stipendio = stipendio + (stipendio/100)*percentuale;
    }

    /**
     * Restituisce true se gli stipendi dei due dipendenti sono uguali, false altrimenti
     * @param d Dipendente che viene comparato con quello su cui viene invocato il metodo
     * @return Valore booleano per determinare l'uguaglianza fra stipendi
     */

    public boolean equals(Dipendente d){
        return d.stipendio == stipendio;
    }

    /**
     * Restituisce una stringa che mostra il nome e lo stipendio del dipendente
     * @return Stringa comprendente il nome e lo stipendio del dipendente
     */

    public String toString(){
        return ("Nome : "+nome+"  Stipendio: "+stipendio);
    }

    /**
     * Testa il programma
     */

    public static void main(String[] args){

        Dipendente Giovanna = new Dipendente("Giovanna",2457.00);
        Dipendente Carlo = new Dipendente("Carlo", 700.00);
        Dipendente Anonimo = new Dipendente();

        System.out.println(Anonimo.toString()+"\n");

        System.out.println(Carlo.toString());
        System.out.println(Giovanna.toString()+"\n");

        Carlo.aumenta(251);
        Giovanna.aumenta(-71.51);

        if(Giovanna.equals(Carlo)) System.out.println("Gli stipendi dei dipendenti Giovanna e Carlo sono uguali");
        else System.out.println("Gli stipendi dei dipendenti Giovanna e Carlo sono differenti");

        System.out.println("\n"+Carlo.toString());
        System.out.println(Giovanna.toString());


    }
}



