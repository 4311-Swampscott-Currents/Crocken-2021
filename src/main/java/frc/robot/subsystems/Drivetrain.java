package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.*;

import org.swampscottcurrents.serpentframework.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.TestMotorCommand;

public class Drivetrain extends SerpentSubsystem {
    private WPI_TalonFX frontLeftMotor = new WPI_TalonFX(0);
    private WPI_TalonFX frontRightMotor = new WPI_TalonFX(1);
    private WPI_TalonFX backLeftMotor = new WPI_TalonFX(2);
    private WPI_TalonFX backRightMotor = new WPI_TalonFX(3);

    private ADXRS450_Gyro gyroscope = new ADXRS450_Gyro();

    private DifferentialDrive differentialDrive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

    public Drivetrain() {
        frontLeftMotor.setInverted(InvertType.InvertMotorOutput);

        backLeftMotor.follow(frontLeftMotor);
        backRightMotor.follow(frontRightMotor);

        differentialDrive.setSafetyEnabled(false);
        differentialDrive.setRightSideInverted(false);

        setDefaultCommand(new TestMotorCommand(new VictorSP(0), this));
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
