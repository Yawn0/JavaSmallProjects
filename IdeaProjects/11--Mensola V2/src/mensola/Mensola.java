package mensola;

public class Mensola {
    // Attributi
    private static final int MAX_NUM_VOLUMI = 15;
    private Libro[] volumi;

    public Mensola() {
        volumi = new Libro[MAX_NUM_VOLUMI];
    }

    public Mensola(Mensola mensola) {
        int posizione;
        volumi = new Libro[MAX_NUM_VOLUMI];
        for (posizione = 0; posizione < MAX_NUM_VOLUMI; posizione++) {
            if (mensola.getVolume(posizione) != null)
                volumi[posizione] = new Libro(mensola.getVolume(posizione));
        }
    }

    // Metodi
    public int setVolume(Libro libro, int posizione) {
        try {
            int x = 0;
            for (int i = 0; i < volumi.length; i++) {   // controlla se mensola piena
                if (volumi[i] == null) x = 1;
            }

            if (x == 0) return -1;  // mensola piena

            if (volumi[posizione] != null) {
                for (int i = volumi.length - 1; i > posizione; i--) {
                    if (volumi[i] == null && volumi[i - 1] != null) {
                        volumi[i] = new Libro(volumi[i - 1]);
                        volumi[i - 1] = null;
                    }
                }
                volumi[posizione] = new Libro(libro);
                return posizione; // restituisce la posizione di inserimento
            } else {
                x = 0;
                for (int i = 0; i < volumi.length && x == 0; i++) {
                    if (volumi[i] == null) {
                        volumi[i] = new Libro(libro);
                        x = 1;
                        posizione = i;
                    }
                }
                return posizione; // restituisce la posizione di inserimento
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            return -1; // posizione non valida
        }
    }

    public int setVolume(Libro libro) {
        try {
            int x = 0;
            for (int i = 0; i < volumi.length; i++) {   // controlla se mensola piena
                if (volumi[i] == null) x = 1;
            }
            if (x == 0) return -1;  // mensola piena

            x = 0;
            int i = 0;

            for (; i < volumi.length && x == 0; i++) {
                if (volumi[i] == null) {
                    volumi[i] = new Libro(libro);
                    x = 1;
                }
            }
            return i - 1;

        } catch (ArrayIndexOutOfBoundsException exception) {
            return -1; // posizione non valida
        }
    }

    public Libro getVolume(int posizione) {
        try {
            // restituisce una copia del libro in posizione
            return new Libro(volumi[posizione]);
        } catch (ArrayIndexOutOfBoundsException exception) {
            return null; // posizione non valida: nessun libro restituito
        }
    }

    public int rimuoviVolume(int posizione) {
        try {
            volumi[posizione] = null; // rimozione libro in posizione
            int i = posizione;
            for (; i < volumi.length - 1 && volumi[i + 1] != null; i++) {
                volumi[i] = new Libro(volumi[i + 1]);
            }
            volumi[i] = null;
            return i - 1; // restituisce la posizione liberata
        } catch (ArrayIndexOutOfBoundsException exception) {
            return -1; // posizione non valida
        }
    }

    public int getNumMaxVolumi() {
        return MAX_NUM_VOLUMI;
    }

    public int getNumVolumi() {
        int posizione, n = 0;
        for (posizione = 0; posizione < MAX_NUM_VOLUMI; posizione++)
            if (volumi[posizione] != null)
                n++;
        return n;
    }

    public static void main(String[] args) {
        Mensola mensola = new Mensola();
        System.out.println("Numero volumi: " + mensola.getNumVolumi());
        // inserimento volumi
        mensola.setVolume(new Libro("A", "C. Collodi", 150));
        mensola.setVolume(new Libro("B", "C. Perrault", 80));
        mensola.setVolume(new Libro("C", "C. Perrault", 50));

        mensola.setVolume(new Libro("D", "C. Collodi", 150), 12);
        mensola.setVolume(new Libro("E", "C. Perrault", 80), 9);
        mensola.setVolume(new Libro("F", "C. Collodi", 150), 2);
        mensola.setVolume(new Libro("G", "C. Perrault", 80));

        System.out.println("Numero volumi: " + mensola.getNumVolumi());

        // visualizzazione elenco volumi
        for (int i = 0; i < mensola.getNumMaxVolumi(); i++) {
            Libro libro = mensola.getVolume(i);
            if (libro != null)
                System.out.println("posizione: " + i + " | " + libro.getTitolo() + " " + libro.prezzo() + "€");
        }

        for (int i = 0; i < mensola.getNumMaxVolumi(); i++) {
            Libro libro = mensola.getVolume(i);
            if (libro == null)
                System.out.print("  0");    //stampa 0 se posizione vuota
            else
                System.out.print("  1");    //stampa 1 se posizione piena
        }

        mensola.rimuoviVolume(2);
        mensola.rimuoviVolume(13);

        System.out.println("\nNumero volumi: " + mensola.getNumVolumi());

        // visualizzazione elenco volumi
        for (int i = 0; i < mensola.getNumMaxVolumi(); i++) {
            Libro libro = mensola.getVolume(i);
            if (libro != null)
                System.out.println("posizione: " + i + " | " + libro.getTitolo() + " " + libro.prezzo() + "€");
        }

        for (int i = 0; i < mensola.getNumMaxVolumi(); i++) {
            Libro libro = mensola.getVolume(i);
            if (libro == null)
                System.out.print("  0");    //stampa 0 se posizione vuota
            else
                System.out.print("  1");    //stampa 1 se posizione piena
        }
    }
}