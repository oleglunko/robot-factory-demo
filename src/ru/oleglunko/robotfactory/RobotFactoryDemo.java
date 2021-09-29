package ru.oleglunko.robotfactory;

import ru.oleglunko.robotfactory.entity.RobotDetail;
import ru.oleglunko.robotfactory.entity.Scientist;
import ru.oleglunko.robotfactory.thread.Assistant;
import ru.oleglunko.robotfactory.thread.Night;
import ru.oleglunko.robotfactory.thread.RobotFactory;
import ru.oleglunko.robotfactory.util.RandomUtil;

import java.util.LinkedList;
import java.util.List;

public class RobotFactoryDemo {

    public static void main(String[] args) throws InterruptedException {
        Night night = new Night();
        var robotFactory = new RobotFactory(night, getInitRobortDetails());
        var firstAssistant = new Assistant(new Scientist("Max"), night, robotFactory.getDump());
        var secondAssistant = new Assistant(new Scientist("Andrew"), night, robotFactory.getDump());

        night.start();
        robotFactory.start();
        firstAssistant.start();
        secondAssistant.start();


        night.join();
        robotFactory.join();
        firstAssistant.join();
        secondAssistant.join();

        var firstScientist = firstAssistant.getScientist();
        var secondScientist = secondAssistant.getScientist();
        System.out.println("==========================================");
        System.out.println("RESULTS:");
        System.out.printf("%s built %d robots!\n", firstScientist.getName(), firstScientist.buildRobot().size());
        System.out.printf("%s built %d robots!\n", secondScientist.getName(), secondScientist.buildRobot().size());

        if (firstScientist.buildRobot().size() == secondScientist.buildRobot().size()) {
            System.out.printf("There are no losers!");
        } else if (firstScientist.buildRobot().size() > secondScientist.buildRobot().size()) {
            System.out.printf("Congrats! %s won!", firstScientist.getName());
        } else {
            System.out.printf("Congrats! %s won!", secondScientist.getName());
        }

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
