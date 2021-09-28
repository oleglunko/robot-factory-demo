package ru.oleglunko.robotfactory.thread;

import ru.oleglunko.robotfactory.entity.Dump;
import ru.oleglunko.robotfactory.entity.RobotDetail;

import java.util.List;

public class RobotFactory extends Thread {

    private final Night night;
    private final Dump dump;

    public RobotFactory(Night night, List<RobotDetail> initRobotDetails) {
        this.night = night;
        this.dump = new Dump(initRobotDetails);
    }

    @Override
    public void run() {
        synchronized (dump.getLock()) {
            
        }
    }
}
