package ru.oleglunko.robotfactory.entity;

import java.util.LinkedList;
import java.util.List;

public class Dump {

    private final Object lock = new Object();
    private final List<RobotDetail> dump = new LinkedList<>();

    public Dump() {
    }

    public Dump(List<RobotDetail> initRobotDetails) {
        dump.addAll(initRobotDetails);
    }

    public void add(RobotDetail detail) {
        dump.add(detail);
    }

    public void removeAll(List<RobotDetail> robotDetails) {
        dump.removeAll(robotDetails);
    }

    public Object getLock() {
        return lock;
    }
}
