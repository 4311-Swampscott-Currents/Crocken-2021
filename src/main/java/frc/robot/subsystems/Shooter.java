package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import org.swampscottcurrents.serpentframework.SerpentSubsystem;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.commands.*;

public class Shooter extends SerpentSubsystem {

    private final NetworkTableEntry shooterSpeedEntry = NetworkTableInstance.getDefault().getEntry("Shooter Speed");
    private final WPI_TalonFX launcherMotor = new WPI_TalonFX(5);

    public Shooter() {
        launcherMotor.setInverted(InvertType.InvertMotorOutput);
        launcherMotor.setNeutralMode(NeutralMode.Coast);

        shooterSpeedEntry.setNumber(1);

        setDefaultCommand(new ShooterIdleCommand(this));
    }

    /** Causes the flywheel to spin at the specified speed, in percent. */
    public void runShooter(double speed) {
        launcherMotor.set(speed);
    }

    /** Causes the flywheel to spin at the speed specified on Shuffleboard, in percent. */
    public void runShooter() {
        runShooter(shooterSpeedEntry.getDouble(0));
    }

    @Override
    public void reset() {
        launcherMotor.stopMotor();
    }
}