package ru.oleglunko.robotfactory.thread;

import ru.oleglunko.robotfactory.util.RandomUtil;

public class Night extends Thread {

    private static final int NIGHTS_AMOUNT = 10;
    private static final long NIGHT_DELAY = 100;
    private final Object lock = new Object();

    @Override
    public void run() {
        for (int i = 0; i < RandomUtil.getValueWithoutZero(NIGHTS_AMOUNT); i++) {
            synchronized (lock) {
                try {
                    System.out.println(String.format("Night №%d started", i+1));
                    lock.notifyAll();
                    lock.wait(NIGHT_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Object getLock() {
        return lock;
    }
}
