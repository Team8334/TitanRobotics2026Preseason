package frc.robot.Interface;

public interface Subsystem {
    public void update();

    public void initialize();

    public void log();

    public boolean isEnabled();

    public String getName();
}
