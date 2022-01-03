
import java.io.*;

public class Divisione {

    public static int div(int x, int n)throws ArithmeticException{
        return n/x;
    }

    public static void main(String [] args) {
        int x = 0;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        do {

            System.out.print("Scrivi un intero (0 per finire): ");

            try {
                x = Integer.parseInt(input.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try{
                System.out.println("Risultato di div : " + div(x,12));
            }catch(ArithmeticException e){
                e.printStackTrace();
            }
        } while(true);
    }
}