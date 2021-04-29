package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import org.swampscottcurrents.serpentframework.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.*;
import frc.robot.commands.*;

public class Intake extends SerpentSubsystem {

    private final DoubleSolenoid intakePistons = new DoubleSolenoid(0, 1);
    private final WPI_VictorSPX intakeMotor = new WPI_VictorSPX(21);

    public Intake() {
        intakeMotor.setNeutralMode(NeutralMode.Coast);

        setDefaultCommand(new IntakeIdleCommand(this));
    }

    /** Actuates the intake solenoid, lifting the intake into the frame. */
    public void raiseIntake() {
        intakePistons.set(Value.kReverse);
    }

    /** Actuates the intake solenoid, lowering the intake to the ground. */
    public void lowerIntake() {
        intakePistons.set(Value.kForward);
    }

    /** Turns on the intake motor at the default speed, spinning wheels to collect balls. */
    public void runIntake() {
        runIntake(Constants.INTAKE_DEFAULT_SPEED_PERCENT);
    }

    /** Turns on the intake motor at the specified speed, spinning wheels to collect balls. */
    public void runIntake(double percent) {
        intakeMotor.set(percent);
    }

    /** Turns off the intake motor. */
    public void stopIntake() {
        intakeMotor.stopMotor();
    }

    @Override
    public void reset() {
        stopIntake();
    }
}
