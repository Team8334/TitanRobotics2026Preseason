package frc.robot.Auto;

//import frc.robot.Auto.Missions.*;
//import frc.robot.Auto.Missions.BlueMissions.BlueScoreL4;
//import frc.robot.Auto.Missions.RedMissions.RedScoreL4;

import frc.robot.Auto.SwervePath;
import com.pathplanner.lib.auto.AutoBuilder;

import java.util.Optional;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.Data.Debug;

/*
 * This lets the person choose which mission is executed
 */

public class AutoMissionChooser {
    enum Command {
        //these are the options you will see in smart dashboard.
        exampleMission,
        // general missions that use alliance to determine the actual missions
        ScoringL4Mission,
        // actual missions
        MoveAcrossLineMission,
        doNothing,
        RedScoreL4,
        BlueScoreL4,
    }

    private Command cachedDesiredMission = Command.doNothing;

    private final SendableChooser<Command> missionChooser;


    private Optional<MissionBase> autoMission = Optional.empty();

    public static double delay;

    String alliance;

    public AutoMissionChooser() {
        //missionChooser = new SendableChooser<>();
        missionChooser = AutoBuilder.buildAutoChooser();

        // add more here as needed, is what is seen when choosing a mission
        missionChooser.addOption("Do Nothing", Command.doNothing);
        missionChooser.addOption("Leave Community", Command.MoveAcrossLineMission);
        missionChooser.addOption("Scoring L4", Command.ScoringL4Mission);

        SmartDashboard.putNumber("Auto Delay (seconds)", 0);

        SmartDashboard.putData("Auto Mission", missionChooser);
        SmartDashboard.putString("Current Action System", "None");

        try {
            alliance = DriverStation.getAlliance().orElseThrow(() -> new Exception("No alliance")).toString();
        }
        catch (Exception e) {
            // Handle the exception, for example:
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    public void updateMissionCreator() {
        try {
            alliance = DriverStation.getAlliance().orElseThrow(() -> new Exception("No alliance")).toString();
        }
        catch (Exception e) {
            
        }
        delay = SmartDashboard.getNumber("Auto Delay", 0);
        
        Command desiredMission = missionChooser.getSelected();

        if (desiredMission == null) {
            desiredMission = Command.doNothing;
        }

        if (cachedDesiredMission != desiredMission) {
            autoMission = getAutoMissionForParams(desiredMission);
        }

        cachedDesiredMission = desiredMission;
    }

    private Optional<MissionBase> getAutoMissionForParams(Command mission) {
        switch (mission) {
            // do nothing mission
            case doNothing:
                //return Optional.of(new DoNothingMission());
                missionChooser.getSelected();
            case ScoringL4Mission:
                if (alliance == "Red") {
                   return missionChooser.getSelected();
                }
                else if (alliance == "Blue") {
                    missionChooser.getSelected();
                }
            // if no auto mission is found
            default:
                System.err.println("No valid autonomous mission found for" + mission);
                return Optional.empty();
        }
    }

    public void reset() {
        autoMission = Optional.empty();
        cachedDesiredMission = Command.doNothing;
    }

    public void outputToSmartDashboard() {
        SmartDashboard.putString("AutoMissionSelected", cachedDesiredMission.name());
    }

    public Optional<MissionBase> getAutoMission() {
        return autoMission;
    }
}