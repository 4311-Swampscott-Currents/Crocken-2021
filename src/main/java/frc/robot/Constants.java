package frc.robot;

public class Constants {

    public static final double TALONFX_MAX_ROTATIONS_PER_SECOND = 6250;
    public static final double TALONFX_ENCODER_TICKS_PER_ROTATION = 2048;
    public static final double WHEEL_ROTATIONS_PER_MOTOR_ROTATION = 1 / 10.71;
    public static final double WHEEL_RADIUS_INCHES = 3;
    public static final double WHEEL_CIRCUMFERENCE_INCHES = 2 * WHEEL_RADIUS_INCHES * Math.PI;
    public static final double ROBOT_RADIUS_INCHES = 10.875;

    public static final double DRIVESTRAIGHT_THRESHOLD_INCHES = 0.1;
    public static final double DRIVESTRAIGHT_DAMPING_COEFFICIENT = 5;

    public static final double TURNTOANGLE_THRESHOLD_DEGREES = 2;

    public static final double SPINNER_DEFAULT_SPEED_PERCENT = 0.65;
    public static final double TOWER_DEFAULT_SPEED_PERCENT = 0.5;
    public static final double INTAKE_DEFAULT_SPEED_PERCENT = 0.5;
}