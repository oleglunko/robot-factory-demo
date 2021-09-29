package ru.oleglunko.robotfactory;

import ru.oleglunko.robotfactory.entity.RobotDetail;
import ru.oleglunko.robotfactory.entity.Scientist;
import ru.oleglunko.robotfactory.thread.Assistant;
import ru.oleglunko.robotfactory.thread.Night;
import ru.oleglunko.robotfactory.thread.RobotFactory;
import ru.oleglunko.robotfactory.util.RandomUtil;
import ru.oleglunko.robotfactory.util.ThreadUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RobotFactoryDemo {

    public static void main(String[] args) {
        Night night = new Night();
        var robotFactory = new RobotFactory(night, getInitRobotDetails());
        var firstScientist = new Scientist("Max");
        var secondScientist = new Scientist("Andrew");
        var firstAssistant = new Assistant(firstScientist, night, robotFactory.getDump());
        var secondAssistant = new Assistant(secondScientist, night, robotFactory.getDump());

        ThreadUtil.startThreads(night, robotFactory, firstAssistant, secondAssistant);
        ThreadUtil.joinThreads(night, robotFactory, firstAssistant, secondAssistant);

        showResults(firstScientist, secondScientist);

    }

    private static List<RobotDetail> getInitRobotDetails() {
        return IntStream.range(0, 20)
                .map(it -> RandomUtil.getValue(RobotDetail.VALUES.size()))
                .mapToObj(RobotDetail.VALUES::get)
                .collect(Collectors.toList());
    }

    private static void showResults(Scientist firstScientist, Scientist secondScientist) {
        int amountOfRobotsForFirstScientist = firstScientist.buildRobot().size();
        int amountOfRobotsForSecondScientist = secondScientist.buildRobot().size();

        System.out.println("==========================================");
        System.out.println("RESULTS:");
        System.out.printf("%s built %d robots!\n", firstScientist.getName(), amountOfRobotsForFirstScientist);
        System.out.printf("%s built %d robots!\n", secondScientist.getName(), amountOfRobotsForSecondScientist);

        determineWinner(firstScientist, secondScientist, amountOfRobotsForFirstScientist, amountOfRobotsForSecondScientist);
    }

    private static void determineWinner(Scientist firstScientist, Scientist secondScientist,
                                        int amountOfRobotsForFirstScientist, int amountOfRobotsForSecondScientist) {
        if (amountOfRobotsForFirstScientist == amountOfRobotsForSecondScientist) {
            System.out.println("There are no losers!");
        } else if (amountOfRobotsForFirstScientist > amountOfRobotsForSecondScientist) {
            System.out.printf("Congrats! %s won!", firstScientist.getName());
        } else {
            System.out.printf("Congrats! %s won!", secondScientist.getName());
        }
    }
}
