package frc.robot.Auto;

import frc.robot.Auto.AutoEndedException;

/*
 * This mission has the robot sit and wait till the
 * teleop phase of the match
 * Can be used for both red and blue alliances
 */

public class DoNothingMission extends MissionBase {
    @Override
    protected void routine() throws AutoEndedException {
        System.out.println("Do nothing auto mission");
    }
}