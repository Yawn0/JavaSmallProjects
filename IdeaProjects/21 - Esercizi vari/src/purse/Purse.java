package purse;

import java.util.ArrayList;

public class Purse {
    public ArrayList<String> lstMonete;

    public Purse(){
        lstMonete = new ArrayList<>();
    }

    public void addCoin(String sCoinName){
        lstMonete.add(sCoinName);
    }

    @Override
    public String toString() {
        StringBuilder oSBuilder = new StringBuilder();

        String[] asMonete = new String[lstMonete.size()];
        lstMonete.toArray(asMonete);
        int iCounter = 0;

        oSBuilder.append("Purse[");

        for(String sCoinName : lstMonete){
            oSBuilder.append(sCoinName);
            if(iCounter < lstMonete.size() - 1){
                oSBuilder.append(", ");
            }
            iCounter++;
        }

        oSBuilder.append("]");

        return  oSBuilder.toString();
    }

    public static void main(String[] args) {

        Purse oPurse = new Purse();

        oPurse.addCoin("Quarter");
        oPurse.addCoin("Dime");
        oPurse.addCoin("Nickel");
        oPurse.addCoin("Dime");

        System.out.println(oPurse);
    }
}
