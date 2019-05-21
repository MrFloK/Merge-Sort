package Mergesort;

public class Stopwatch {

    long millis;

    //Start der Stopuhr
    void starte() {
        millis = System.currentTimeMillis();
    }

    //Stop de rStopuhr
    void stoppe() {
        millis = System.currentTimeMillis() - millis;
    }

    //Ausgabe der gemessenen Zeit
    long lies() {
        return millis;
    }

}

