package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;

import org.swampscottcurrents.serpentframework.*;

import edu.wpi.first.wpilibj.*;
import frc.robot.*;
import frc.robot.commands.*;

public class Spindexer extends SerpentSubsystem {

    private final WPI_VictorSPX spinnerMotor = new WPI_VictorSPX(21);

    private final Ultrasonic towerUltrasonic = new Ultrasonic(1, 0);
    private final Spark leftTowerMotor = new Spark(7);
    private final Spark rightTowerMotor = new Spark(8);

    public Spindexer() {
        spinnerMotor.setNeutralMode(NeutralMode.Coast);

        Ultrasonic.setAutomaticMode(true);
        leftTowerMotor.setInverted(true);
        rightTowerMotor.setInverted(false);

        setDefaultCommand(new SpindexerIdleCommand(this));
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

    /** Returns the distance, in inches, from the ultrasonic sensor on the tower to the object in front of it. */
    public double getTowerUltrasonicDistance() {
        return towerUltrasonic.getRangeInches();
    }

    /** Returns whether a ball is currently at the top of the tower, in front of the ultrasonic. */
    public boolean isBallInFrontOfUltrasonic() {
        return getTowerUltrasonicDistance() < Constants.TOWER_ULTRASONIC_BALL_THRESHOLD_INCHES;
    }

    @Override
    public void reset() {
        stopSpinner();
        stopTower();
    }
}
