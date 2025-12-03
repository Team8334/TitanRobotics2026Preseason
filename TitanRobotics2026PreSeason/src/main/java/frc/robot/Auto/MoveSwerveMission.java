package frc.robot.Auto;

import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Auto.AutoEndedException;
import frc.robot.Auto.SwervePath;

/*
 *
 */

public class MoveSwerveMission {
    //@Override
    protected Command getAutoCommand(){
        return new PathPlannerAuto("Random Auto");
    }
}