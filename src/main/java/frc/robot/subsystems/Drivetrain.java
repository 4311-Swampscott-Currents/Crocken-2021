package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.*;

import org.swampscottcurrents.serpentframework.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.OperatorControlCommand;
import frc.robot.commands.TestMotorCommand;

public class Drivetrain extends SerpentSubsystem {
    public WPI_TalonFX frontLeftMotor = new WPI_TalonFX(11);
    public WPI_TalonFX frontRightMotor = new WPI_TalonFX(13);
    private WPI_TalonFX backLeftMotor = new WPI_TalonFX(12);
    private WPI_TalonFX backRightMotor = new WPI_TalonFX(14);

    private AHRS gyroscope = new AHRS();

    private DifferentialDrive differentialDrive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

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

        //setDefaultCommand(new TestMotorCommand(this));
        setDefaultCommand(new OperatorControlCommand(this));
    }

    public void drivePercent(double leftSpeed, double rightSpeed) {
        frontLeftMotor.set(ControlMode.PercentOutput, leftSpeed);
        frontRightMotor.set(ControlMode.PercentOutput, rightSpeed);
    }

    public void driveDifferential(double forwardPercent, double rotationPercent) {
        differentialDrive.arcadeDrive(forwardPercent, rotationPercent);
    }

    public double getGyroscopeRotation() {
        return gyroscope.getAngle();
    }

    public void resetEncoders() {
        frontLeftMotor.setSelectedSensorPosition(0);
        frontRightMotor.setSelectedSensorPosition(0);
    }

    public int getLeftEncoderPosition() {
        return frontLeftMotor.getSelectedSensorPosition();
    }

    public int getRightEncoderPosition() {
        return frontRightMotor.getSelectedSensorPosition();
    }

    public void resetGyroscope() {
        gyroscope.reset();
    }

    public void stop() {
        frontLeftMotor.stopMotor();
        frontRightMotor.stopMotor();
    }

    @Override
    public void reset() {
        stop();
    }
}
