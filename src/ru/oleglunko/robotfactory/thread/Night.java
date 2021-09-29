package ru.oleglunko.robotfactory.thread;

import ru.oleglunko.robotfactory.util.NightConstant;

public class Night extends Thread {

    private final Object lock = new Object();

    @Override
    public void run() {
        for (int i = 0; i < NightConstant.NIGHTS_AMOUNT; i++) {
            synchronized (lock) {
                try {
                    System.out.printf("\n=================== \nNight №%d started \n", (i + 1));
                    lock.notifyAll();
                    lock.wait(NightConstant.NIGHT_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        synchronized (lock){
            lock.notifyAll();
        }
    }

    public Object getLock() {
        return lock;
    }
}
