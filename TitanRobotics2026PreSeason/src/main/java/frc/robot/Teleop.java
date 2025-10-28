package frc.robot;

import frc.robot.Devices.Controller;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Subsystems.SwerveBase;
import frc.robot.Data.PortMap;

public class Teleop {

    Controller driverController;
    SwerveBase swerveBase;

    private double controllerLeftX;
    private double controllerLeftY;
    private double controllerRightX;
    private boolean controllerRightBumper;

    public Teleop() {
        driverController = new Controller(PortMap.DRIVER_CONTROLLER);
        swerveBase = SwerveBase.getInstance();
    }

    public void teleopPeriodic()
    {
        driveBaseControl();
    }

    public void driveBaseControl() {
        controllerLeftY = driverController.getLeftY();
        controllerLeftX = driverController.getLeftX();
        controllerRightX = driverController.getRightX();
        controllerRightBumper = driverController.getRightBumperButton();

        double forward;
        double strafe;
        double rotation;

        boolean isFieldOrriented = true;

        if (Math.abs(controllerLeftY) >= 0.1) {
            forward = (controllerLeftY);
        } else {
            forward = 0;
        }
        if (Math.abs(controllerLeftX) >= 0.1) {
            strafe = (controllerLeftX);
        } else {
            strafe = 0;
        }
        if (Math.abs(controllerRightX) >= 0.1) {
            rotation = (controllerRightX);
        } else {
            rotation = 0;
        }

        if(controllerRightBumper && isFieldOrriented == true)
        {
            isFieldOrriented = false;
        }

        if(controllerRightBumper && isFieldOrriented == false)
        {
            isFieldOrriented = true;
        }

        if(isFieldOrriented)
        {
            swerveBase.drive(new Translation2d(forward,strafe), rotation, true);
        }
        else 
        {
            swerveBase.drive(new Translation2d(forward,strafe), rotation, false);
        }
    }
}
