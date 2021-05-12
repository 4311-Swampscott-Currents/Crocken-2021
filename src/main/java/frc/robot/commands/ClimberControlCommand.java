package frc.robot.commands;

import org.swampscottcurrents.serpentframework.*;

import frc.robot.MainJoystick;
import frc.robot.Robot;
import frc.robot.subsystems.*;

public class ClimberControlCommand extends SerpentCommand {

    private final Robot robot;

    public ClimberControlCommand(Climber climber) {

        robot = Robot.getInstance();

        addRequirements(climber);
    }

    @Override
    public void execute() {
        if(robot.joystick.getButtonReleased(MainJoystick.TOGGLE_HOOK_BUTTON)) {
            robot.climber.toggleHook();
        }
        if(robot.joystick.getButton(MainJoystick.RUN_WINCH_BUTTON)) {
            robot.climber.runWinch();
        }
        else {
            robot.climber.stopWinch();
        }
    }
}