package misurazioni;

import java.io.Serializable;

//public class Misura implements Serializable {
//
//    private static final long lSerialVersionUID = 1L;
//
//    private String sGrandezza;
//    private double dValore;
//    private long lTimeStamp;
//
//    public  Misura(){
//        sGrandezza = "Temperatura";
//        Random oRandom = new Random();
//        dValore = oRandom.nextDouble()+ oRandom.nextInt(41);
//        lTimeStamp = System.currentTimeMillis();
//    }
//
//    public Misura(String sTipologia, double dValore, long lTimeStamp){
//        this.sGrandezza = sTipologia;
//        this.dValore = dValore;
//        this.lTimeStamp = lTimeStamp;
//    }
//
//    public Misura(String sGrandezza, double dValore){
//        this.sGrandezza = sGrandezza;
//        this.dValore = dValore;
//    }
//
//    @Override
//    public boolean equals(Object oObject) {
//        if(!(oObject instanceof Misura))		// controlla che sia dello stesso tipo
//            return false;
//        if(oObject == this)					// controlla che gli oggetti siano della classe Object
//            return true;
//        Misura oMisura = (Misura)oObject;
//        return (oMisura.sGrandezza.equals(sGrandezza)
//                && oMisura.dValore == dValore
//                && oMisura.lTimeStamp == lTimeStamp);
//    }
//
//    @Override
//    public String toString(){
//        return "Tipo : " + this.sGrandezza + " valore : " + this.dValore + " istante : " + this.lTimeStamp;
//    }
//
//    public double getValore() {
//        return dValore;
//    }
//
//    public String getGrandezza() {
//        return sGrandezza;
//    }
//
//    public long getTimeStamp() {
//        return lTimeStamp;
//    }
//}

public class Misura implements Serializable {

    private static final long serialVersionUID = 1L;
    private String sGrandezza;
    private double dValore;
    private long lTimestamp;

//    public Misura() {
//        sGrandezza = "Temperatura";
//        Random oRandom = new Random();
//        dValore = oRandom.nextDouble()+ oRandom.nextInt(41);
//        lTimestamp = System.currentTimeMillis();
//    }

    public Misura(String sGrandezza, double dValore, long lTimestamp) {
        this.sGrandezza = sGrandezza;
        this.dValore = dValore;
        this.lTimestamp = lTimestamp;
    }

    public Misura(String sGrandezza, double dValore){
        this.sGrandezza = sGrandezza;
        this.dValore = dValore;
    }

    public double getValore() {
        return dValore;
    }

    public String getGrandezza() {
        return sGrandezza;
    }

    public boolean equals(Object oObject) {
        if(!(oObject instanceof Misura))
            return false;

        if(this == oObject)
            return true;

        Misura oMisura = (Misura) oObject;

        return sGrandezza == oMisura.sGrandezza && lTimestamp == oMisura.lTimestamp && dValore == oMisura.dValore;
    }

    public String toString() {
        return sGrandezza +" "+ dValore +" "+ lTimestamp;
    }
}
