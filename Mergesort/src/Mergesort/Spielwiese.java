package Mergesort;
import java.util.Arrays;
import java.util.Scanner;

public class Spielwiese {

    public static void main(String[] args) {

        //Erstellen der Stopuhren für die Sorteirverfahren -klassisch und 2 Threads
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

        //Unsortierte Liste erstellen => befüllen mit zufälligen Zahlen
        int [] unsortedList = new int[inputlength];
        unsortedList = Arrays.stream(unsortedList)
                .map(randomnumber -> (int) (Math.random()*inputrange))
                .toArray();

        //Arrays erstellen für das Sortierte Array
        int [] sortedList;

        //Stopuhr starten
        t1.starte();

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

            //Start der zwei-Thread-Sortierung
        t2.starte();

        //Manuelles spalten der Listen
        int[] left;
        int[] right;
        //Beispiel um Lambda Ausdrücke zu generieren
        ArraysCustomizing teilen = (a,b,c) -> Arrays.copyOfRange(a,b,c);
        left = teilen.fkt(unsortedList, 0, unsortedList.length/2);
        right = teilen.fkt(unsortedList, left.length,unsortedList.length );


        //Thread initialisieren und starten
        ThreadClass firstThread = new ThreadClass(left);
        ThreadClass secondThread = new ThreadClass(right);

        //Threads jeweils eine Hälfte der Liste übergeben
        Thread leftThread = new Thread(firstThread);
        Thread rightThread = new Thread(secondThread);

        //Threads starten
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
        int[] sortedleft;
        int[] sortedright;

        sortedleft = firstThread.call();
        sortedright = secondThread.call();

        //Zusammensetzten durch merge()
        int[] sortedListThread;
        sortedListThread = NormalWay.merge(sortedleft, sortedright);

        //Ausgabe des zweiten Ergebinsses
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

    interface ArraysCustomizing{
        int [] fkt(int[] origin, int from, int to);
    }

}
