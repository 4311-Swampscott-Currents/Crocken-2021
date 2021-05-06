package frc.robot;

import frc.robot.subsystems.*;

import org.swampscottcurrents.serpentframework.*;
import org.swampscottcurrents.serpentframework.logix.*;

public class Robot extends FastRobot {

    private static Robot instance;

    public  MainJoystick joystick;    
    public  Drivetrain drivetrain;
    public  Intake intake;
    public  Shooter shooter;

    public Robot() {
        instance = this;
        joystick = new MainJoystick();
        drivetrain = new Drivetrain();
        intake = new Intake();
        //shooter = new Shooter();
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
