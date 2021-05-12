package org.swampscottcurrents.serpentframework.gameplan;

import edu.wpi.first.wpilibj2.command.*;

/**
 * Represents an action that the robot will take during its autonomous routine.
 */
public class GameAction {
    public Command getActionCommand(double currentX, double currentY) {
        return new WaitCommand(0);
    }
}