package frc.robot.commands;

import org.swampscottcurrents.serpentframework.SerpentCommand;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.MainJoystick;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class TestMotorCommand extends SerpentCommand {

    private Robot robot;
    private SpeedController motor;

    public TestMotorCommand(SpeedController controller, Subsystem... systems) {
        robot = Robot.getInstance();
        motor = controller;
        addRequirements(systems);
    }

    @Override
    public void execute() {
        if(robot.joystick.getButton(MainJoystick.RUN_TEST_MOTOR_BUTTON)) {
            SmartDashboard.putNumber("yeet", robot.joystick.getAxis(MainJoystick.THROTTLE_AXIS));
            motor.set(robot.joystick.getAxis(MainJoystick.THROTTLE_AXIS));
        }
        else {
            motor.stopMotor();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
