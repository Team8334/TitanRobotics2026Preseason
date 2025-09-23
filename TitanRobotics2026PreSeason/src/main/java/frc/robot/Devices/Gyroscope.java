package frc.robot.Devices;

import com.studica.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;

public class Gyroscope {
    
    private static Gyroscope instance = null;
    AHRS ahrs;

    /*
     * making an instance of gyroscope
     */
    public static Gyroscope getInstance(){
        if (instance == null)
        {
            instance = new Gyroscope();
        }
        return instance;
    }
    
    /* the main function of gyro */
    public Gyroscope(){
        ahrs = new AHRS(AHRS.NavXComType.kMXP_SPI);
        ahrs.reset();
    }

    /* get the angle in degrees from the gyro */
    public double getAngleDegrees(){
        return ahrs.getAngle();
    }

    public Rotation2d getAngleRotation(){
        return Rotation2d.fromDegrees(ahrs.getAngle());
    }

    public void reset(){

    }
}
