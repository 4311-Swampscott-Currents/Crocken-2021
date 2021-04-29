package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;

import org.swampscottcurrents.serpentframework.*;

import edu.wpi.first.wpilibj.*;
import frc.robot.Constants;

public class Spindexer extends SerpentSubsystem {

    private final WPI_VictorSPX spinnerMotor = new WPI_VictorSPX(20);
    private final Spark leftTowerMotor = new Spark(7);
    private final Spark rightTowerMotor = new Spark(8);

    public Spindexer() {
        spinnerMotor.setNeutralMode(NeutralMode.Coast);
        leftTowerMotor.setInverted(true);
        rightTowerMotor.setInverted(false);

        //SET DEFAULT COMMAND
    }

    /** Turns on the spindexer motor at the default speed, rotating balls around the indexing tub. */
    public void runSpinner() {
        runSpinner(Constants.SPINNER_DEFAULT_SPEED_PERCENT);
    }

    /** Turns on the spindexer motor at the specified speed, rotating balls around the indexing tub. */
    public void runSpinner(double percent) {
        spinnerMotor.set(ControlMode.PercentOutput, percent);
    }

    /** Turns off the spindexer motor. */
    public void stopSpinner() {
        spinnerMotor.stopMotor();
    }

    /** Turns on the spindexer tower motors at the default speed, drawing balls up to the shooter from the indexing tub. */
    public void runTower() {
        runTower(Constants.TOWER_DEFAULT_SPEED_PERCENT);
    }

    /** Turns on the spindexer tower motors at the specified speed, drawing balls up to the shooter from the indexing tub. */
    public void runTower(double percent) {
        leftTowerMotor.set(percent);
        rightTowerMotor.set(percent);
    }

    /** Turns off the spindexer tower motors. */
    public void stopTower() {
        leftTowerMotor.stopMotor();
        rightTowerMotor.stopMotor();
    }

    @Override
    public void reset() {
        stopSpinner();
        stopTower();
    }
}
