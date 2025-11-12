package frc.robot;

import frc.robot.Devices.Controller;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Subsystems.SwerveBase;
import frc.robot.Data.PortMap;

public class Teleop {

    Controller driverController; //object of Controller
    SwerveBase swerveBase; //object of SwerveBase

    private double controllerLeftX; //variable for the left x joystick axis
    private double controllerLeftY; //variable for the left y joystick axis
    private double controllerRightX; //variable for the right x joystick axis
    private boolean controllerRightBumper; //variable for if the right bumper is pressed

    public Teleop() {
        driverController = new Controller(PortMap.DRIVER_CONTROLLER); //creates a new controller
        swerveBase = SwerveBase.getInstance(); //gets an instance of SwerveBase
    }

    public void teleopPeriodic() //everything in this method will get executed 
    {
        driveBaseControl(); //executes the driveBaseControl method
    }

    public void driveBaseControl() {
        controllerLeftY = driverController.getLeftY(); //sets the variable controllerLeftY to the actual data coming from the controller
        controllerLeftX = driverController.getLeftX(); //sets the variable controllerLeftX to the actual data coming from the controller
        controllerRightX = driverController.getRightX(); //sets the variable controllerRightX to the actual data coming from the controller
        controllerRightBumper = driverController.getRightBumperButton(); //sets the variable controllerRightBumper to the actual data coming from the controller

        double forward; 
        double strafe; //Rhea this means going side to side
        double rotation = 0;

        boolean isFieldOrriented = false;

        if (Math.abs(controllerLeftY) >= 0.1) {
            forward = -(controllerLeftY);
        } else {
            forward = 0;
        }
        if (Math.abs(controllerLeftX) >= 0.1) {
            strafe = -(controllerLeftX);
        } else {
            strafe = 0;
        }
        /*if (Math.abs(controllerRightX) >= 0.1) {
            rotation = (controllerRightX);
        } else {
            rotation = 0;
        }*/

        if(controllerRightBumper && isFieldOrriented == true)
        {
            isFieldOrriented = false;
        }

        if(controllerRightBumper && isFieldOrriented == false)
        {
            isFieldOrriented = true;
        }

        if(isFieldOrriented) //translation2d is used for lateral movement of the swerve drive
        {
            swerveBase.drive(new Translation2d(forward,strafe), rotation, true);
        }
        else 
        {
            swerveBase.drive(new Translation2d(forward,strafe), rotation, false);
        }
    }
}
