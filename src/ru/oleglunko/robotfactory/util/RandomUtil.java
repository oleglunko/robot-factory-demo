package ru.oleglunko.robotfactory.util;

import ru.oleglunko.robotfactory.entity.RobotDetail;

import java.util.Random;

public final class RandomUtil {

    private static final Random RANDOM = new Random();

    private RandomUtil() {
    }

    public static int getValue(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static int getValueWithoutZero(int bound) {
        return RANDOM.nextInt(bound) + 1;
    }

    public static RobotDetail getRandomRobotDetail() {
        return RobotDetail.VALUES.get(getValue(RobotDetail.VALUES.size()));
    }
}
