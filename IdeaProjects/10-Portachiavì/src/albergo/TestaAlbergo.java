package albergo;

import java.io.*;

/**
 * Classe per testare le funzionalita' del package albergo
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class TestaAlbergo {

    /**
     * Creo un array di 5 alberghi, lo riempio parzialmente,chiedo all'utente in quale albergo vuole risiedere<br>
     *     e gli propongo un menu' ccon varie possibilita'
     */
    public static void main(String[] args) {

        Albergo[] listaAlberghi = new Albergo[5];

        listaAlberghi[0] = new Albergo("Chiaro");
        listaAlberghi[1] = new Albergo("Bello");
        listaAlberghi[2] = new Albergo("Brutto");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String selezione = "";
        String albergoSel = "";
        int x = 0, I = -1;      //flag per il do-while (x) e indice per individuare albergo selezionato (I)

        do {
            System.out.println("Decidi in quale albergo vuoi risiedere tra:\n");
            for (Albergo albergo : listaAlberghi)
                if (albergo != null)
                    System.out.println(albergo.getNome());

            try {
                albergoSel = input.readLine();
            } catch (IOException e) {
                System.out.println("Errore I/O");
                e.printStackTrace();
            }
            for (int i = 0; i < listaAlberghi.length; i++)
                if (listaAlberghi[i] != null)
                    if (listaAlberghi[i].getNome().equals(albergoSel))
                        I = i;

        } while (I == -1);

        do {

            System.out.println("""
                    Benevenuto all'albergo :  """ + albergoSel + """
                                        
                    Digita 'S' per stampare tutte le camere di tutti gli alberghi               
                    Digita 'chiave' se vuoi una camera
                    Digita 'ritorna' se hai terminato la tua permanenza
                    Digita 'CTF' per creare un file CSV dei dati dell'albergo
                    Digita 'CFF' per riempire un albergo con i dati presi da un file CSV
                    Digita 'X' per terminare la sessione
                    """);
            try {
                selezione = input.readLine();
            } catch (IOException e) {
                System.out.println("Errore I/O");
                e.printStackTrace();
            }

            switch (selezione) {

                case "S", "s" -> {
                    for (Albergo albergo : listaAlberghi) {
                        if (albergo != null) {
                            System.out.println(albergo.getNome());
                            for (Chiave chiave : albergo.getPortachiavi())
                                System.out.println(chiave.toString());
                        }
                    }
                }

                case "chiave" -> {
                    int f = 1;
                    do {
                        System.out.println("Inserisci il tuo nome o il numero [1 - 10] della camera che vuoi: ");

                        try {
                            selezione = input.readLine();
                        } catch (IOException e) {
                            System.out.println("Errore I/O");
                            e.printStackTrace();
                        }


                        char[] caratteri = selezione.toCharArray();

                        if (selezione.matches("[1-9]|10")) {
                            if (listaAlberghi[I].chiediChiave(Integer.parseInt(selezione) - 1))
                                f = 0;
                            else
                                System.out.println("Camera occupata");
                        } else if (String.valueOf(caratteri[0]).matches("[a-zA-Z]")) {
                            int num = listaAlberghi[I].chiediChiave(selezione);
                            if (num == -1)
                                System.out.println("Nessuna camera disponibile");
                            else
                                System.out.println("La tua camera é la numero " + num);
                            f = 0;
                        }
                    } while (f == 1);
                }

                case "ritorna" -> {
                    int j = 1;
                    do {
                        System.out.println("Inserisci il numero della tua chiave : ");
                        try {
                            selezione = input.readLine();
                        } catch (IOException e) {
                            System.out.println("Errore I/O");
                            e.printStackTrace();
                        }
                        if (!listaAlberghi[I].posaChiave(Integer.parseInt(selezione))) {
                            System.out.println("Camera errata");
                        } else
                            j = 0;
                    } while (j == 1);
                }

                case "CTF", "ctf" -> listaAlberghi[I].chiaviToFile();

                case "CFF", "cff" -> {
                    System.out.println("Inserisci il nome dell'albergo su cui copiare i dati : ");
                    try {
                        selezione = input.readLine();
                    } catch (IOException e) {
                        System.out.println("Errore I/O");
                        e.printStackTrace();
                    }
                    int g = 0;
                    for (int i = 0; i < listaAlberghi.length && g == 0; i++)
                        if (listaAlberghi[i] != null)
                            if (listaAlberghi[i].getNome().equals(selezione)) {
                                System.out.println("Inserisci il nome del file da cui vuoi prendere i dati : ");
                                try {
                                    listaAlberghi[i].chiaviFromFile(input.readLine());
                                } catch (IOException e) {
                                    System.out.println("Errore I/O");
                                    e.printStackTrace();
                                }
                                g = 1;
                            }
                    if (g == 0) System.out.println("Nome file o nome albergo non trovato/i");
                }

                case "x", "X" -> x = 1;

                default -> System.out.println("Digitazione errata");
            }
        } while (x == 0);
    }
}
