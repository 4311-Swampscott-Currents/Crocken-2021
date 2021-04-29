package frc.robot.commands;

import org.swampscottcurrents.serpentframework.*;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.*;
import frc.robot.subsystems.*;

public class IntakeIdleCommand extends SerpentCommand {

    private final Robot robot;

    public IntakeIdleCommand(Intake subsystem) {
        robot = Robot.getInstance();
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        robot.intake.raiseIntake();
    }

    @Override
    public void execute() {
        if(robot.joystick.getButtonReleased(MainJoystick.TOGGLE_INTAKE_BUTTON)) {
            CommandScheduler.getInstance().schedule(new IntakePickUpBallsCommand());
        }
    }
}
