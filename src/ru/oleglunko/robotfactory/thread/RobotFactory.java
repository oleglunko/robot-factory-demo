package ru.oleglunko.robotfactory.thread;

import ru.oleglunko.robotfactory.entity.Dump;
import ru.oleglunko.robotfactory.entity.RobotDetail;
import ru.oleglunko.robotfactory.util.NightConstant;
import ru.oleglunko.robotfactory.util.RandomUtil;

import java.util.List;

public class RobotFactory extends Thread {

    private static final int MAX_DETAIL_COUNT = 4;
    private final Night night;
    private final Dump dump;

    public RobotFactory(Night night, List<RobotDetail> initRobotDetails) {
        this.night = night;
        this.dump = new Dump(initRobotDetails);
    }

    //TODO refactoring - make 2 methods
    @Override
    public void run() {
        for (int i = 0; i < NightConstant.NIGHTS_AMOUNT; i++) {
            synchronized (dump.getLock()) {
                int countToTossNewRobotDetails = RandomUtil.getValue(MAX_DETAIL_COUNT);
                for (int j = 0; j < countToTossNewRobotDetails ; j++) {
                    var robotDetail = RobotDetail.VALUES.get(RandomUtil.getValue(RobotDetail.VALUES.size()));
                    dump.add(robotDetail);
                }
            }

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
