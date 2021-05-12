package frc.robot.commands;

import org.swampscottcurrents.serpentframework.*;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

/** Causes the robot to drive in a straight line for a specified number of inches. */
public class DriveStraightCommand extends LogixCommand {

    private final double rotationGoal;
    private final double rotationSpeed;

    /** Creates a new DriveStraightCommand that will drive the robot forward the specified length at the specified speed, in inches per second. */
    public DriveStraightCommand(double inches, double speed) {
        rotationGoal = inches / Constants.WHEEL_CIRCUMFERENCE_INCHES / Constants.WHEEL_ROTATIONS_PER_MOTOR_ROTATION;
        rotationSpeed = speed / Constants.WHEEL_CIRCUMFERENCE_INCHES / Constants.WHEEL_ROTATIONS_PER_MOTOR_ROTATION / Constants.TALONFX_MAX_ROTATIONS_PER_SECOND;

        Drivetrain dt = Robot.getInstance().drivetrain;
        addRequirements(dt);

        getLogixController()
            .run(() -> dt.resetEncoders())
            .during(() -> !areWheelsWithinThreshhold(dt))
                .run(() -> dt.drivePercent(getOutputForError(dt.getLeftEncoderPosition()), getOutputForError(dt.getRightEncoderPosition())))
            .then();
    }

    private double getOutputForError(int encoderPosition) {
        return rotationSpeed * (2 / Math.PI) * Math.atan(Constants.DRIVESTRAIGHT_DAMPING_COEFFICIENT * (rotationGoal - encoderPosition / Constants.TALONFX_ENCODER_TICKS_PER_ROTATION));
    }

    private boolean areWheelsWithinThreshhold(Drivetrain dt) {
        return Math.abs(rotationGoal - dt.getLeftEncoderPosition() / Constants.TALONFX_ENCODER_TICKS_PER_ROTATION) < Constants.DRIVESTRAIGHT_THRESHOLD_INCHES
            && Math.abs(rotationGoal - dt.getRightEncoderPosition() / Constants.TALONFX_ENCODER_TICKS_PER_ROTATION) < Constants.DRIVESTRAIGHT_THRESHOLD_INCHES;
    }
}
