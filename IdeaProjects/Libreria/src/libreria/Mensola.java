package libreria;

public class Mensola {

 // Attributi
  private static final int MAX_NUM_VOLUMI=15;
  private Libro volumi[];

  public Mensola() {
    volumi=new Libro[MAX_NUM_VOLUMI];
  }

  public Mensola(Mensola mensola) {
    int posizione;
    volumi=new Libro[MAX_NUM_VOLUMI];
    for(posizione=0; posizione<MAX_NUM_VOLUMI; posizione++) {
      if (mensola.getVolume(posizione)!=null)
        volumi[posizione]=new Libro(mensola.getVolume(posizione));
    }
  }

 // Metodi
  public int setVolume(Libro libro, int posizione) {
    try {
     // inserimento copia di libro in posizione
      if (volumi[posizione]!=null)
        return -2; // posizione occupata
      volumi[posizione]=new Libro(libro);
      return posizione; // restituisce la posizione di inserimento
    }
    catch (ArrayIndexOutOfBoundsException exception) {
      return -1; // posizione non valida
    }
  }

  public Libro getVolume(int posizione) {
    try {
    // restituisce una copia del libro in posizione
      return new Libro(volumi[posizione]);
    }
    catch (ArrayIndexOutOfBoundsException exception) {
      return null; // posizione non valida: nessun libro restituito
    }
    catch (NullPointerException exception) {
      return null; // posizione vuota: nessun libro restituito
    }
  }

  public int rimuoviVolume(int posizione) {
    try {
      volumi[posizione]=null; // rimozione libro in posizione
      return posizione; // restituisce la posizione liberata
    }
    catch (ArrayIndexOutOfBoundsException exception) {
      return -1; // posizione non valida
    }
  }

  public int getNumMaxVolumi() {
    return MAX_NUM_VOLUMI;
  }

  public int getNumVolumi() {
    int posizione, n=0;
    for (posizione=0; posizione<MAX_NUM_VOLUMI; posizione++)
      if (volumi[posizione]!=null)
        n++;
    return n;
  }
}