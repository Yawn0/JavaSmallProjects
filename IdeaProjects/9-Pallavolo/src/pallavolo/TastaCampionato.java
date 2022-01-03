package pallavolo;

import java.io.*;
import java.time.Year;

/**
 * Classe TestaCampionato<br>
 * classe main del programma
 *
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class TastaCampionato {

    /**
     * Campionato 1.
     */
    static Campionato c1;

    /**
     * 1) Istanzio le tre squadre, 2) le riempio(non del tutto) di pallavolisti, <br>
     * 3) istanzio il campionato 4) creo un menu' per far interagire l'utente
     */
    public static void main(String[] args) {

        //1

        Squadra s1 = new Squadra("Fieri", "Lunamatrona");
        Squadra s2 = new Squadra("Energici", "Vagli Sopra");
        Squadra s3 = new Squadra("Acuti", "Vagli Sotto");

        //2

        s1.aggiungiPallavolista(new Pallavolista("Sergio ", 23, "centrale", true));
        s1.aggiungiPallavolista(new Pallavolista("Arturo ", 11, "palleggiatore", true));
        s1.aggiungiPallavolista(new Pallavolista("Maestro", 34, "Banda", true));
        s1.aggiungiPallavolista(new Pallavolista("Ciccio ", 33, "libero", false));
        s1.aggiungiPallavolista(new Pallavolista("Sberlo ", 55, "opposto", false));

        s2.aggiungiPallavolista(new Pallavolista("Ginger ", 88, "centrale", false));
        s2.aggiungiPallavolista(new Pallavolista("False  ", 99, "banda", true));
        s2.aggiungiPallavolista(new Pallavolista("Paolo  ", 12, "libero", true));
        s2.aggiungiPallavolista(new Pallavolista("Fiero  ", 13, "palleggiatore", true));
        s2.aggiungiPallavolista(new Pallavolista("Paolino", 90, "oppsto", false));

        s3.aggiungiPallavolista(new Pallavolista("Pirlo  ", 50, "palleggiatore", true));
        s3.aggiungiPallavolista(s2.copyGiocatore(88));
        s3.aggiungiPallavolista(s1.copyGiocatore(34));
        s3.aggiungiPallavolista(s1.copyGiocatore(11));
        s3.aggiungiPallavolista(s2.copyGiocatore(13));
        s3.aggiungiPallavolista(s1.copyGiocatore(33));

        //3

        c1 = new Campionato(Year.of(2021), s1, s2, s3);

        //4

        int x = 0;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        do {
            String selezione = "";

            try {
                System.out.println(""" 
                                                            
                        ################################################
                                                        
                        Digita '1'  per cambiare numero maglia ad un giocatore 
                        Digita '2'  per aggiungere un giocatore ad una squadra
                        Digita '3'  per cambiare i titolari di una squadra
                                                            
                        Digita 'G'  per stampare il nome di un giocatore dato il suo numero
                        Digita 'S'  per stampare tutti i partecipanti
                        Digita 'ST' per stampare i titolari delle squadre
                                                            
                        Digita 'WF' per scrivere il contenuto di una squadra su un file
                        Digita 'RF' per riempire una squadra con giocatori da un file
                                                            
                        Digita 'X'  per terminare la sessione                                    
                        """);
                selezione = input.readLine();
            } catch (IOException e) {
                System.err.println("Si é verificato un errore I/O");
                e.printStackTrace();
            }

            switch (selezione) {
                case "1" -> cambiaNumero();
                case "2" -> aggiungiGiocatore();
                case "3" -> cambiaTitolari();
                case "G", "g" -> stampaGiocatore();
                case "S", "s" -> c1.stampaSquadre();
                case "ST", "st", "sT", "St" -> stampaTitolari();
                case "WF", "Wf", "wF", "wf" -> scriviSuFile();
                case "RF", "Rf", "rF", "rf" -> riempiDaFile();
                case "X", "x" -> x = 1;
                default -> System.out.println("Digitazione scorretta\n");
            }
        } while (x != 1);
    }

    /**
     * Metodo per cambiare il numero maglia di un pallavolista da terminale
     */
    public static void cambiaNumero() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String nomeSquadra = "";
        int nuovoNum = 0;

        try {
            System.out.println("Digitare il nome del giocatore interessato");
            nomeSquadra = input.readLine();
        } catch (IOException e) {
            System.err.println("Si é verificato un errore I/O");
            e.printStackTrace();
        }

        int x = 0;

        for (int i = 0; i < c1.getSquadre().length; i++)
            for (int j = 0; j < c1.getSquadre()[i].getTeam().length && c1.getSquadre()[i].getTeam()[j] != null; j++)
                if (c1.getSquadre()[i].getTeam()[j].getNome().equals(nomeSquadra)) {
                    try {
                        System.out.println("Digitare il nuovo numero");
                        nuovoNum = Integer.parseInt(input.readLine());
                    } catch (IOException e) {
                        System.err.println("Si é verificato un errore I/O");
                        e.printStackTrace();
                    }
                    c1.getSquadre()[i].getTeam()[j].impostaNumeroMaglia(nuovoNum);
                    x = 1;
                }
        if (x == 0) System.out.println("Nome non trovato");
    }

    /**
     * Metodo per aggiungere un pallavolista ad una squadra da terminale
     */
    public static void aggiungiGiocatore() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String nomeSquadra = "", nomeGiocatore = "", ruolo = "";
        int numero = 0, x = 0;
        boolean titolare = false;

        try {
            System.out.println("Digita il nome della squadra interessata");
            nomeSquadra = input.readLine();
        } catch (IOException e) {
            System.err.println("Si é verificato un errore I/O");
            e.printStackTrace();
        }

        for (int i = 0; i < c1.getSquadre().length; i++) {
            if (c1.getSquadre()[i].getNome().equals(nomeSquadra)) {
                try {
                    System.out.println("Digita il nome del nuovo giocatore");
                    nomeGiocatore = input.readLine();

                    System.out.println("Digita il numero del nuovo giocatore");
                    numero = Integer.parseInt(input.readLine());

                    System.out.println("Digita il ruolo del nuovo giocatore\n(Palleggiatore / centrale / banda / libero / opposto)");
                    ruolo = input.readLine();

                    System.out.println("Decidi se gioca o sta in panchina\n(true / false)");
                    titolare = Boolean.parseBoolean(input.readLine());

                } catch (IOException e) {
                    System.err.println("Si é verificato un errore I/O");
                    e.printStackTrace();
                }
                c1.getSquadre()[i].aggiungiPallavolista(new Pallavolista(nomeGiocatore, numero, ruolo, titolare));
                x = 1;
            }
        }
        if (x == 0) System.out.println("\nSquadra non trovata\n");
    }

    /**
     * Metodo per cambiare lo stato di titolare ai pallavolisti da terminale
     */
    public static void cambiaTitolari() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String nomeSquadra = "";

        try {
            System.out.println("Digitare il nome del giocatore interessato");
            nomeSquadra = input.readLine();
        } catch (IOException e) {
            System.err.println("Si é verificato un errore I/O");
            e.printStackTrace();
        }

        int x = 0;

        for (int i = 0; i < c1.getSquadre().length; i++)
            for (int j = 0; j < c1.getSquadre()[i].getTeam().length && c1.getSquadre()[i].getTeam()[j] != null; j++)
                if (c1.getSquadre()[i].getTeam()[j].getNome().equals(nomeSquadra)) {
                    c1.getSquadre()[i].getTeam()[j].cambiaTitolare();
                    x = 1;
                }
        if (x == 0) System.out.println("Nome non trovato");
    }

    /**
     * Mtodo per stampare tutte le squadre del campionato da terminale.
     */
    public static void stampaGiocatore() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int numeroGiocatore = 0;

        try {
            System.out.println("Digitare il numero del giocatore interessato");
            numeroGiocatore = Integer.parseInt(input.readLine());
        } catch (IOException e) {
            System.err.println("Si é verificato un errore I/O");
            e.printStackTrace();
        }

        int x = 0;

        for (int i = 0; i < c1.getSquadre().length; i++)
            for (int j = 0; j < c1.getSquadre()[i].getTeam().length && c1.getSquadre()[i].getTeam()[j] != null; j++)
                if (c1.getSquadre()[i].getTeam()[j].getNumero() == numeroGiocatore) {
                    c1.getSquadre()[i].nomeGiocatore(numeroGiocatore);
                    x = 1;
                }
        if (x == 0) System.out.println("Numero non trovato");
    }

    /**
     * Metodo per stampare tutti i pallavolisti in campo da terminale
     */
    public static void stampaTitolari() {
        for (int i = 0; i < c1.getSquadre().length; i++) {
            c1.getSquadre()[i].stampaTitolari();
        }
    }

    public static void scriviSuFile() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String nomeSquadra = "";
        int x = 0;

        try {
            System.out.println("Digita il nome della squadra interessata");
            nomeSquadra = input.readLine();
        } catch (IOException e) {
            System.err.println("Si é verificato un errore I/O");
            e.printStackTrace();
        }

        for (int i = 0; i < c1.getSquadre().length && x == 0; i++)
            if (c1.getSquadre()[i].getNome().equals(nomeSquadra)) {
                c1.getSquadre()[i].salvaGiocatori(nomeSquadra);
                x = 1;
            }

        if (x == 0) System.out.println("\nSquadra non trovata\n");

    }

    public static void riempiDaFile() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String nomeFile = "", nomeSquadra = "";
        int x = 0;

        try {
            System.out.println("Digita il nome del file da cui copiare la squadra");
            nomeFile = input.readLine();

            System.out.println("Digita il nome della squadra su cui copiarla");
            nomeSquadra = input.readLine();
        } catch (IOException e) {
            System.err.println("Si é verificato un errore I/O");
            e.printStackTrace();
        }

        for (int i = 0; i < c1.getSquadre().length && x == 0; i++)
            if (c1.getSquadre()[i].getNome().equals(nomeSquadra)) {
                c1.getSquadre()[i].caricaGiocatori(nomeFile);
                x = 1;
            }

        if (x == 0) System.out.println("\nSquadra non trovata\n");
    }
}
