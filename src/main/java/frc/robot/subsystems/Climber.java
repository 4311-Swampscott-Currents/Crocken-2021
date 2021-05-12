package frc.robot.subsystems;

import org.swampscottcurrents.serpentframework.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
import frc.robot.commands.ClimberControlCommand;

public class Climber extends SerpentSubsystem {

    public DoubleSolenoid hookPiston = new DoubleSolenoid(2, 3);

    public Spark leftWinchMotor = new Spark(3);
    public Spark rightWinchMotor = new Spark(4);

    public Climber() {
        setDefaultCommand(new ClimberControlCommand(this));
    }

    /** Raises the hook piston, hoisting the hook into the air. */
    public void raiseHook() {
        hookPiston.set(Value.kForward);
    }

    /** Lowers the hook piston. */
    public void lowerHook() {
        hookPiston.set(Value.kReverse);
    }

    /** Switches the current position of the hook piston. */
    public void toggleHook() {
        hookPiston.toggle();
    }

    /** Runs the two winch motors at the default speed. */
    public void runWinch() {
        runWinch(Constants.WINCH_DEFAULT_SPEED_PERCENT);
    }

    /** Runs the two winch motors at the specified speed, in percent. */
    public void runWinch(double speed) {
        leftWinchMotor.set(speed);
        rightWinchMotor.set(speed);
    }

    /** Stops the two winch motors. */
    public void stopWinch() {
        leftWinchMotor.stopMotor();
        rightWinchMotor.stopMotor();
    }

    @Override
    public void reset() {
        stopWinch();
    }
}