package frc.robot.commands;

import org.swampscottcurrents.serpentframework.SerpentCommand;

import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.MainJoystick;
import frc.robot.Robot;

/** Causes the robot to fire all of its balls. */
public class ShootBallsCommand extends SerpentCommand {

    private final Robot robot;

    public ShootBallsCommand() {
        robot = Robot.getInstance();
        addRequirements(robot.shooter, robot.spindexer);
    }

    @Override
    public void initialize() {
        robot.shooter.runShooter();
        robot.spindexer.runSpinner();
        robot.spindexer.runTower();
    }

    @Override
    public boolean isFinished() {
        return !robot.joystick.getButton(MainJoystick.SHOOT_BUTTON);
    }
}
