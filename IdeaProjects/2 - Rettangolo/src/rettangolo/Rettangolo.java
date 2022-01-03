package rettangolo;

/**
 * Esercizio sulla classe "Rettangolo"
 * Classe principale del programma
 *
 * @author Leonardo Tocchet
 * @version 1.0.0.0
 */

public class Rettangolo {

    private double b;
    private double h;

    /**
     * Crea un'istanza della classe Rettangolo, a cui assegna i valori passati come parametri
     * @param b base del rettanglo
     * @param h altezza del rettangolo
     */

    public Rettangolo(double b, double h){
        setBase(b);
        setAltezza(h);
    }

    /**
     * Crea una copia del rettangolo passato come parametro
     * @param r oggetto di tipo Rettangolo passato come parametro
     */

    public Rettangolo(Rettangolo r){
        b = r.getBase();
        h = r.getAltezza();
    }

    /**
     * Imposta il valore della base
     * @param b base del rettangolo
     */

    public void setBase(double b) { this.b = b; }

    /**
     * Imposta il valore dell'altezza
     * @param h altezza del rettangolo
     */

    public void setAltezza(double h) { this.h = h; }

    /**
     * Restituisce il valore della base
     * @return b valore della base
     */

    public double getBase(){ return b; }

    /**
     * Restituisce il valore dell'altezza
     * @return h altezza del rettangolo
     */

    public double getAltezza(){ return h; }

    /**
     * Restituisce il perimetro del rettangolo
     * @return Valore del perimetro
     */
    public double perimetro(){
        return ((b*2) + (h*2));
    }

    /**
     * Restituisce l'area del rettangolo
     * @return Valore dell'area del rettangolo
     */

    public double area(){
        return b*h;
    }

    /**
     * Disegna un rettangolo avente come base ed altezza i valori dell'oggeto su cui viene invocato
     */

    public void disegna(){

        for(double x = b;x>0;x--){
            System.out.print("*");
        }

        System.out.println();

        for(double y = h-2;y>0;y--){

            System.out.print("*");

            for(double x = b-2;x>0;x--){
                System.out.print(" ");
            }

            System.out.print("*\n");
        }

        for(double x = b;x>0;x--){
            System.out.print("*");
        }
    }

    /**
     * Restituisce la stringa "Rettangolo"
     * @return Stringa "Rettangolo"
     */

    public String toString(){
        return "Rettangolo";
    }

    /**
     * Testa il programma
     */

    public static void main(String[] args){

        Rettangolo r1 = new Rettangolo(6,2);
        Rettangolo r2 = new Rettangolo(5, 7);
        Rettangolo r3 = new Rettangolo(r1);

        System.out.println("\narea e perimetro del "+r1.toString()+" r1: \n"+r1.area()+"\n"+r1.perimetro());
        r1.disegna();

        System.out.println("\narea e perimetro del "+r1.toString()+" r2: \n"+r2.area()+"\n"+r2.perimetro());
        r2.disegna();

        System.out.println("\narea e perimetro del "+r1.toString()+" r3: \n"+r3.area()+"\n"+r3.perimetro());
        r3.disegna();

    }
}


