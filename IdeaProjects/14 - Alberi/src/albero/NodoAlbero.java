package albero;

public class NodoAlbero {

    private String info;
    private NodoAlbero pfc;
    private NodoAlbero pfs;

    public NodoAlbero(String info) {
        this.info = info;
        pfc = null;
        pfs = null;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
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
