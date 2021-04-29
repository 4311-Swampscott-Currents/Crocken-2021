package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.swampscottcurrents.serpentframework.SerpentCommand;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.MainJoystick;
import frc.robot.Robot;

public class ShooterCommand extends SerpentCommand {
    private final WPI_VictorSPX leftTowerMotor = new WPI_VictorSPX(21);
    private final VictorSP rightTowerMotor = new VictorSP(0);
    private final WPI_TalonFX shooterMotor = new WPI_TalonFX(5);
    private final VictorSP indexerMotor = new VictorSP(1);

    public ShooterCommand(Subsystem... system) {
        addRequirements(system);
    }

    @Override
    public void initialize() {
        SmartDashboard.putNumber("LeftTower", SmartDashboard.getNumber("LeftTower", 0));
        SmartDashboard.putNumber("RightTower", SmartDashboard.getNumber("RightTower", 0));
        SmartDashboard.putNumber("Shooter", SmartDashboard.getNumber("Shooter", 0));
        SmartDashboard.putNumber("Indexer", SmartDashboard.getNumber("Indexer", 0));
    }

    @Override
    public void execute() {
        if(Robot.getInstance().joystick.getButton(MainJoystick.RUN_TEST_MOTOR_BUTTON)) {
            leftTowerMotor.set(SmartDashboard.getNumber("LeftTower", 0));
            rightTowerMotor.set(SmartDashboard.getNumber("RightTower", 0));
            shooterMotor.set(SmartDashboard.getNumber("Shooter", 0));
            indexerMotor.set(SmartDashboard.getNumber("Indexer", 0));
        }
        else {
            leftTowerMotor.stopMotor();
            rightTowerMotor.stopMotor();
            shooterMotor.stopMotor();
            indexerMotor.stopMotor();
        }
    }
}
