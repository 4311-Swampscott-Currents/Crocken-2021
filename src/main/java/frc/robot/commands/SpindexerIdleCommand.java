package frc.robot.commands;

import org.swampscottcurrents.serpentframework.*;

import frc.robot.Robot;
import frc.robot.subsystems.Spindexer;

/** Causes the spindexer to remain idle, unless balls are actively entering the system, in which case the spindexer advances the balls up to the tower and/or shooter. */
public class SpindexerIdleCommand extends SerpentCommand {

    private final Robot robot;

    public SpindexerIdleCommand(Spindexer indexer) {
        addRequirements(indexer);
        robot = Robot.getInstance();
    }

    @Override
    public void execute() {
        boolean shouldTowerSpin = false;
        if(robot.intake.getCurrentCommand() instanceof IntakePickUpBallsCommand) {
            robot.spindexer.runSpinner();
            if(!robot.spindexer.isBallInFrontOfUltrasonic()) {
                shouldTowerSpin = true;
            }
        }
        else {
            robot.spindexer.stopSpinner();
        }
        if(shouldTowerSpin) {
            robot.spindexer.runTower();
        }
        else {
            robot.spindexer.stopTower();
        }
    }
}