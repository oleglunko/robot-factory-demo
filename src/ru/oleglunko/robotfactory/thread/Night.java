package ru.oleglunko.robotfactory.thread;

import ru.oleglunko.robotfactory.util.NightConstant;
import ru.oleglunko.robotfactory.util.RandomUtil;

public class Night extends Thread {

    private final Object lock = new Object();

    @Override
    public void run() {
        for (int i = 0; i < RandomUtil.getValueWithoutZero(NightConstant.NIGHTS_AMOUNT); i++) {
            synchronized (lock) {
                try {
                    System.out.printf("Night №%d started \n", (i + 1));
                    lock.notifyAll();
                    lock.wait(NightConstant.NIGHT_DELAY);
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
