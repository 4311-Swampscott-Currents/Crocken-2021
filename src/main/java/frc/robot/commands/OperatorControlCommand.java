package frc.robot.commands;

import com.kauailabs.navx.frc.Quaternion;

import org.swampscottcurrents.serpentframework.*;

import frc.robot.MainJoystick;
import frc.robot.Robot;
import frc.robot.subsystems.*;

public class OperatorControlCommand extends SerpentCommand {

    private final MainJoystick joystick;
    private final Drivetrain drivetrain;
    private boolean isControlFieldOriented = false;

    public OperatorControlCommand(Drivetrain train) {
        joystick = Robot.getInstance().joystick;
        drivetrain = train;
        addRequirements(train);
    }

    @Override
    public void execute() {
        if(isControlFieldOriented) {
            Quaternion2D robotOrientation = Quaternion2D.fromDegrees(drivetrain.getGyroscopeRotation());

            double x = joystick.getAxis(MainJoystick.Y_AXIS);
            double y = -joystick.getAxis(MainJoystick.X_AXIS);

            double xFinal = robotOrientation.x * x + robotOrientation.y * y;
            double yFinal = Math.signum(x) * (y * robotOrientation.x - x * robotOrientation.y);

            drivetrain.driveDifferential(xFinal, yFinal);
        }
        else {
            drivetrain.driveDifferential(joystick.getAxis(MainJoystick.Y_AXIS), joystick.getAxis(MainJoystick.X_AXIS));
        }
        if(joystick.getButtonReleased(MainJoystick.SWAP_DRIVE_ORIENTATION_BUTTON)) {
            isControlFieldOriented = !isControlFieldOriented;
        }
    }
}
