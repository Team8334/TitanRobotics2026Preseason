package frc.robot.Devices;

public class SparkMaxMotor {
    
    private static SparkMaxMotor instance = null;

    public static SparkMaxMotor getInstance(){
        if (instance == null)
        {
            instance = new SparkMaxMotor();
        }
        return instance;
    }

    public SparkMaxMotor(){

    }

}
