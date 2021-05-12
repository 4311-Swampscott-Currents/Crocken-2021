package org.swampscottcurrents.serpentframework.gameplan.infiniterecharge;

import org.swampscottcurrents.serpentframework.gameplan.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class WaitForSeconds extends GameAction {
    public double seconds = 1;

    @Override
    public Command getActionCommand(double currentX, double currentY) {
        return new WaitCommand(seconds);
    }

    @Override
    public String toString() {
        return "Wait for " + seconds + " second(s)";
    }
}