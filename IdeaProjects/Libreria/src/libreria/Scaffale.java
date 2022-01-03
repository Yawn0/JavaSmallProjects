package libreria;

public class Scaffale {
 // Attributi
 private static final int NUM_RIPIANI=5;
 private Mensola ripiani[];

 // Costruttori

 public Scaffale() {
   int ripiano;
   ripiani=new Mensola[NUM_RIPIANI];
   for (ripiano=0; ripiano<NUM_RIPIANI; ripiano++)
    ripiani[ripiano]=new Mensola();
 }

 public Scaffale(Scaffale scaffale) throws PosizioneNonValida {
   int ripiano, posizione;
   Libro libro;
   ripiani=new Mensola[NUM_RIPIANI];
    for (ripiano=0; ripiano<NUM_RIPIANI; ripiano++) {
       ripiani[ripiano]=new Mensola();
       for ( posizione=0; posizione<ripiani[ripiano].getNumMaxVolumi();ripiano++) {
         libro=scaffale.getLibro(ripiano, posizione);
         if (libro!=null)
           ripiani[ripiano].setVolume(libro, posizione);
       }
   }
 }

 // Metodi

 public Libro getLibro(int ripiano, int posizione) throws PosizioneNonValida {
   try {
     return ripiani[ripiano].getVolume(posizione);
   }
   catch (ArrayIndexOutOfBoundsException exception) {
     throw new PosizioneNonValida(posizione, ripiano);
   }
 }

 public void setLibro(Libro libro, int ripiano, int posizione) throws PosizioneNonValida, PosizioneNonVuota {
   int err;
   try {err = ripiani[ripiano].setVolume(libro, posizione);
     if (err <0)
       if (err ==-1)
         throw new PosizioneNonValida(posizione, ripiano);
       else
         throw new PosizioneNonVuota(posizione, ripiano);
   }
   catch (ArrayIndexOutOfBoundsException exception) {
     throw new PosizioneNonValida(posizione, ripiano);
   }
 }

 public void rimuoviLibro(int ripiano, int posizione) throws PosizioneNonValida {
   try {
     if (ripiani[ripiano].rimuoviVolume(posizione)<0)
       throw new PosizioneNonValida(posizione, ripiano);
   }
   catch (ArrayIndexOutOfBoundsException exception) {
     throw new PosizioneNonValida(posizione, ripiano);
   }
 }

 public int getNumRipiani() {
   return NUM_RIPIANI;
 }

 public int getNumMaxLibri() {
   int ripiano, n=0;
   for (ripiano=0; ripiano<NUM_RIPIANI; ripiano++) {
     n += ripiani[ripiano].getNumMaxVolumi();
   }
   return n;
 }

 public int getNumLibri() {
   int ripiano, n=0;
   for (ripiano=0; ripiano<NUM_RIPIANI; ripiano++) {
     n += ripiani[ripiano].getNumVolumi();
   }
   return n;
 }

 public int getNumLibri(int ripiano) throws PosizioneNonValida {
   try {
     return ripiani[ripiano].getNumVolumi();
   }
   catch (ArrayIndexOutOfBoundsException exception) {
     throw new PosizioneNonValida(0, ripiano);
   }
 }

 public static void main (String[] args) {
   Scaffale scaffale = new Scaffale();
   Libro libro;

 // creazione di tre oggetti di tipo Libro

   Libro l1=new Libro("Pinocchio", "C. Collodi", 150);
   Libro l2=new Libro("Pollicino", "C. Perrault", 80);
   Libro l3=new Libro( "La bella addormentata nel bosco", "C. Perrault", 50);

 // inserimento mensole #0 e #1

   try {
     scaffale.setLibro(l1, 0, 10);
     scaffale.setLibro(l2, 0, 0);
     libro = new Libro("Cappuccetto Rosso", "F.lli Grimm", 150);
     scaffale.setLibro(libro, 1, 1);
   }
   catch (PosizioneNonValida exception) {
     System.out.println(exception);
   }
   catch (PosizioneNonVuota exception) {
     System.out.println(exception);
   }

 // test errori inserimento

   try {
     scaffale.setLibro(l3, 10, 0);
   }
   catch (PosizioneNonValida exception) {
     System.out.println(exception);
   }
   catch (PosizioneNonVuota exception) {
     System.out.println(exception);
   }

   try {
     scaffale.setLibro(l3, 0, 20);
   }
   catch (PosizioneNonValida exception) {
     System.out.println(exception);
   }
   catch (PosizioneNonVuota exception) {
     System.out.println(exception);
   }

   try {
     scaffale.setLibro(l3, 0, 10);
   }
   catch (PosizioneNonValida exception) {
     System.out.println(exception);
   }
   catch (PosizioneNonVuota exception) {
     System.out.println(exception);
   }

 // inserimento mensola #1

   try {
     scaffale.setLibro(l3, 1, 0);
   }
   catch (PosizioneNonValida exception) {
     System.out.println(exception);
   }
   catch (PosizioneNonVuota exception) {
     System.out.println(exception);
   }

 // visualizzazione contenuto mensole

   try {
     for (int ripiano=0; ripiano<scaffale.getNumRipiani(); ripiano++) {
       for ( int posizione=0; posizione<scaffale.getNumLibri(ripiano);posizione++) {
         libro = scaffale.getLibro(ripiano, posizione);
         if (libro!=null)
           System.out.println( "ripiano: "+ripiano+ " posizione: "+posizione+" -> "+ libro.getTitolo()+" "+libro.prezzo()+"€");
       }
     }
   }
   catch (PosizioneNonValida exception) {
     System.out.println(exception);
   }

 // modifica titolo e autore libro estratto da scaffale

   try {
     libro=scaffale.getLibro(0,0);
     if ( libro!=null) {
       libro.setTitolo("Sussi e Biribissi");
       libro.setAutore("Collodi nipote");
     }
   }
   catch (PosizioneNonValida exception) {
     System.out.println(exception);
   }

 // visualizzazione contenuto mensole

   try {
     for ( int ripiano=0; ripiano<scaffale.getNumRipiani(); ripiano++) {
       for ( int posizione=0; posizione<scaffale.getNumLibri(ripiano);posizione++) {
         libro = scaffale.getLibro(ripiano, posizione);
         if (libro!=null)
           System.out.println( "ripiano: "+ripiano+ " posizione: "+posizione+" -> "+libro.getTitolo()+" "+libro.prezzo()+"€");
       }
     }
   }
   catch (PosizioneNonValida exception) {
     System.out.println(exception);
   }
 }
}