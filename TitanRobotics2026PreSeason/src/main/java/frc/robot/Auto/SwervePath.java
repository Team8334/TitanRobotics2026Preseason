package frc.robot.Auto;

import edu.wpi.first.wpilibj.Timer;

import frc.robot.Subsystems.SwerveBase;

public class SwervePath implements Actions{
    
    private double seconds;
    Timer timer;
    private SwerveBase sDrive = null;

    public SwervePath (double seconds){
        this.seconds = seconds;
        sDrive = SwerveBase.getInstance();
    }
    
    @Override
    public void start(){

        timer = new Timer();
        timer.start();
    }

    @Override
    public void update(){
        sDrive.drive(0,0,0);
    }
    
    @Override
    public boolean isFinished() {
        return timer.get() >= seconds;
    }

    @Override
    public void done(){
        timer.stop();
        sDrive.drive();
    }
}