package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.*;

import org.swampscottcurrents.serpentframework.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;
import frc.robot.commands.*;

/** Represents the robot's drivetrain, providing controls for robot movement and positioning. */
public class Drivetrain extends SerpentSubsystem {

    private final WPI_TalonFX frontLeftMotor = new WPI_TalonFX(04);
    private final WPI_TalonFX frontRightMotor = new WPI_TalonFX(02);
    private final WPI_TalonFX backLeftMotor = new WPI_TalonFX(01);
    private final WPI_TalonFX backRightMotor = new WPI_TalonFX(03);

    private AHRS gyroscope = new AHRS();

    private DifferentialDrive differentialDrive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

    /** Creates a new drivetrain instance, setting the proper settings on the drivetrain motors. */
    public Drivetrain() {
        frontLeftMotor.setInverted(InvertType.InvertMotorOutput);
        backLeftMotor.setInverted(InvertType.FollowMaster);
        backRightMotor.setInverted(InvertType.FollowMaster);

        backLeftMotor.follow(frontLeftMotor);
        backRightMotor.follow(frontRightMotor);
 
        differentialDrive.setSafetyEnabled(false);
        differentialDrive.setRightSideInverted(false);

        frontLeftMotor.setNeutralMode(NeutralMode.Brake);
        frontRightMotor.setNeutralMode(NeutralMode.Brake);
        backLeftMotor.setNeutralMode(NeutralMode.Brake);
        backRightMotor.setNeutralMode(NeutralMode.Brake);

        setDefaultCommand(new TestMotorCommand(this));
        //setDefaultCommand(new OperatorControlCommand(this));
    }

    /** Sets the drivetrain motor outputs to the specified speeds, in percent. */
    public void drivePercent(double leftSpeed, double rightSpeed) {
        frontLeftMotor.set(ControlMode.PercentOutput, leftSpeed);
        frontRightMotor.set(ControlMode.PercentOutput, rightSpeed);
    }

    /** Drives the robot using differential controls, taking the percentage of movement that should be moving forward/backward as well as the percentage that should be turning left/right. */
    public void driveDifferential(double forwardPercent, double rotationPercent) {
        differentialDrive.arcadeDrive(forwardPercent, rotationPercent);
    }

    /** Obtains the current speed, in percent, of the left side of the drivetrain. */
    public double getLeftMotorOutput() {
        return frontLeftMotor.get();
    }

    /** Obtains the current speed, in percent, of the right side of the drivetrain. */
    public double getRightMotorOutput() {
        return frontRightMotor.get();
    }

    /** Obtains the current angle that the robot is facing. */
    public double getGyroscopeRotation() {
        return gyroscope.getAngle();
    }

    /** Resets the encoder counts on each of the drivetrain motors. */
    public void resetEncoders() {
        frontLeftMotor.setSelectedSensorPosition(0);
        frontRightMotor.setSelectedSensorPosition(0);
    }

    /** Obtains the current position of the left motor encoder in raw TalonFX encoder ticks. */
    public int getLeftEncoderPosition() {
        return frontLeftMotor.getSelectedSensorPosition();
    }

    /** Obtains the current position of the right motor encoder in raw TalonFX encoder ticks. */
    public int getRightEncoderPosition() {
        return frontRightMotor.getSelectedSensorPosition();
    }

    /** Resets the robot's gyroscope, setting its current angle to be the default angle. */
    public void resetGyroscope() {
        gyroscope.reset();
    }

    /** Stops the drivetrain, halting all motors. */
    public void stop() {
        frontLeftMotor.stopMotor();
        frontRightMotor.stopMotor();
    }

    @Override
    public void reset() {
        stop();
    }
}
