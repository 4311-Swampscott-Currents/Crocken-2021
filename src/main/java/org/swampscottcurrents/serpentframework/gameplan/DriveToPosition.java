package org.swampscottcurrents.serpentframework.gameplan;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.DriveStraightCommand;
import frc.robot.commands.TurnToAngleCommand;

/** Represents an action where the robot turns toward and then drives to a certain position. */
public class DriveToPosition extends GameAction implements IPositionChangingAction {
    /** The horizontal position, in feet, from the top-left corner of the playing field that the robot should drive to. */
    public double positionX;
    /** The vertical position, in feet, from the top-left corner of the playing field that the robot should drive to. */
    public double positionY;
    /** What speed the robot should drive at. If negative, the robot should use its default speed. */
    public double speed = -1;
    /** Whether the robot should drive backwards. */
    public boolean driveBackwards = false;

    /** Creates a new DriveToPosition action with default values. */
    public DriveToPosition() {}

    /** Creates a new DriveToPosition action with the specified coordinates as the target position. */
    public DriveToPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    @Override
    public Command getActionCommand(double currentX, double currentY) {
        return new SequentialCommandGroup(
            new TurnToAngleCommand(Math.toDegrees(Math.atan2(positionY - currentY, positionX - currentX)) + (driveBackwards ? 180 : 0), Constants.DEFAULT_TURNTOANGLE_SPEED_INCHES_PER_SECOND),
            new DriveStraightCommand((driveBackwards ? -1 : 1) * Units.feetToMeters(Math.sqrt(Math.pow(positionX - currentX, 2) + Math.pow(positionY - currentY, 2))), speed > 0 ? Units.feetToMeters(speed) : Constants.DEFAULT_DRIVESTRAIGHT_SPEED_INCHES_PER_SECOND));
    }

    /** Gets a string representation of this object. */
    @Override
    public String toString() {
        if(speed > 0) {
            return "Drive " + (driveBackwards ? "backwards " : "") + speed + " ft/s to (" + toFeetAndInches(positionX) + ", " + toFeetAndInches(positionY) + ")";
        }
        else {
            return "Drive " + (driveBackwards ? "backwards " : "") + "to (" + toFeetAndInches(positionX) + ", " + toFeetAndInches(positionY) + ")";
        }
    }

    private String toFeetAndInches(double feet) {
        int totalFeet = (int)Math.floor(feet);
        return totalFeet + "\'" + Math.round(12 * (feet - totalFeet)) + "\"";
    }

    @Override
    public double getFinalPositionX() {
        return positionX;
    }

    @Override
    public double getFinalPositionY() {
        return positionY;
    }
}