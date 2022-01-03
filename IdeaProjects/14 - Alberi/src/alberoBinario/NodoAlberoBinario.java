package alberoBinario;

public class NodoAlberoBinario{
    private String info;
    private NodoAlberoBinario psx;
    private NodoAlberoBinario pdx;
    private NodoAlberoBinario padre;

    public NodoAlberoBinario(String info) {
        setInfo(info);
        psx = null;
        pdx = null;
        padre = null;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setLeftChild(NodoAlberoBinario psx) {
        this.psx = psx;
    }

    public void setRigthChild(NodoAlberoBinario pdx) {
        this.pdx = pdx;
    }

    public NodoAlberoBinario getLeftChild() {
        return psx;
    }

    public NodoAlberoBinario getRightChild() {
        return pdx;
    }

    public NodoAlberoBinario getPadre() {
        return padre;
    }

    public void setPadre(NodoAlberoBinario padre) {
        this.padre = padre;
    }
}

