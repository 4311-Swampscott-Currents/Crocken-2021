package frc.robot.commands;

import org.swampscottcurrents.serpentframework.SerpentCommand;

import frc.robot.*;

public class IntakePickUpBallsCommand extends SerpentCommand {

    private Robot robot = Robot.getInstance();

    @Override
    public void initialize() {
        robot.intake.lowerIntake();
        robot.intake.runIntake();
    }

    @Override
    public boolean isFinished() {
        return robot.joystick.getButtonReleased(MainJoystick.TOGGLE_INTAKE_BUTTON);
    }
}
