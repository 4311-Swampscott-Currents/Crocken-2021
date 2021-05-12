package org.swampscottcurrents.serpentframework.gameplan;

/** Represents the starting position of the robot, or where it has been placed on the field. */
public class RobotPosition extends GameAction implements IPositionChangingAction {
    /** The horizontal position, in feet, from the top-left corner of the playing field where the robot has been placed. */
    public double positionX = 5;
    /** The vertical position, in feet, from the top-left corner of the playing field where the robot has been placed. */
    public double positionY = 5;

    @Override
    public double getFinalPositionX() {
        return positionX;
    }

    @Override
    public double getFinalPositionY() {
        return positionY;
    }
}