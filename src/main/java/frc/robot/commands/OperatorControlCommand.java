package frc.robot.commands;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.Base64;

import com.kauailabs.navx.frc.Quaternion;

import org.swampscottcurrents.serpentframework.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    public void initialize() {
        
        System.out.println("yeet");
        times.clear();
        leftSpeed.clear();
        rightSpeed.clear();
    }
    
    private double processRotationPower(double degrees) {
        final double maxPower = 0.75;
        return Math.max(-maxPower, Math.min(maxPower, signPow(degrees / 90, 0.35)));
    }

    private double signPow(double value, double power) {
        return Math.signum(value) * Math.pow(Math.abs(value), power);
    }

    @Override
    public void execute() {
        if(isControlFieldOriented) {
            Quaternion2D robotOrientation = Quaternion2D.fromDegrees(drivetrain.getGyroscopeRotation());

            double x = -joystick.getAxis(MainJoystick.Y_AXIS) * 0.75;
            double y = joystick.getAxis(MainJoystick.X_AXIS) * 0.75;

            Quaternion2D joystickOrientation = Quaternion2D.fromAxis(x, y);

            double result = Quaternion2D.subtract(joystickOrientation, robotOrientation).toDegrees();

            double dist = Math.sqrt(x * x + y * y);

            SmartDashboard.putNumber("res", result);
            SmartDashboard.putNumber("for", dist * Math.max(0, 1 - Math.abs(result / 90)));
            SmartDashboard.putNumber("turn", dist * (result / 180));

            drivetrain.driveDifferential(-dist * Math.max(0, 1 - Math.abs(result / 150)), -dist * processRotationPower(result));

            /*if(x != 0 || y != 0) {
                if(x == 0) {
                    x = Double.MIN_VALUE;
                }
                if(y == 0) {
                    y =  Double.MIN_VALUE;
                }
            }

            double xFinal = robotOrientation.x * x + robotOrientation.y * y;
            double yFinal = Math.signum(x) * (y * robotOrientation.x - x * robotOrientation.y);

            SmartDashboard.putNumber("Gyros", drivetrain.getGyroscopeRotation());
            SmartDashboard.putNumber("XFIn", xFinal);
            SmartDashboard.putNumber("YFin", yFinal);*/

            //drivetrain.driveDifferential(xFinal, 3 * yFinal);
        }
        else {
            drivetrain.driveDifferential(joystick.getAxis(MainJoystick.Y_AXIS), -joystick.getAxis(MainJoystick.X_AXIS));
        }
        if(joystick.getButtonReleased(MainJoystick.SWAP_DRIVE_ORIENTATION_BUTTON)) {
            drivetrain.resetGyroscope();
            isControlFieldOriented = !isControlFieldOriented;
        }
        recordTimingAndOutputs();
    }

    private ArrayList<Double> times = new ArrayList<Double>();
    private ArrayList<Double> leftSpeed = new ArrayList<Double>();
    private ArrayList<Double> rightSpeed = new ArrayList<Double>();

    private void recordTimingAndOutputs() {
        times.add(Robot.getInstance().getTimeDelta());
        leftSpeed.add(drivetrain.frontLeftMotor.get());
        rightSpeed.add(drivetrain.frontRightMotor.get());
    }

    @Override
    public void finish(boolean interrupted) {
        
        System.out.println("yert");
        double[] timeArr = new double[times.size()];
        for(int i = 0; i < timeArr.length; i++) {
            timeArr[i] = times.get(i);
        }

        System.out.println("B:" + encodeLocation(timeArr) + ":E");
    }

    public static String encodeLocation(double[] doubleArray) {
        return Base64.getEncoder().encodeToString(doubleToByteArray(doubleArray));
    }
    public static double[] decodeLocation(String base64Encoded) {
        return byteToDoubleArray(Base64.getDecoder().decode(base64Encoded));
    }
    private static byte[] doubleToByteArray(double[] doubleArray) {
        ByteBuffer buf = ByteBuffer.allocate(Double.SIZE / Byte.SIZE * doubleArray.length);
        buf.asDoubleBuffer().put(doubleArray);
        return buf.array();
    }
    private static double[] byteToDoubleArray(byte[] bytes) {
        DoubleBuffer buf = ByteBuffer.wrap(bytes).asDoubleBuffer();
        double[] doubleArray = new double[buf.limit()];
        buf.get(doubleArray);
        return doubleArray;
    }
}
