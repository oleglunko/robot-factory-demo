package ru.oleglunko.robotfactory.thread;

import ru.oleglunko.robotfactory.entity.Dump;
import ru.oleglunko.robotfactory.entity.RobotDetail;
import ru.oleglunko.robotfactory.util.NightConstant;
import ru.oleglunko.robotfactory.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class RobotFactory extends Thread {

    private static final int MAX_DETAILS_COUNT = 4;
    private final Night night;
    private final Dump dump;

    public RobotFactory(Night night, List<RobotDetail> initialRobotDetails) {
        this.night = night;
        this.dump = new Dump(initialRobotDetails);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < NightConstant.NIGHTS_AMOUNT; i++) {
                tossNewRobotDetailsToDump();
                waitNextNight();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tossNewRobotDetailsToDump() {
        int countForTossNewDetails = RandomUtil.getValueWithoutZero(MAX_DETAILS_COUNT);
        List<RobotDetail> tossedDetails = new ArrayList<>(MAX_DETAILS_COUNT);
        synchronized (dump.getLock()) {
            for (int j = 0; j < countForTossNewDetails; j++) {
                RobotDetail robotDetail = RandomUtil.getRandomRobotDetail();
                tossedDetails.add(dump.add(robotDetail));
            }
        }
        System.out.printf("Factory tossed out %d new robot details: %s\n", countForTossNewDetails, tossedDetails);
    }

    private void waitNextNight() throws InterruptedException {
        synchronized (night.getLock()) {
            night.getLock().wait();
        }
    }

    public Dump getDump() {
        return dump;
    }
}
