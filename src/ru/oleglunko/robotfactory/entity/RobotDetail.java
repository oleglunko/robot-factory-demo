package ru.oleglunko.robotfactory.entity;

import java.util.Arrays;
import java.util.List;

public enum RobotDetail {
    HEAD,
    BODY,
    LEFT_HAND,
    RIGHT_HAND,
    LEFT_LEG,
    RIGHT_LEG,
    CPU,
    RAM,
    HDD;

    public static final List<RobotDetail> CASHED_VALUES = Arrays.asList(values());
}
