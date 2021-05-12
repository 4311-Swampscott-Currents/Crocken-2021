package org.swampscottcurrents.serpentframework.gameplan.infiniterecharge;

import org.swampscottcurrents.serpentframework.gameplan.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class FireBalls extends GameAction {

    @Override
    public Command getActionCommand(double currentX, double currentY) {
        return new InstantCommand(() -> System.out.println("YEEET. There's no action associated with the FireBalls GameAction. See FireBalls.java"));
    }

    @Override
    public String toString() {
        return "Fire balls";
    }
}