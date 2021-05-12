package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.swampscottcurrents.serpentframework.SerpentCommand;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.MainJoystick;
import frc.robot.Robot;

/** Causes the shooter to remain off. */
public class ShooterIdleCommand extends SerpentCommand {
    private final WPI_VictorSPX leftTowerMotor = new WPI_VictorSPX(21);
    private final VictorSP rightTowerMotor = new VictorSP(0);
    private final WPI_TalonFX shooterMotor = new WPI_TalonFX(5);
    private final VictorSP indexerMotor = new VictorSP(1);

    public ShooterIdleCommand(Subsystem... system) {
        addRequirements(system);
    }

    @Override
    public void execute() {
        if(Robot.getInstance().joystick.getButton(MainJoystick.SHOOT_BUTTON)) {
            CommandScheduler.getInstance().schedule(new ShootBallsCommand());
        }
    }
}
