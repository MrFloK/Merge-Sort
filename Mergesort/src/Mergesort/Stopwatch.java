package Mergesort;

public class Stopwatch {

    long millis;

    void starte() {
        millis = System.currentTimeMillis();
    }

    void stoppe() {
        millis = System.currentTimeMillis() - millis;
    }

    long lies() {
        return millis;
    }

}

