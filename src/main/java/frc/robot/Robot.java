package frc.robot;

import frc.robot.subsystems.*;

import org.swampscottcurrents.serpentframework.*;
import org.swampscottcurrents.serpentframework.logix.*;

public class Robot extends FastRobot {

    private static Robot instance;

    
    public Drivetrain drivetrain;

    @Override
    public void robotStart() {
        LogixConditional.DefaultTimeProvider = this::getRobotTime;
    }

    public static Robot getInstance() {
        return instance;
    }
}
