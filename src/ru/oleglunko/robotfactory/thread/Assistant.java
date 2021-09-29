package ru.oleglunko.robotfactory.thread;

import ru.oleglunko.robotfactory.entity.Dump;
import ru.oleglunko.robotfactory.entity.RobotDetail;
import ru.oleglunko.robotfactory.entity.Scientist;
import ru.oleglunko.robotfactory.util.NightConstant;
import ru.oleglunko.robotfactory.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class Assistant extends Thread {

    private static final int MAX_DETAILS_COUNT = 4;
    private final Scientist scientist;
    private final Night night;
    private final Dump dump;

    public Assistant(Scientist scientist, Night night, Dump dump) {
        this.scientist = scientist;
        this.night = night;
        this.dump = dump;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < NightConstant.NIGHTS_AMOUNT; i++) {
                List<RobotDetail> takenDetails = getRobotDetails();
                scientist.takeRobotDetails(takenDetails);
                waitNextNight();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<RobotDetail> getRobotDetails() {
        int countDetailsToTake = RandomUtil.getValueWithoutZero(MAX_DETAILS_COUNT);
        List<RobotDetail> takenDetails = new ArrayList<>(MAX_DETAILS_COUNT);
        synchronized (dump.getLock()) {
            if (dump.size() <= countDetailsToTake) {
                takenDetails.addAll(dump.removeAll());
            } else if (dump.isNotEmpty()) {
                for (int j = 0; j < countDetailsToTake; j++) {
                    RobotDetail removedDetail = dump.remove(RandomUtil.getValue(dump.size()));
                    takenDetails.add(removedDetail);
                }
            }
            System.out.printf("%s's assistant took  %d next details: %s\n", scientist.getName(), takenDetails.size(), takenDetails);
        }

        return takenDetails;
    }

    private void waitNextNight() throws InterruptedException {
        synchronized (night.getLock()) {
            night.getLock().wait();
        }
    }
}
