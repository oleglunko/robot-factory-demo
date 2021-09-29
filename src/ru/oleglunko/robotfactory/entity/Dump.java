package ru.oleglunko.robotfactory.entity;

import java.util.ArrayList;
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

    public RobotDetail add(RobotDetail detail) {
        dump.add(detail);
        return detail;
    }

    public List<RobotDetail> removeAll() {
        List<RobotDetail> removedDetails = new ArrayList<>(dump);
        dump.clear();
        return removedDetails;
    }

    public RobotDetail remove(int index) {
        return dump.remove(index);
    }

    public int size() {
        return dump.size();
    }

    public boolean isNotEmpty() {
        return !dump.isEmpty();
    }

    public Object getLock() {
        return lock;
    }
}
