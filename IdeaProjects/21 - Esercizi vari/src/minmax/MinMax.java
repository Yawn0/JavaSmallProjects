package minmax;

import java.util.Arrays;
import java.util.Random;

public class MinMax {

    public static int[] aiArray = new int[10];

    public static final int LENGTH = 10;

    public static void main(String[] args) {
        Random oRandom = new Random();

        for(int iIndex = 0; iIndex < LENGTH; iIndex++){
            aiArray[iIndex] = oRandom.nextInt(0,100);
        }

        System.out.println(Arrays.toString(aiArray));

        int iMin = aiArray[0];
        int iMax = aiArray[0];

        for(int iIndex = 1; iIndex < LENGTH; iIndex++ ){
            if(iMin > aiArray[iIndex]){
                iMin = aiArray[iIndex];
            }
            if(iMax < aiArray[iIndex]){
                iMax = aiArray[iIndex];
            }
        }

        System.out.println("max : " + iMax + " min : " + iMin);
    }
}
