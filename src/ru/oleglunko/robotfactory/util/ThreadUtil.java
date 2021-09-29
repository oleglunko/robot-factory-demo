package ru.oleglunko.robotfactory.util;

import ru.oleglunko.robotfactory.thread.Assistant;
import ru.oleglunko.robotfactory.thread.Night;
import ru.oleglunko.robotfactory.thread.RobotFactory;

public final class ThreadUtil {

    private ThreadUtil() {
    }

    public static void startThreads(Thread ... threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public static void joinThreads(Thread ... threads) {
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
