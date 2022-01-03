package albero;

public class Albero {
    private NodoAlbero ptr;

    public Albero(){
        ptr=null;
    }

/* public void visitaAnticipata() {
        visitaAnticipata(ptr);
    }

    private void visitaAnticipata(NodoAlbero p) {
        if (p == null)
            return;
        System.out.println(p.getInfo());
        if (p.getFirstChild() != null)
            visitaAnticipata(p.getFirstChild());
        if (p.getFirstSibling() != null)
            visitaAnticipata(p.getFirstSibling());
    }
*/

    // esercizio 1
    public int getProfondita(String k){
        class innerGetProfondita{
            private int valoreProfondita(NodoAlbero nodo){    // conta i livelli di profondita del sottoalbero
                int count = 0;
                while(nodo.getFirstChild() != null){
                    nodo = nodo.getFirstChild();
                    count++;
                }
                return count;
            }
            private NodoAlbero cercaNodo(NodoAlbero nodo, String k){    // ritorna il nodo in cui é stato trovato il valore
                if(nodo == null) return null;

                if(nodo.getInfo().equals(k))
                    return nodo;

                if(nodo.getFirstChild() != null)
                    return cercaNodo(nodo.getFirstChild(),k);

                if(nodo.getFirstSibling() != null)
                    return cercaNodo(nodo.getFirstSibling(),k);

                return null;
            }
        }
        NodoAlbero radice = new innerGetProfondita().cercaNodo(ptr,k);  // ptr --> radice dell'albero della classe Albero
        if(radice != null)                                              // radice --> radice sottoalbero
            return new innerGetProfondita().valoreProfondita(radice);

        return -1;
    }

/*
    private int cercaValore(NodoAlbero nodo, String stringa){
        NodoAlbero n1 = null;
        if(nodo == null) return -1;
        if(nodo.getInfo().equals(stringa)){
            n1 = nodo;
            int count = 0;
            class visitaSottoAlbero{
                public void visitaAnticipata(NodoAlbero n1){
                    if (n1 == null)
                        return;
                    count++;
                    System.out.println(n1.getInfo());
                    if (n1.getFirstChild() != null)
                        visitaAnticipata(n1.getFirstChild());
                    if (n1.getFirstSibling() != null)
                        visitaAnticipata(n1.getFirstSibling());
                }
            }
            new visitaSottoAlbero().visitaAnticipata(n1);
            return count;
        }
    }

    private NodoAlbero cercaNodo(NodoAlbero p,String chiave) {

        NodoAlbero p1=null;
        if (p == null)
            return null;
        if (p.getInfo().equals(chiave)) return p;  //ricerca con successo
        if (p.getFirstChild() != null)              //ricerca verso il figlio
            p1=cercaNodo(p.getFirstChild(),chiave);
        if (p1!=null) return p1;                    //interruzione della ricorsione
        if (p.getFirstSibling() != null)            //ricerca verso il fratello
            p1=cercaNodo(p.getFirstSibling(),chiave);
        if (p1!=null) return p1;                    //interruzione della ricorsione
        return null;
    }
*/
//    public void aggiungiFratello(String chiave, String info) {
//        NodoAlbero n=new NodoAlbero(info) ;
//        if (ptr==null){ //albero vuoto inserimento radice
//            ptr=n;
//            return;
//        }
//        NodoAlbero p;
//        p=cercaNodo(ptr,chiave);
//        if (p!=null) p.setFirstSibling(n);
//        else System.out.println ("nodo "+chiave+" non trovato!" );
//
//    }
//    public void aggiungiFiglio(String chiave, String info) {
//        NodoAlbero n=new NodoAlbero(info) ;
//        if (ptr==null){ //albero vuoto inserimento radice
//            ptr=n;
//            return;
//        }
//        NodoAlbero p;
//        p=cercaNodo(ptr,chiave);
//        if (p!=null) p.setFirstChild(n);
//        else System.out.println ("nodo  "+chiave+" non trovato!" );
//    }
//
//
//    public static void main(String[] args) {
//        Albero a=new Albero();
//        a.aggiungiFiglio('a', 'a');
//
//        a.aggiungiFiglio('a', 'b');
//        a.aggiungiFratello('b','c');
//        a.aggiungiFratello('c','d');
//
//        a.aggiungiFiglio('b', 'e');
//        a.aggiungiFratello('e','f');
//        a.aggiungiFratello('f','g');
//
//        a.aggiungiFiglio('c', 'h');
//        a.aggiungiFratello('h','i');
//
//        a.aggiungiFiglio('d','l');
//        a.visitaAnticipata();
//    }
}