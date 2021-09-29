package ru.oleglunko.robotfactory.entity;

import java.util.ArrayList;
import java.util.List;

public class Dump {

    private final Object lock = new Object();
    private final List<RobotDetail> dump = new ArrayList<>();

    public Dump() {
    }

    public Dump(List<RobotDetail> initialRobotDetails) {
        dump.addAll(initialRobotDetails);
    }

    public RobotDetail add(RobotDetail detail) {
        dump.add(detail);
        return detail;
    }

    public RobotDetail remove(int index) {
        return dump.remove(index);
    }

    public List<RobotDetail> removeAll() {
        List<RobotDetail> removedDetails = new ArrayList<>(dump);
        dump.clear();
        return removedDetails;
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
