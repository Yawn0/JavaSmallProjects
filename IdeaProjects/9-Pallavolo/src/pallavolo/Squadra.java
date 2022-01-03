package pallavolo;

import java.io.*;

/**
 * Classe Squadra<br>
 * rappresentante una squadra di pallavolisti con nome e citta'
 *
 * @author Leonardo Tocchet
 * @version 2.0
 */
public class Squadra {
    private final Pallavolista[] team;
    private String nome;
    private String citta;

    /**
     * Instanzia una nuova Squadra con nome, citta' di appartenenza
     *
     * @param nome  nome della squadra dato in input
     * @param citta nome della citta' dato in input
     */
    public Squadra(String nome, String citta) {
        setCitta(citta);
        setNome(nome);
        team = new Pallavolista[11];
    }

    /**
     * Metodo che permette di aggiungere un pallavolista alla squadra
     *
     * @param p oggetto pallavolista dato in input da aggiungere alla squadra
     */
    public void aggiungiPallavolista(Pallavolista p) {
        int x = 0, i = 0;

        do {
            if (team[i] == null) {
                team[i] = p;
                x = 1;
            }
            i++;
        } while (x == 0 && i < team.length);

        if (x == 0) System.out.println("La squadra é al completo");
    }

    /**
     * Stampa i pallavolisti titolari di una squadra
     */
    public void stampaTitolari() {
        System.out.println("\nPallavolisti titolari della squadra : " + getNome());
        for (int i = 0; i < team.length && team[i] != null; i++)
            if (team[i].isTitolare())
                System.out.println(team[i].toString());
    }

    /**
     * Dato il numero della maglia, stampa il nome del pallavolista
     *
     * @param n numero maglia dato in input
     */
    public void nomeGiocatore(int n) {
        for (int i = 0; i < team.length && team[i] != null; i++)
            if (n == team[i].getNumero())
                System.out.println(team[i].getNome());
    }

    /**
     * Restituisce un oggetto pallavolista in base al numero maglia del giocatore passatogli
     *
     * @param n numero maglia del pallavolista da cercare
     * @return oggetto Pallavolista
     */
    public Pallavolista copyGiocatore(int n) {
        for (int i = 0; i < team.length && team[i] != null; i++)
            if (n == team[i].getNumero()) return team[i];
        return null;
    }

    /**
     * Metodo che stampa la squadra con nome, nome citta' e tutti i suoi giocatori
     */
    public void visualizzaTeam() {
        System.out.println("\nNOME \t|  NUMERO\t| RUOLO");
        for (int i = 0; i < team.length && team[i] != null; i++)
            System.out.println(team[i].getNome() + "\t|\t " + team[i].getNumero() + "\t\t| " + team[i].getRuolo());
    }

    /**
     * permette di creare un file di testo in formato CSV (Comma Separated Values)<br>
     * in cui ogni riga corrisponde ad un giocatore e al valore della posizione<br>
     * all’interno dell’array, del nome, del numero di maglia e del ruolo separati dal carattere “;”
     *
     * @param nomeSquadra nome della squadra con cui compilare il file
     * @exception IOException          eccezione di tipo I/O
     * @exception NullPointerException eccezione di tipo Runtime
     */

    public void salvaGiocatori(String nomeSquadra) {
        try {
            BufferedWriter writeR = new BufferedWriter(new FileWriter(nomeSquadra + ".txt"));
            for (int i = 0; i < team.length && team[i] != null; i++)
                writeR.write((i + 1) + " ; " + team[i].toString() + " ; " + team[i].isTitolare() + "\n");
            writeR.close();
            System.out.println("File " + nomeSquadra + ".txt creato");
        } catch (IOException e) {
            System.err.println("Si é verificato un errore I/O");
            e.printStackTrace();
        } catch (NullPointerException f) {
            System.err.println("Si é verificato un errore NullPointer");
            f.printStackTrace();
        }
    }

    /**
     * permette di copiare una squadra di pallavolisti da un file di testo<br>
     * in un altra squadra di pallavolisti
     *
     * @param nomeFile nome del file da cui copiare la squadra
     */

    public void caricaGiocatori(String nomeFile) {
        String giocatore;
        String[] p;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nomeFile));
            for (int i = 0, x = 0; i < team.length && x == 0; i++) {
                if ((giocatore = reader.readLine()) != null) {
                    p = giocatore.split(" ; ");
                    aggiungiPallavolista(new Pallavolista(p[1], Integer.parseInt(p[2]), p[3], Boolean.parseBoolean(p[4])));
                } else x = 1;
            }
            reader.close();
            System.out.println("File " + nomeFile + " copiato in squadra " + getNome());
        } catch (IOException e) {
            System.err.println("Si é verificato un errore I/O");
            e.printStackTrace();
        }
    }

    /**
     * Restituisce il nome della squadra
     *
     * @return nome della squadra
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell squadra
     *
     * @param nome Stringa data in input
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il nome della citta' della squadra
     *
     * @return Stringa rappresentante il nome della citta' della squadra
     */
    public String getCitta() {
        return citta;
    }

    /**
     * Imposta il nome della citta' della squadra
     *
     * @param citta Stringa data in input
     */
    public void setCitta(String citta) {
        this.citta = citta;
    }

    /**
     * Restituisce l'array contenente i giocatori della squadra
     *
     * @return array contenente i giocatori della squadra
     */
    public Pallavolista[] getTeam() {
        return team;
    }

}
