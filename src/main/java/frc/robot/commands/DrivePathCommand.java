package frc.robot.commands;

import org.swampscottcurrents.serpentframework.SerpentCommand;

import edu.wpi.first.wpilibj.SpeedController;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class DrivePathCommand extends SerpentCommand {

    private final SpeedController leftMotors;
    private final SpeedController rightMotors;

    private final double[] timeDeltas;
    private final double[] leftMotorSpeeds;
    private final double[] rightMotorSpeeds;

    private int currentSegment;
    private double currentTime;

    public DrivePathCommand(Drivetrain train, double[] times, double[] lefts, double[] rights, SpeedController left, SpeedController right) {
        addRequirements(train);
        
        timeDeltas = times;
        leftMotorSpeeds = lefts;
        rightMotorSpeeds = rights;
        leftMotors = left;
        rightMotors = right;
    }

    @Override
    public void initialize() {
        currentSegment = 0;
        currentTime = Robot.getInstance().getRobotTime() + timeDeltas[0];
    }

    @Override
    public void execute() {
        double difference = currentTime - Robot.getInstance().getRobotTime();
        while(difference < 0) {
            currentSegment++;
            if(currentSegment > timeDeltas.length) {
                leftMotors.stopMotor();
                rightMotors.stopMotor();
                return;
            }
            currentTime += timeDeltas[currentSegment]; 
            difference = currentTime - Robot.getInstance().getRobotTime();
        }
        leftMotors.set(leftMotorSpeeds[currentSegment]);
        rightMotors.set(rightMotorSpeeds[currentSegment]);
    }

    @Override
    public boolean isFinished() {
        return currentSegment > timeDeltas.length;
    }
}
