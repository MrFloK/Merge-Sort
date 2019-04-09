package Mergesort;

public class NormalWay {

    public static int[] split (int[] unsortedList) {

        if (unsortedList.length == 1 )  {
            return unsortedList;
        }

        int listLength = unsortedList.length;
        double listLengthDouble = listLength;
        double halfListLength = listLengthDouble / 2;

        int[] left = new int[(int) Math.floor(halfListLength)];
        int[] right = new int[(int) Math.ceil(halfListLength)];
        for (int i = 0; i < unsortedList.length; i++)   {
            if(i < (int) Math.floor(halfListLength)) {
                left[i] = unsortedList[i];
                } else {
                    right[i - (int) Math.floor(halfListLength)] = unsortedList [i];
            }
        }
            left = split(left);
            right = split(right);
            return merge(left, right);
    }

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
