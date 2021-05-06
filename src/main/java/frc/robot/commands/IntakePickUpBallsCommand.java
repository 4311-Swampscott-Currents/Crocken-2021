package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.swampscottcurrents.serpentframework.SerpentCommand;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.*;

public class IntakePickUpBallsCommand extends SerpentCommand {

    private Robot robot = Robot.getInstance();

    private static final WPI_VictorSPX spinnerMotor = new WPI_VictorSPX(21);

    public IntakePickUpBallsCommand() {
        addRequirements(Robot.getInstance().intake);
    }

    @Override
    public void initialize() {
        robot.intake.lowerIntake();
        robot.intake.runIntake();
        spinnerMotor.set(0.25);
    }

    @Override
    public void execute() {
        if(robot.joystick.getButtonReleased(MainJoystick.TOGGLE_INTAKE_BUTTON)) {
            cancel();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void finish(boolean interrupted) {
        spinnerMotor.stopMotor();
    }
}
