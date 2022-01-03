package es1matrici;
import java.io.*;
import java.util.Random;
/**
 * @LeonardoTocchet
 */
public class Es1matrici {

    static final int N = 10;

    static BufferedReader input = new BufferedReader(new InputStreamReader (System.in));

    // STATIC

    //    Da quello che ho capito il modificatore static permette di condividere
    //    la modifica di un membro ( metodo o variabile ) con tutto il programma
    //    perché viene associato alla classe a cui appartiene e non ad un'istanza di essa.
    //    Detto ciò non sono convinto del perché una costante come N, dichiarata qui sopra debba essere static,
    //    o perché metodi come 'clearScreen' utilizzato qui sotto, dove non viene modificata nessuna variabile
    //    debbano essere static.

    //    Si può dire che il modificatore static da ad un membro visibilità globale all'interno della classe
    //    in cui é stato inizializzato?

    public static void main(String[] args) {

        try{

            int n;

            do{
                System.out.printf("\nDefinire grandezza 'n' per matrice quadrata , con 'n' minore di %d e maggiore di 0: ",N);
                n = Integer.parseInt(input.readLine());
            }while(n>N || n<1);

            int[][] mat= new int[n][n];

            int scelta;

            do{
                menu();

                scelta = sceltaMenu();

                switch(scelta){
                    case 0:
                        break;
                    case 1:
                        insMatManual(n, mat);
                        break;
                    case 2:
                        insMatAuto(n, mat);
                        break;
                    case 3:
                        printMat(n, mat);
                        break;
                    case 4:
                        printMin(n, mat);
                        break;
                    case 5:
                        printMax(n, mat);
                        break;
                    case 6:
                        printMediaRiga(n, mat);
                        break;
                    case 7:
                        printMediaColonna(n, mat);
                        break;
                    case 8:
                        sommaSottoDiag(n, mat);
                        break;
                    case 9:
                        sommaDiag(n, mat);
                        break;
                    case 10:
                        sommaSopraDiag(n, mat);
                        break;
                    case 11:
                        if(detIfMatSym(n, mat)) System.out.println("La matrice é simmetrica");
                        else System.out.println("La matrice non é simmetrica");
                        break;
                    case 12:
                        clearScreen();
                        break;
                    default:
                        System.out.print("\nScelta non consentita\n");
                }

            }while (scelta!=0);

        }
        catch (Exception ex){
            System.err.println("Si è verificato un errore");
            ex.printStackTrace();
        }
    }

    static void clearScreen(){
        System.out.println("\n\nNOPE\n\n");     //non sono riuscito a trovare un metodo per pulire la console in JAVA :(
    }

    static boolean detIfMatSym(int n, int[][] mat){

        for(int i=0;i<n;i++){
            for(int j=1;j<n;j++){
                if(mat[i][j]!=mat[j][i]) return false;
            }
        }
        return true;
    }

    static void sommaSopraDiag(int n, int[][]mat){

        int somma=0;
        for (int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                somma=somma+mat[i][j];
            }
        }
        System.out.printf("La somma dei valori presenti sopra la diagonale principale é: %d\n",somma);
    }

    static void sommaDiag(int n, int[][] mat){

        int somma=0;
        for(int i=0;i<n;i++){
            somma +=  mat[i][i];
        }
        System.out.printf("La somma dei valori presenti sulla diagonale principale é : %d",somma);
    }

    static void sommaSottoDiag(int n, int[][] mat){

        int somma=0;
        for (int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                somma=somma+mat[i][j];
            }
        }
        System.out.printf("La somma dei valori presenti sotto la diagonale principale é: %d\n",somma);
    }

    static void printMediaColonna(int n, int[][] mat){

        for(int j=0;j<n;j++){
            int somma=0,media;
            for(int i=0;i<n;i++) {
                somma = somma + mat[i][j];
            }
            media=somma/n;
            System.out.printf("La media della colonna %d é %d\n",j+1,media);
        }
    }

    static void printMediaRiga(int n, int[][] mat){

        for(int i=0;i<n;i++){
            int somma=0,media;
            for(int j=0;j<n;j++) {
                somma = somma + mat[i][j];
            }
            media=somma/n;
            System.out.printf("La media della riga %d é %d\n",i+1,media);
        }
    }

    static void printMin(int n, int[][] mat) {

        int min= mat[0][0], j=1, posR = 0, posC = 0;
        for(int i = 0; i< n; i++){
            for(; j< n; j++){
                if(mat[i][j]<min){
                    min= mat[i][j];
                    posR = i;
                    posC = j;
                }
            }
            j=0;
        }
        System.out.printf("\nil valore minimo é : %d\n",min);
        System.out.printf("trovato in posizione : riga %d colonna %d \n\n",posR+1,posC+1);
    }

    static void printMax(int n, int[][] mat){

        int max= mat[0][0], j=1, posR = 0, posC = 0;
        for(int i = 0; i< n; i++){
            for(; j< n; j++){
                if(mat[i][j]>max){
                    max= mat[i][j];
                    posR = i;
                    posC = j;
                }
            }
            j=0;
        }
        System.out.printf("\nil valore massimo é : %d\n",max);
        System.out.printf("trovato in posizione : riga %d colonna %d \n\n",posR+1,posC+1);
    }

    static void printMat(int n, int[][] mat) {

        System.out.println("Stampa matrice");
        for(int i = 0; i< n; i++){
            for(int i2 = 0; i2< n; i2++){
                System.out.printf("%d\t", mat[i][i2]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void insMatAuto(int n, int[][] mat) {

        Random rand = new Random();

        for(int i = 0; i< n; i++){
            for(int i2 = 0; i2< n; i2++){
                mat[i][i2]= rand.nextInt(100);  //numero randomico da 0 a 99
            }
        }
    }

    static void insMatManual(int n, int[][] mat) throws IOException {

        for(int i = 0; i< n; i++){
            for(int i2 = 0; i2< n; i2++){
                do{
                    System.out.printf("Immettere il valore nella riga %d ,colonna %d: \n",i+1,i2+1);
                    mat[i][i2]= Integer.parseInt(input.readLine());
                }while(mat[i][i2]<0);
            }
        }
    }

    static int sceltaMenu() throws IOException {

        System.out.print("\nEffettua la tua scelta: ");
        int scelta = Integer.parseInt(input.readLine());
        System.out.printf("\nHai scelto %d\n", scelta);
        return scelta;
    }

    static void menu() {
        System.out.print("///////////////////////////////////////////////////////////////////////////////\n");
        System.out.println("\nDigitare: ");
        System.out.print("\n0 - per uscire\n\n");
        System.out.print("1 - per caricare la matrice manualmente\n");
        System.out.print("2 - per caricare la matrice automaticamente\n\n");
        System.out.print("3 - per stampare la matrice\n\n");
        System.out.print("4 - per stampare il valore minimo e la sua posizione\n");
        System.out.print("5 - per stampare il valore massimo e la sua posizione\n\n");
        System.out.print("6 - per stampare la media dei valori di ogni riga\n");
        System.out.print("7 - per stampare la media dei valori di ogni colonna\n\n");
        System.out.print("8 - per stampare la somma dei valori presenti sotto la diagonale principale\n");
        System.out.print("9 - per stampare la somma dei valori presenti sulla diagonale principale\n");
        System.out.print("10 - per stampare la somma dei valori presenti sopra la diagonale principale\n\n");
        System.out.print("11 - per determina se la matrice e' simmetrica\n\n");
        System.out.print("12 - per pulire lo schermo\n\n");
        System.out.print("///////////////////////////////////////////////////////////////////////////////\n");
    }
}