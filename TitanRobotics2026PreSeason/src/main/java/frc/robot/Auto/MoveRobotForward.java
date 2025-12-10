package frc.robot.Auto;

import java.util.ArrayList;

import edu.wpi.first.math.controller.HolonomicDriveController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Subsystems.SwerveBase;
import frc.robot.Interface.AutoActions;

public class MoveRobotForward implements AutoActions{
    Trajectory trajectory;
    HolonomicDriveController controller;
    SwerveBase swerveBase;
    Timer timer;


    public MoveRobotForward(){
        swerveBase = SwerveBase.getInstance();
            
        controller = new HolonomicDriveController(
        new PIDController(1, 0, 0), new PIDController(1, 0, 0),
        new ProfiledPIDController(1, 0, 0,
        new TrapezoidProfile.Constraints(6.28, 3.14)));

        var sideStart = new Pose2d(1.54,23.23, Rotation2d.fromDegrees(-180));
        var crossScale = new Pose2d(0, 5, Rotation2d.fromDegrees(-160));

        trajectory = TrajectoryGenerator.generateTrajectory(
        sideStart,
         //interiorWaypoints,
        null, crossScale, null);
        //config);

    }

    @Override
    public void start() {
        timer = new Timer();
        timer.start();
            }
    
    
    public void update() {

        Pose2d currentRobotPose = swerveBase.getPose();
        
        Trajectory.State goal = trajectory.sample(3.4);
        // Get the adjusted speeds. Here, we want the robot to be facing
        // 70 degrees (in the field-relative coordinate system).
        ChassisSpeeds adjustedSpeeds = controller.calculate(
            currentRobotPose, goal, Rotation2d.fromDegrees(70.0));
            swerveBase.drive(adjustedSpeeds);
    }


    @Override
    public boolean isFinished() {
        return timer.get() >= seconds;
    }

    @Override
    public void done() {
        timer.stop();
        swerveBase.drive(0, 0, 0);
    }



    /*
     * var interiorWaypoints = new ArrayList<Translation2d>();
     * interiorWaypoints.add(new Translation2d(Units.feetToMeters(14.54),
     * Units.feetToMeters(23.23)));
     * interiorWaypoints.add(new Translation2d(Units.feetToMeters(21.04),
     * Units.feetToMeters(18.23)));
     * 
     * TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(12),
     * Units.feetToMeters(12));
     * config.setReversed(true);
*/
}
