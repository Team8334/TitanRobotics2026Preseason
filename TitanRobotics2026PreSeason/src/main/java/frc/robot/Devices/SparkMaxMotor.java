package frc.robot.Devices;

import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.Data.Debug;

public class SparkMaxMotor 
{

    private static SparkMaxMotor instance = null;

    public static SparkMaxMotor getInstance() 
    {
        if (instance == null) 
        {
            instance = new SparkMaxMotor();
        }
        return instance;
    }
    
    public SparkMaxMotor() 
    {
        
    }
}

