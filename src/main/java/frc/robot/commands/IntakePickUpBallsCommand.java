package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.swampscottcurrents.serpentframework.SerpentCommand;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.*;

/** Causes the intake to move into the lowered position and pick up balls. */
public class IntakePickUpBallsCommand extends SerpentCommand {

    private Robot robot = Robot.getInstance();

    public IntakePickUpBallsCommand() {
        addRequirements(Robot.getInstance().intake);
    }

    @Override
    public void initialize() {
        robot.intake.lowerIntake();
        robot.intake.runIntake();
    }

    @Override
    public void execute() {
        if(robot.joystick.getButtonReleased(MainJoystick.TOGGLE_INTAKE_BUTTON)) {
            cancel();
        }
    }
}
