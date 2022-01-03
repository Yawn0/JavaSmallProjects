package geometria;

/**
 * Questa e' la classe Rettangolo
 *
 * @author Leonardo Tocchet
 * @version 2.0
 */

public class Rettangolo {

    private Punto p;
    private double b;
    private double h;

    /**
     * Crea un'istanza della classe Rettangolo, che ha come punto p(3.5,4.5), base b=5 e altezza h=7
     * @since 25/11/20
     */

    public Rettangolo(){
        p = new Punto(3.5,4.5);
        setBase(5);
        setAltezza(7);
    }

    /**
     * Crea un'istanza della classe Rettangolo, a cui assegna i valori passati come parametri.
     * Il punto p rappresenta il vertice del rettangolo in alto a sinistra
     * @param p vertice in alto a sinistra del rettangolo
     * @param b base del rettangolo
     * @param h altezza del rettangolo
     * @since 25/11/20
     */

    public Rettangolo(Punto p, double b, double h){
        this.p = p;
        setBase(b);
        setAltezza(h);
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
     * @deprecated
     */

    public double getBase(){ return b; }

    /**
     * Restituisce il valore dell'altezza
     * @return h altezza del rettangolo
     * @deprecated
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

        System.out.println("\n\n");
    }

    /**
     * Sposta il rettangolo, in base al punto p, passato come parametro
     * @param p vertice in alto a sinistra del rettangolo
     * @since 25/11/20
     */

    public void spostaRettangolo(Punto p){
        this.p=p;
    }

    /**
     * Stampa gli attributi del rettangolo: il punto p (nella forma (x;y)), il valore della base e il
     * valore dell’altezza
     * @since 25/11/20
     */

    public void datiRettangolo(){
        System.out.println("Attributi rettangolo: \npunto "+p.toString()+"\nvalore base: "+b+"\nvalore altezza: "+h);
    }

    /**
     * Restituisce la stringa "Rettangolo"
     * @return Stringa "Rettangolo"
     */

    public String toString(){
        return "Rettangolo";
    }

}



