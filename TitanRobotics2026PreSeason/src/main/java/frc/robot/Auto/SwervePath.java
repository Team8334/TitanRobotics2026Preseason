package frc.robot.Auto;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Subsystems.SwerveBase;

public class SwervePath implements Actions{
    
    private double seconds;
    Timer timer;

    public SwervePath (double seconds){
        this.seconds = seconds;
        
    }
    
    @Override
    public void start(){

        timer = new Timer();
        timer.start();
    }

    @Override
    public void update(){
    }
    
    @Override
    public boolean isFinished() {
        return timer.get() >= seconds;
    }

    @Override
    public void done(){
        timer.stop();
    }
}