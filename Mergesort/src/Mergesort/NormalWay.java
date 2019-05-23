package Mergesort;

import java.util.Arrays;

public class NormalWay {

    public static int[] split (int[] unsortedList) {

        //Abbruchbedingung -> Wenn Liste nur noch 1 Element enthält
        if (unsortedList.length == 1 )  {
            return unsortedList;
        }

        //Liste aufteilen
        int[] left;
        int[] right;
        left = Arrays.copyOfRange(unsortedList, 0, unsortedList.length/2);
        right = Arrays.copyOfRange(unsortedList, left.length,unsortedList.length );

            //mit geteilter Liste Rekursion ausführen
            left = split(left);
            right = split(right);

            //Zusammenführen der Listen
            return merge(left, right);
    }


    //Zusammenführen der Listen
    public static int[] merge(int[] left, int[] right) {
        int[] merge = new int[left.length + right.length];
        int leftcounter = 0;
        int rightcounter = 0;
        for (int i = 0; leftcounter < left.length || rightcounter < right.length; i++)  {
            if(leftcounter < left.length && rightcounter < right.length)    {
                if(left[leftcounter] <= right[rightcounter])    {
                    merge[i] = left[leftcounter];
                    leftcounter ++;
                }   else    {
                    merge[i] = right[rightcounter];
                    rightcounter ++;
                }
            }   else if (leftcounter < left.length) {
                merge[i] = left[leftcounter];
                leftcounter++;
            }   else if (rightcounter < right.length)   {
                merge[i] = right[rightcounter];
                rightcounter++;
            }
        }
        return merge;
    }
}
