package ru.oleglunko.robotfactory.entity;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Scientist {

    private final Map<RobotDetail, Integer> robotDetails = new EnumMap<>(RobotDetail.class);
    private final String name;

    public Scientist(String name) {
        this.name = name;
    }

    public void takeRobotDetails(List<RobotDetail> list) {
        list.forEach(detail -> robotDetails.merge(detail, 1, Integer::sum));
    }

    public List buildRobot() {
        if (isEnoughPartsToBuild()) {
            int robotAmount = robotDetails.values().stream().min(Integer::compareTo).orElse(0);
            return IntStream.range(0, robotAmount)
                    .mapToObj(value -> new Robot())
                    .collect(Collectors.toList());
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    private boolean isEnoughPartsToBuild() {
        return robotDetails.keySet().containsAll(RobotDetail.VALUES);
    }

    public String getName() {
        return name;
    }
}
