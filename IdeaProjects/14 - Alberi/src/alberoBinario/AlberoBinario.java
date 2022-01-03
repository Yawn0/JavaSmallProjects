package alberoBinario;

import java.util.Stack;

public class AlberoBinario {
    private NodoAlberoBinario ptr;

    // esercizio 2
    public void Boscaiolo(String k) {
        class innerBoscaiolo {
            private NodoAlberoBinario ricercaNodo(NodoAlberoBinario n, String k) { // ritorna il padre del nodo in cui é stato trovato il valore
                if (n == null) return null;
                if (n.getLeftChild() != null && n.getLeftChild().getInfo().equals(k)) {
                    return n;
                }
                if (n.getRightChild() != null && n.getRightChild().getInfo().equals(k)) {
                    return n;
                }
                if (n.getLeftChild() != null)
                    return ricercaNodo(n.getLeftChild(), k);
                if (n.getRightChild() != null)
                    return ricercaNodo(n.getRightChild(), k);

                return null;
            }
        }

        if (ptr != null) {
            if (ptr.getInfo().equals(k)) {       // se il valore é nella radice
                ptr = null;
                return;
            }
        }

        while (true) {                            // elimino tutti i nodi nei quali trovo il valore
            NodoAlberoBinario nodoAlbero = new innerBoscaiolo().ricercaNodo(ptr, k);
            if (nodoAlbero != null) {
                if (nodoAlbero.getRightChild().getInfo().equals(k)) {
                    nodoAlbero.setRigthChild(null);
                } else if (nodoAlbero.getLeftChild().getInfo().equals(k)) {
                    nodoAlbero.setLeftChild(null);
                }
                System.out.println("Nodo eliminato");
            } else break;
        }
    }

    // esercizio 3
    public void ricerca(NodoAlberoBinario n, String k) {  // ricerca ricorsiva anticipata
        if (n == null) return;
        if (n.getInfo().equals(k))
            System.out.println(n.getInfo() + " !Valore rilevato in questo nodo!");
        if (n.getLeftChild() != null)
            ricerca(n.getLeftChild(), k);
        if (n.getRightChild() != null)
            ricerca(n.getRightChild(), k);
    }

//    //esercizio 4
//    public void visitaIterativa(){
//        if(ptr == null) return;         // albero vuoto?
//        NodoAlberoBinario nodo = ptr;   // nodo per scorrere l'albero
//
//        int count1;                     // per tornare al padre
//        int count2 = 0;                 // per tornare alla radice
//        int count3 = 0;                 // per non creare un ciclo infinito all'ultimo da visitare
//
//        while(true){
//            count1 = 0;
//
//            if(count2 == 1) {
//                if(nodo.getRightChild() != null){
//                    nodo = nodo.getRightChild();
//                    count2 ++;
//                }
//                else
//                    break;
//            }
//
//            if(count2 > 2)
//                break;
//
//            System.out.println(nodo.getInfo().toString());
//
//            if(nodo.getLeftChild() != null ){
//                nodo.setPadre(nodo);
//                nodo = nodo.getLeftChild();
//                count3 = 0;
//            }
//            else{
//                while (true){
//                    if(count1 == 1 && count3 < 1){
//                        nodo = nodo.getPadre();
//                        count3++;
//                    }
//                    if(count1 > 1){
//                        nodo.setPadre(ptr);
//                        nodo = nodo.getPadre();
//                        count2++;
//                        break;
//                    }
//                    if(nodo.getRightChild() != null){
//                        nodo.setPadre(nodo);                // questo set non credo serva se non ho capito male gli alberi binari
//                        nodo = nodo.getRightChild();
//                        break;
//                    }
//                    count1++;
//                }
//            }
//        }
//    }

    //esercizio 4
    public void visitaIterativa(){
        if(ptr == null) return;         // albero vuoto?
        NodoAlberoBinario nodo = ptr;   // nodo per scorrere l'albero

        int count1;                     // per tornare al padre
        int count2 = 0;                 // per tornare alla radice
        int count3 = 0;                 // per non creare un ciclo infinito all'ultimo da visitare

        while(count2 < 2){
            count1 = 0;

            if(count3 < 1)
                System.out.println(nodo.getInfo().toString());

            if(nodo.getLeftChild() != null ){            // passo al figlio sinistro
                nodo.setPadre(nodo);
                nodo = nodo.getLeftChild();
                count3 = 0;
            }
            else{
                while (count1 < 3){
                    if(count1 == 1 && count3 < 1){       // se il nodo non ha figli
                        nodo = nodo.getPadre();
                        count3++;
                    }
                    if(count1 > 1 && count3 > 0){        // ritorno alla radice
                        nodo.setPadre(ptr);
                        nodo = nodo.getPadre();
                        count2++;
                        if(nodo.getRightChild() == null) // radice con solo l'albero sinistro?
                            count2 = 2;
                    }
                    if(nodo.getRightChild() != null){    // passo al figlio destro
                        nodo.setPadre(nodo);
                        nodo = nodo.getRightChild();
                        count1++;
                    }
                    count1++;
                }
            }
        }
    }

    //esercizio 4
//    public void visitaIterativa() {
//        Stack<NodoAlberoBinario> pila = new Stack<>();
//        pila.push(ptr);
//        while (!pila.isEmpty()) {
//
//            NodoAlberoBinario current = pila.pop();
//            System.out.println(current.getInfo().toString());
//
//            if (current.getRightChild() != null)
//                pila.push(current.getRightChild());
//
//            if (current.getLeftChild() != null)
//                pila.push(current.getLeftChild());
//
//        }
//    }

}
