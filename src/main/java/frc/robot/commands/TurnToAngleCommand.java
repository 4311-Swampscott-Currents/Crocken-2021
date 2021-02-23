package frc.robot.commands;

import org.swampscottcurrents.serpentframework.*;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class TurnToAngleCommand extends LogixCommand {

    private final double rotationGoal;
    private final double rotationSpeed;

    public TurnToAngleCommand(double degrees, double speed) {
        rotationGoal = degrees;
        rotationSpeed = speed / Constants.WHEEL_CIRCUMFERENCE_INCHES / Constants.WHEEL_ROTATIONS_PER_MOTOR_ROTATION / Constants.TALONFX_MAX_ROTATIONS_PER_SECOND;

        Drivetrain dt = Robot.getInstance().drivetrain;

        getLogixController()
            .run(() -> dt.resetEncoders())
            .during(() -> !areWheelsWithinThreshhold(dt))
                .run(() -> dt.drivePercent(getOutputForError(dt.getLeftEncoderPosition()), getOutputForError(dt.getRightEncoderPosition())))
            .then();
    }

    private double getOutputForError(int encoderPosition) {
        return rotationSpeed * Math.atan(Constants.DRIVESTRAIGHT_DAMPING_COEFFICIENT * (rotationGoal - encoderPosition / Constants.TALONFX_ENCODER_TICKS_PER_ROTATION));
    }

    private boolean areWheelsWithinThreshhold(Drivetrain dt) {
        return Math.abs(rotationGoal - dt.getGyroscopeRotation()) < Constants.TURNTOANGLE_THRESHOLD_DEGREES;
    }
}
