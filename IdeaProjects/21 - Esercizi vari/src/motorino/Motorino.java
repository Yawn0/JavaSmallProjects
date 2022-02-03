package motorino;

public class Motorino {

    public String sColore;
    public double dVelocita;
    public String sTipo;
    public boolean bAntifurto = false;

    public Motorino(String sColore, String sTipo, double dVelocita){
        this.sColore = sColore;
        this.sTipo = sTipo;
        this.dVelocita = dVelocita;
    }

    public double getVelocita(){
        return dVelocita;
    }

    public void accelera(double dVelocitaAggiuntiva){
        if(!bAntifurto){
            dVelocita += dVelocitaAggiuntiva;
        }
    }

    public void inserisciAntifurto(){
        bAntifurto = true;
    }
}
