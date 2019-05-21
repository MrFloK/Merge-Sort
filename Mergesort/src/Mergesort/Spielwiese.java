package Mergesort;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Spielwiese {

    public static void main(String[] args) {

        //Erstellen der Stopuhren für die SOrteirverfahren -klassisch und 2 Threads
        Stopwatch t1 = new Stopwatch();
        Stopwatch t2 = new Stopwatch();

        //Scannereingabe für benutzerdefinierte Werte
        System.out.println("Wie groß soll das Array werden ?");
        Scanner sc = new Scanner(System.in);
        int inputlength = sc.nextInt();
        System.out.println("Wie groß sollen die Zahlen werden ?");
        int inputrange = sc.nextInt();
        System.out.println("Soll das Array angezeigt werden ?");
        boolean input3 = sc.nextBoolean();
        sc.close();

        //Stopuhr starten
        t1.starte();



        //Unsortierte Liste erstellen => befüllen mit zufälligen Zahlen
        int [] unsortedList = new int[inputlength];



        unsortedList = Arrays.stream(unsortedList)
                .map(randomnumber -> (int) (Math.random()*inputrange))
                .toArray();

        //Arrays.stream(unsortedList).forEach(number -> System.out.println(number));

        int [] sortedList = new int[unsortedList.length];
            //Einfacher Weg
        //Liste sortieren mit einem Thread
        sortedList = NormalWay.split(unsortedList);

        //Ausgabe
        if (input3) {
            System.out.println("\nEndprodukt eines Threads: ");
            Arrays.stream(sortedList)
                    .forEach(number -> System.out.println(number));
        }

        //Stoppe Stopuhr
        t1.stoppe();

        //Start der zwei Thread Programmierung
        t2.starte();

        //Manuelles spalten der Listen
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

        int counter = 0;

        //left[i] = Arrays.stream(unsortedList)
                //.forEach(number ->  < number ? number : );

        //right = Arrays.stream(unsortedList)
        //        .


        //Thread initialisieren und starten
        ThreadClass firstThread = new ThreadClass(left);
        ThreadClass secondThread = new ThreadClass(right);

        Thread leftThread = new Thread(firstThread);
        Thread rightThread = new Thread(secondThread);


        leftThread.start();
        rightThread.start();

        //sichergehen, dass beide Threads erst durchgelaufen sind, bevor Main weitergeht
        try {
            leftThread.join();
            rightThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //2 seperate Listen wieder zusammensetzen
        int[] sortedleft = new int[(int) Math.floor(halfListLength)];
        int[] sortedright = new int[(int) Math.ceil(halfListLength)];


        sortedleft = firstThread.call();
        sortedright = secondThread.call();

        int[] sortedListThread = new int[unsortedList.length];
        sortedListThread = NormalWay.merge(sortedleft, sortedright);

        if (input3) {
            System.out.println("\nEndprodukt Zwei Threads: ");
            Arrays.stream(sortedListThread).forEach(numbers -> System.out.println(numbers));
        }

        //Stoppe Stopuhr
        t2.stoppe();

        //Ausgabe der Stopuhren
        System.out.println("\nZeit mit einem Thread: " + t1.lies() + " ms." );
        System.out.println("\nZeit mit zwei Threads: " + t2.lies() + " ms.");

    }
}
