package motorino;

public class MotorinoImmatricolato extends Motorino{

    double dMaxVelocita;
    String sTarga;


    public MotorinoImmatricolato(String sColore, String sTipo, double dVelocita, double dMaxVelocita, String sTarga) {
        super(sColore, sTipo, dVelocita);
        this.dMaxVelocita = dMaxVelocita;
        this.sTarga = sTarga;
    }

    public void maxVel(){
        System.out.println(dMaxVelocita);
    }

    @Override
    public void accelera(double dVelocitaAggiuntiva) {

        double dSomma = dVelocitaAggiuntiva + getVelocita();

        dVelocita = Math.min(dSomma, dMaxVelocita);
    }
}
