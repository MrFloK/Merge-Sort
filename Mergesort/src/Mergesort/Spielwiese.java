package Mergesort;
import java.util.Scanner;

public class Spielwiese {

    public static void main(String[] args) {

        Stopwatch t1 = new Stopwatch();
        Stopwatch t2 = new Stopwatch();

        System.out.println("Wie groß soll das Array werden ?");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        System.out.println("Wie groß sollen die Zahlen werden ?");
        int input2 = sc.nextInt();
        System.out.println("Soll das Array angezeigt werden ?");
        boolean input3 = sc.nextBoolean();
        sc.close();

        t1.starte();

        //Unsortierte Liste erstellen => befüllen mit zufälligen Zahlen
        int [] unsortedList = new int[input];
        for (int i = 0; i < unsortedList.length;i++ ) {
            int random = (int) (Math.random()*input2); //Java Zufallszahlen auf int casten
            unsortedList[i] = random;
        }

        int [] sortedList = new int[unsortedList.length];
            //Einfacher Weg
        //Liste sortieren mit einem Thread
        sortedList = NormalWay.split(unsortedList);

        //Ausgabe
        if (input3) {
            System.out.println("\nEndprodukt eines Threads: ");
            for (int part : sortedList) {
                System.out.println(part);
            }
        }

        //Stoppe Stopuhr
        t1.stoppe();

        //Start der zwei Thread Programmierung
        t2.starte();

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

        ThreadClass firstThread = new ThreadClass(left);
        ThreadClass secondThread = new ThreadClass(right);

        Thread leftThread = new Thread(firstThread);
        Thread rightThread = new Thread(secondThread);


        leftThread.start();
        rightThread.start();

        try {
            leftThread.join();
            rightThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        int[] sortedleft = new int[(int) Math.floor(halfListLength)];
        int[] sortedright = new int[(int) Math.ceil(halfListLength)];


        sortedleft = firstThread.call();
        sortedright = secondThread.call();

        int[] sortedListThread = new int[unsortedList.length];
        sortedListThread = NormalWay.merge(sortedleft, sortedright);

        if (input3) {
            System.out.println("\nEndprodukt Zwei Threads: ");
            for (int part : sortedListThread) {
                System.out.println(part);
            }
        }

        //Stoppe Stopuhr
        t2.stoppe();

        System.out.println("\nZeit mit einem Thread: " + t1.lies() + " ms." );
        System.out.println("\nZeit mit zwei Threads: " + t2.lies() + " ms.");

    }
}
