package Mergesort;

public class ThreadClass implements Runnable {

    private int[] unsortedList;

    public ThreadClass(int[] unsortedList) {
        this.unsortedList = unsortedList;
    }

    @Override
    public void run() {
        unsortedList = NormalWay.split(unsortedList);
    }


    public int[] call(){
            return unsortedList;
    }
}
