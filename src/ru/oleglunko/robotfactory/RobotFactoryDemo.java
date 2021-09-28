package ru.oleglunko.robotfactory;

import ru.oleglunko.robotfactory.entity.RobotDetail;
import ru.oleglunko.robotfactory.thread.Night;
import ru.oleglunko.robotfactory.thread.RobotFactory;
import ru.oleglunko.robotfactory.util.RandomUtil;

import java.util.LinkedList;
import java.util.List;

public class RobotFactoryDemo {

    public static void main(String[] args) throws InterruptedException {
        Night night = new Night();
        var robotFactory = new RobotFactory(night, getInitRobortDetails());

        night.start();
        robotFactory.start();

        night.join();
        robotFactory.join();
    }

    //TODO refactoring - will make it with Stream
    private static List<RobotDetail> getInitRobortDetails() {
        List<RobotDetail> initRobotDetails = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            initRobotDetails.add(RobotDetail.VALUES.get(RandomUtil.getValue(RobotDetail.values().length)));
        }
        return initRobotDetails;
    }
}
