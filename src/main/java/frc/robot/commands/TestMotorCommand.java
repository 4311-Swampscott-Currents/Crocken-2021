package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.swampscottcurrents.serpentframework.SerpentCommand;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.MainJoystick;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

/** A debug command utilized for testing motors. */
public class TestMotorCommand extends SerpentCommand {

    private Robot robot;
    private SpeedController firstMotor, secondMotor, thirdMotors, fourthMotor;

    private double firstSpeed = 0;
    private double secondSpeed = 0;
    private double thirdSpeed = 0;

    public TestMotorCommand(Subsystem... systems) {
        robot = Robot.getInstance();
        firstMotor = new Spark(7);
        secondMotor = new WPI_VictorSPX(21);
        thirdMotors = new Spark(4);
        fourthMotor = new WPI_TalonFX(5);

        addRequirements(systems);
    }
    
        
    DoubleSolenoid d = new DoubleSolenoid(0, 1);
    Solenoid e = new Solenoid(2);
    Solenoid f = new Solenoid(3);
    
    Compressor c = new Compressor();

    @Override
    public void initialize() {
        SmartDashboard.putNumber("First", firstSpeed = 0);
        SmartDashboard.putNumber("Second", secondSpeed = 0);
        SmartDashboard.putNumber("Third", thirdSpeed = 0);
        SmartDashboard.putNumber("Fourth", 0);
        c.setClosedLoopControl(true);
        d.set(Value.kReverse);
        e.set(false);
        f.set(false);
    }

    @Override
    public void execute() {
        if(robot.joystick.getButton(MainJoystick.RUN_TEST_MOTOR_BUTTON)) {
            firstMotor.set(SmartDashboard.getNumber("First", 0));
            secondMotor.set(SmartDashboard.getNumber("Second", 0));
            thirdMotors.set(SmartDashboard.getNumber("Third", 0));
            fourthMotor.set(SmartDashboard.getNumber("Fourth", 0));
        }
        else {
            firstMotor.stopMotor();
            secondMotor.stopMotor();
            thirdMotors.stopMotor();
            fourthMotor.stopMotor();
        }
        if(robot.joystick.getButtonReleased(MainJoystick.RUN_TEST_MOTOR_BUTTON)) {
            d.toggle();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
