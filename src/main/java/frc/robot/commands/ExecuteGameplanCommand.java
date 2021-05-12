package frc.robot.commands;

import org.swampscottcurrents.serpentframework.gameplan.*;

import edu.wpi.first.wpilibj2.command.*;

public class ExecuteGameplanCommand extends SequentialCommandGroup {
    public ExecuteGameplanCommand(GamePlan plan) {
        double displacementX = 0, displacementY = 0;
        for(GameAction act : plan.actions) {
            addCommands(act.getActionCommand(displacementX, displacementY));
            if(act instanceof IPositionChangingAction) {
                displacementX = ((IPositionChangingAction)act).getFinalPositionX();
                displacementY = ((IPositionChangingAction)act).getFinalPositionY();
            }
        }
    }
}