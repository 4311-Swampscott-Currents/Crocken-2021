package frc.robot;

import frc.robot.subsystems.*;

import org.swampscottcurrents.serpentframework.*;
import org.swampscottcurrents.serpentframework.logix.*;

public class Robot extends FastRobot {

    private static Robot instance;

    public final MainJoystick joystick;    
    public final Drivetrain drivetrain;

    public Robot() {
        instance = this;
        joystick = new MainJoystick();
        drivetrain = new Drivetrain();
    }

    @Override
    public void robotStart() {
        LogixConditional.DefaultTimeProvider = this::getRobotTime;
    }

    @Override
    public void robotUpdate() {
        joystick.update();
    }

    public static Robot getInstance() {
        return instance;
    }
}
