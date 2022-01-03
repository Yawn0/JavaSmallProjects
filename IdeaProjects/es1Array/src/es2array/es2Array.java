package es2array;
import java.io.*;
/**
 * @LeonardoTocchet
 */
public class es2Array {

    static int N = 8;

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader (System.in));

        try{

            int[] array = new int[N];

            do{
                System.out.print("Immettere il 1° voto: ");
                array[0] = Integer.parseInt(input.readLine());
            }while(array[0]<0 || array[0]>10);

            int max=array[0];
            int min=array[0];
            int somma=array[0];
            int i=1;

            for(;i<N;i++){

                String riga;

                do{
                    System.out.printf("Immettere il %d° voto: ",i+1);
                    riga = input.readLine();
                    array[i]= Integer.parseInt(riga);

                }while(array[i]<0 || array[i]>10);

                somma=somma+array[i];

                if((array[i] = Integer.parseInt(riga))>max){
                    max=array[i];
                }
                if((array[i] = Integer.parseInt(riga))<min){
                    min=array[i];
                }
            }

            System.out.println();

            int media=somma/i;

            for(i=0;i<N;i++){
                System.out.println((i+1)+"° voto: "+array[i]);
            }
            System.out.println("Il voto massimo é: "+max);
            System.out.println("Il voto minimo é: "+min);
            System.out.println("La media é: "+media);
        }
        catch (IOException ex){
            System.err.println("Si è verificato un errore");
            ex.printStackTrace();
        }
    }
}