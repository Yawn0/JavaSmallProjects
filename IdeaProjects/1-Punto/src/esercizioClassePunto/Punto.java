
package esercizioClassePunto;

/**
 * Esercizio sulla classe "Punto"
 * Classe principale del programma
 *
 * @author Leonardo Tocchet
 * @version 1.0.0.0
 * @param x coordinata dell'asse delle ascisse del punto creato
 * @param y coordinata dell'asse delle ordinate del punto creato
 */

public class Punto {

    private double x;
    private double y;

    /**
     * Crea un'istanza della classe Punto, a cui assegna i valori di default x=1.2 e y=2.3
     */

    public Punto(){
        setX(1.2);
        setY(2.3);
    }

    /**
     * Crea un'istanza della classe Punto, a cui assegna i valori passati come parametri
     * utilizzando i metodi 'setX' e 'setY'
     * @param x coordinata punto creato
     * @param y coordinata punto creato
     */

    public Punto(double x, double y){
        setX(x);
        setY(y);
    }

    /**
     * Imposta il valore dell'ascissa
     * @param x coordinata punto creato
     */

    public void setX(double x) { this.x = x; }

    /**
     * Imposta il valore dell'ordinata
     * @param y coordinata punto creato
     */

    public void setY(double y) { this.y = y; }

    /**
     * Recupera il valore dell'ascissa
     * @return Restituisce il valore dell'ascissa
     */

    public double getX(){ return x; }

    /**
     * Recupera il valore dell'ordinata
     * @return Restituisce il valore dell'ordinata
     */

    public double getY(){ return y; }

    /**
     * Calcola la distanza tra due punti
     * @param p oggetto dal quale si ricavano le coordinate da sottrarre all'oggetto su cui viene invocato il metodo
     * @return restituisce l'ipotenusa tra i due punti
     */

    public double distanza( Punto p ){
        double dx = x - p.getX();
        double dy = y - p.getY();
        return Math.sqrt( ( dx * dx ) + ( dy * dy ) );
    }

    /**
     * Calcola la distanza tra un punto e l'origine degli assi
     * @return restituisce l'ipotenusa tra il punto e il punto zero degli assi cartesiani
     */

    public double distanzaOrigine(){
        return Math.sqrt( ( x * x ) + ( y * y ) );
    }

    /**
     * Determina se i due punti sono uguali
     * @return restituisce true se i due punti sono uguali, false altrimenti
     * @param p oggetto su cui viene invocato il metodo
     */

    public  boolean equals(Punto p){
        return ( ( x == p.x ) && ( y == p.y ) );
    }

    /**
     * Determina in quale quadrante e' posizionato il punto
     * @return restituisce il numero del quadrante
     */

    public int quadrante(){
        if(x>0 && y>0) return 1;
        else if(x<0 && y>0) return 2;
        else if(x<0 && y<0) return 3;
        else return 4;
    }

    /**
     * Trasforma le coordinate del punto in una stringa
     * @return restituisce una stringa con le coordinate del punto
     */

    public String toString(){
        return ("( "+x+" ; "+y+" )");
    }

    /**
     * Testa il programma
     */

    public static void main(String[] args){
        Punto p1 = new Punto();
        Punto p2 = new Punto(4.5 , -5.4);

        System.out.println("\nCoordinate di p1: "+p1.toString());
        System.out.println("Coordinate di p2: "+p2.toString()+"\n");

        System.out.println("La distanza tra p2 e p1 é: "+p2.distanza(p1)+"\n");

        System.out.println("La distanza tra p1 e il punto zero degli assi é: "+p1.distanzaOrigine()+"\n");

        System.out.println("La distanza tra p2 e il punto zero degli assi é: "+p2.distanzaOrigine()+"\n");

        if(p1.equals(p2)) System.out.println("I due punti sono uguali\n");
        else System.out.println("I due punti sono differenti\n");

        System.out.println("p1 appartiene al "+p1.quadrante()+"° quadrante");
        System.out.println("p2 appartiene al "+p2.quadrante()+"° quadrante");
    }
}
