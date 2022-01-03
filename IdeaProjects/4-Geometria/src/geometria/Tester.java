package geometria;

/**
 * Questa e' la classe Tester per testare le classi Punto e Rettangolo del package Geometria
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */

public class Tester {
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

        Rettangolo r1 = new Rettangolo();
        Rettangolo r2 = new Rettangolo(p2,8,4);

        System.out.println("\nArea e perimetro del "+r1.toString()+" r1: "+r1.area()+"  "+r1.perimetro());
        r1.datiRettangolo();
        r1.disegna();

        System.out.println("\nArea e perimetro del "+r2.toString()+" r2: "+r2.area()+"  "+r2.perimetro());
        r2.datiRettangolo();
        r2.disegna();

        Punto p3 = new Punto(6.8 , -6.8);
        r1.spostaRettangolo(p3);
        r1.datiRettangolo();

        Punto p4 = new Punto(10 , 2.4);
        r2.spostaRettangolo(p4);
        r2.datiRettangolo();

    }
}
