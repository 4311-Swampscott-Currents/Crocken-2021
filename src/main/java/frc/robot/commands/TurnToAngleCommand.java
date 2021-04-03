package frc.robot.commands;

import org.swampscottcurrents.serpentframework.*;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

/** Causes the robot to turn to the specified absolute angle. */
public class TurnToAngleCommand extends SerpentCommand {

    private final Drivetrain drivetrain;
    private final double rotationGoal;
    private final double rotationSpeed;

    private double currentErrorDegrees;

    /** Creates a new TurnToAngleCommand, which will turn the robot to the specified angle at the specified speed in inches per second. */
    public TurnToAngleCommand(double degrees, double speed) {
        drivetrain = Robot.getInstance().drivetrain;
        rotationGoal = degrees;
        rotationSpeed = speed / Constants.WHEEL_CIRCUMFERENCE_INCHES / Constants.WHEEL_ROTATIONS_PER_MOTOR_ROTATION / Constants.TALONFX_MAX_ROTATIONS_PER_SECOND;
        currentErrorDegrees = Double.MAX_VALUE;
    }

    @Override
    public void execute() {
        currentErrorDegrees = Quaternion2D.subtract(Quaternion2D.fromDegrees(rotationGoal), Quaternion2D.fromDegrees(drivetrain.getGyroscopeRotation())).toDegrees();
        double output = rotationSpeed * (2 / Math.PI) * Math.atan(Constants.DRIVESTRAIGHT_DAMPING_COEFFICIENT * currentErrorDegrees);
        drivetrain.drivePercent(-output, output);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(currentErrorDegrees) < Constants.TURNTOANGLE_THRESHOLD_DEGREES;
    }
}