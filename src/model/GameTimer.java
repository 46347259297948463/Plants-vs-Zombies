package model;

public class GameTimer {
    private long startTime;
    public void start() {
        startTime = System.nanoTime();
    }

    public double getTimeS() {
        long now = System.nanoTime();
        return (now - startTime) / 1_000_000_000.0;
    }
//    public long getElapsedTimeInMillis() {
//        long now = System.nanoTime();
//        return (now - startTime) / 1_000_000;
//    }
}
