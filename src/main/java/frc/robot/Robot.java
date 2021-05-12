package frc.robot;

import frc.robot.commands.ExecuteGameplanCommand;
import frc.robot.subsystems.*;

import org.swampscottcurrents.serpentframework.*;
import org.swampscottcurrents.serpentframework.gameplan.GamePlan;
import org.swampscottcurrents.serpentframework.logix.*;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends FastRobot {

    private static Robot instance;

    public final MainJoystick joystick;    
    public final Drivetrain drivetrain;
    public final Intake intake;
    public final Spindexer spindexer;
    public final Shooter shooter;
    public final Climber climber;

    public Robot() {
        instance = this;
        joystick = new MainJoystick();
        drivetrain = new Drivetrain();
        intake = new Intake();
        spindexer = new Spindexer();
        shooter = new Shooter();
        climber = new Climber();
    }

    @Override
    public void robotStart() {
        LogixConditional.DefaultTimeProvider = this::getRobotTime;
    }

    @Override
    public void robotUpdate() {

        SmartDashboard.putString("Intake", e(intake.getCurrentCommand()));
        SmartDashboard.putString("Spindexer", e(spindexer.getCurrentCommand()));
        SmartDashboard.putString("Shooter", e(shooter.getCurrentCommand()));

        joystick.update();
    }

    @Override
    public void autonomousStart() {
        try {
            CommandScheduler.getInstance().schedule(new ExecuteGameplanCommand(GamePlan.deserialize(NetworkTableInstance.getDefault().getTable("GamePlan").getEntry("Plan").getString(""))));
        }
        catch(Exception e) {
            System.out.println("There was an error deserializing the GamePlan!\n" + e);
            e.printStackTrace();
        }
    }

    public static Robot getInstance() {
        return instance;
    }

    private static String e(Object o) {
        if(o == null) {
            return "";
        }
        else {
            return o.getClass().getSimpleName();
        }
    }
}
