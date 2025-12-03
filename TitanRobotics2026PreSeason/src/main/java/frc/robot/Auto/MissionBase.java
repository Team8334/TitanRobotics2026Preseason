package frc.robot.Auto;

import frc.robot.Auto.AutoEndedException;
//import frc.robot.Auto.Actions;
import edu.wpi.first.wpilibj.DriverStation;

/*
 * This mission base does something
 */

public abstract class MissionBase {
    
/**
 * An abstract class that is the basis of the robot's autonomous routines. This is implemented in auto missions (which are routines that do actions).
 */

    protected final double mUpdateRate = 1.0 / 50.0;
    protected boolean mActive = false;
    protected boolean mIsInterrupted = false;

    protected abstract void routine() throws AutoEndedException;

    public void setStartPose() {
        
    }

    public void run() {
        mActive = true;

        try {
            routine();
        } 
        catch (AutoEndedException e) {
            DriverStation.reportError("AUTO MISSION DONE!!!! ENDED EARLY!!!!", false);
            return;
        }

        done();
    }

    public void done() {
        System.out.println("Auto mission done");
    }

    public void stop() {
        mActive = false;
    }

    public boolean isActive() {
        return mActive;
    }

    public boolean isActiveWithThrow() throws AutoEndedException {
        if (!isActive()) {
            throw new AutoEndedException();
        }

        return isActive();
    }

    public void interrupt() {
        System.out.println("** Auto mission interrrupted!");
        mIsInterrupted = true;
    }

    public void resume() {
        System.out.println("** Auto mission resumed!");
        mIsInterrupted = false;
    }

    public void runAction(Actions action) throws AutoEndedException {
        isActiveWithThrow();
        long waitTime = (long) (mUpdateRate * 1000.0);
        // WaitForNumBannerSensorsAction for interrupt state to clear
        while (isActiveWithThrow() && mIsInterrupted) {
            try {
                Thread.sleep(waitTime);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        action.start();

        // Run action, stop action on interrupt, non active mission, or done
        while (isActiveWithThrow() && !action.isFinished() && !mIsInterrupted) {
            action.update();

            try {
                Thread.sleep(waitTime);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        action.done();

    }

    public boolean getIsInterrupted() {
        return mIsInterrupted;
    }
}