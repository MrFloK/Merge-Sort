package Mergesort;

public class ThreadClass implements Runnable {

    private int[] unsortedList;

    //ERstellung der Threadclasse und Übergabe der unsortierten Liste
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
