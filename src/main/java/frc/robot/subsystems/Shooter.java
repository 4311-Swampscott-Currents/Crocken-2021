package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import org.swampscottcurrents.serpentframework.SerpentSubsystem;

import frc.robot.commands.*;

public class Shooter extends SerpentSubsystem {

    private final WPI_TalonFX launcherMotor = new WPI_TalonFX(5);

    public Shooter() {
        launcherMotor.setInverted(InvertType.InvertMotorOutput);
        launcherMotor.setNeutralMode(NeutralMode.Coast);

        //setDefaultCommand(new ShooterCommand(this));
    }

    @Override
    public void reset() {
        launcherMotor.stopMotor();
    }
}