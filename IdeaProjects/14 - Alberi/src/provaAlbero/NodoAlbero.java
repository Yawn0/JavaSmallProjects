package provaAlbero;

public class NodoAlbero {

    private Character info;
    private NodoAlbero pfc;
    private NodoAlbero pfs;

    public NodoAlbero(char info) {
        this.info = new Character(info);
        pfc = null;
        pfs = null;
    }

    public void setInfo(char info) {
        this.info = new Character(info);
    }

    public char getInfo() {
        return new Character(info);
    }

    public void setFirstChild(NodoAlbero pfc) {
        this.pfc = pfc;
    }

    public NodoAlbero getFirstChild() {
        return pfc;
    }

    public void setFirstSibling(NodoAlbero pfs) {
        this.pfs = pfs;
    }

    public NodoAlbero getFirstSibling() {
        return pfs;
    }
}