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

    //TODO refactoring - make 2 methods;
    @Override
    public void run() {
        for (int i = 0; i < NightConstant.NIGHTS_AMOUNT; i++) {
            List<RobotDetail> takenDetails = new ArrayList<>();
            int countOfDetailsToTake = RandomUtil.getValueWithoutZero(MAX_DETAILS_COUNT);
            synchronized (dump.getLock()) {
                for (int j = 0; j < countOfDetailsToTake; j++) {
                    var robotDetail = RobotDetail.VALUES.get(RandomUtil.getValue(RobotDetail.values().length));
                    takenDetails.add(robotDetail);
                }
                dump.removeAll(takenDetails);
            }

            scientist.takeRobotDetails(takenDetails);

            synchronized (night.getLock()) {
                try {
                    night.getLock().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
