package ru.oleglunko.robotfactory.entity;


import java.util.*;

public class Scientist {

    private final Map<RobotDetail, Integer> robotDetails = new HashMap<>();
    private final String name;

    public Scientist(String name) {
        this.name = name;
    }

    public void takeRobotDetails(List<RobotDetail> list) {
        list.forEach(detail -> robotDetails.merge(detail, 1, Integer::sum));
    }

    public List<Robot> buildRobot() {
        List<Robot> robots = new LinkedList<>();
        if (isEnoughPartsToBuild()) {
            int robotAmount = robotDetails.values().stream().min(Integer::compareTo).orElse(0);
            for (int i = 0; i < robotAmount; i++) {
                robots.add(new Robot());
            }
        }

        return robots;
    }

    private boolean isEnoughPartsToBuild() {
        return robotDetails.keySet().containsAll(RobotDetail.VALUES);
    }

    public String getName() {
        return name;
    }
}
