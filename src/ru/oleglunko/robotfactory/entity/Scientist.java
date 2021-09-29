package ru.oleglunko.robotfactory.entity;


import java.util.List;

public class Scientist {

    private String name;

    public Scientist(String name) {
        this.name = name;
    }

    public void takeRobotDetails(List<RobotDetail> takenDetails) {
    }

    public String getName() {
        return name;
    }
}
