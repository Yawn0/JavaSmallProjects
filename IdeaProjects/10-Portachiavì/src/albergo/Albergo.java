package albergo;

import java.io.*;

/**
 * Classe Albergo
 * @author Leonardo Tocchet
 * @version 1.0
 */
public class Albergo {

    private final Chiave[] portachiavi;
    private String nome;

    /**
     * Crea un albergo con 10 camere assegnando un nume all'albergo
     *
     * @param nome definisce il nome dell'albergo
     */
    public Albergo(String nome) {
        portachiavi = new Chiave[10];
        for (int i = 0; i < portachiavi.length; i++)
            portachiavi[i] = new Chiave(i + 1);
        setNome(nome);
    }

    /**
     * Serve per restituire la propria chiave alla fine della permanenza
     *
     * @param numero numero della chiave da restituire
     * @return valore booleano di ritorno<br>
     *     se la chiave non é associata a nessun nome ritorna false<br>
     *     altrimenti ritorna true
     */
    public boolean posaChiave(int numero) {
        if(portachiavi[numero].getNome().equals(""))
            return false;
        else{
            portachiavi[numero - 1].setNome("");
            return true;
        }
    }

    /**
     * Serve per richiedere una camera d'albergo dando il proprio nome
     *
     * @param nome nome da assegnare alla chiave
     * @return se trova una camera libera ritorna il numero della camera assegnata<br>
     *     altrimenti ritorna -1
     */
    public int chiediChiave(String nome) {
        for (int i = 0; i < portachiavi.length; i++) {
            if (portachiavi[i].getNome().equals("")) {
                portachiavi[i].setNome(nome);
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Serve per richiedere una camera d'albergo dando il nuemro della camera che si vuole
     *
     * @param numero numero della camera richiesta
     * @return se non trova camere disponibili o se la camera é gia' occupata ritorna false<br>
     *     se riesce a riservare la camera ritorna true
     */
    public boolean chiediChiave(int numero) {

        if(!portachiavi[numero].getNome().equals(""))
            return false;

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String selezione = "";

        System.out.println("Inserisci il tuo nome : ");
        try {
            selezione = input.readLine();
        } catch (IOException e) {
            System.out.println("Errore I/O");
            e.printStackTrace();
        }
        if (portachiavi[numero].getNome().equals("")) {
            portachiavi[numero].setNome(selezione);
            return true;
        } else
            return false;
    }

    /**
     * Crea un file CSV con il nome dell'albergo su cui é stato invocato il metodo<br>
     *     e ci scrive tutti i dati delle chiavi dell'albergo
     */
    public void chiaviToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(getNome() + ".txt"));
            for (int i = 0; i < portachiavi.length; i++) {
                writer.write(portachiavi[i].getNome() + " ; " + portachiavi[i].getNum() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Errore IO");
            e.printStackTrace();
        }
    }

    /**
     * Dopo aver dato il nome del file CSV come parametro, richiede il nome dell'albergo su cui copiarne i dati
     *
     * @param nomeFile nome del file CSV
     */
    public void chiaviFromFile(String nomeFile) {
        String chiave;
        String[] datiChiave;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nomeFile));

            for (int i = 0, x = 0; i < portachiavi.length && x == 0; i++) {
                if ((chiave = reader.readLine()) != null) {
                    datiChiave = chiave.split(" ; ");
                    portachiavi[i].setNome(datiChiave[0]);
                } else x = 1;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Errore File");
            e.printStackTrace();
        } catch (IOException f) {
            System.out.println("Errore IO");
            f.printStackTrace();
        }
    }

    /**
     * Imposta il nome dell'albergo
     *
     * @param nome nome da assegnare all'albergo
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Ritorna il nome dell'albergo
     *
     * @return nome dell'albergo
     */
    public String getNome() {
        return nome;
    }

    /**
     * ritorna l'array di chaivi dell'albergo
     *
     * @return array di chiavi
     */
    public Chiave[] getPortachiavi() {
        return portachiavi;
    }
}