package frc.robot;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.Base64;

import frc.robot.subsystems.Drivetrain;

public class RobotMovementRecorder {

    private final Robot robot;
    private final double recordingInterval;
    private final ArrayList<Double> leftMotorOutputs;
    private final ArrayList<Double> rightMotorOutputs;

    private double currentLeftMotorOutput;
    private double currentRightMotorOutput;
    private double outputDelta;

    public RobotMovementRecorder(double interval) {
        robot = Robot.getInstance();
        recordingInterval = interval;
        leftMotorOutputs = new ArrayList<Double>();
        rightMotorOutputs = new ArrayList<Double>();
    }

    public RobotMovementRecorder(double interval, String path) {
        robot = Robot.getInstance();
        recordingInterval = interval;
        String[] parts = path.split(",");
        leftMotorOutputs = decodeLocation(parts[0]);
        rightMotorOutputs = decodeLocation(parts[1]);
    }

    public void record() {
        recordInternal(recordingInterval - outputDelta, robot.getTimeDelta());        
    }

    private void recordInternal(double timeLeft, double timeElapsed) {
        if(timeElapsed < timeLeft) {
            currentLeftMotorOutput += timeElapsed * robot.drivetrain.getLeftMotorOutput();
            currentRightMotorOutput += timeElapsed * robot.drivetrain.getRightMotorOutput();
            outputDelta += timeElapsed;
        }
        else {
            currentLeftMotorOutput += timeLeft * robot.drivetrain.getLeftMotorOutput();
            currentRightMotorOutput += timeLeft * robot.drivetrain.getRightMotorOutput();
            leftMotorOutputs.add(currentLeftMotorOutput);
            rightMotorOutputs.add(currentRightMotorOutput);
            currentLeftMotorOutput = 0;
            currentRightMotorOutput = 0;
            outputDelta = timeElapsed - timeLeft;
            recordInternal(recordingInterval, outputDelta);
        }
    }

    public boolean execute() {
        outputDelta += robot.getTimeDelta();
        int index = (int)(outputDelta / recordingInterval);
        if(index < leftMotorOutputs.size()) {
            robot.drivetrain.drivePercent(leftMotorOutputs.get(index) / recordingInterval, rightMotorOutputs.get(index) / recordingInterval);
            return false;
        }
        else {
            return true;
        }
    }

    public String getStringRepresentation() {
        return encodeLocation(leftMotorOutputs) + "," + encodeLocation(rightMotorOutputs);
    }
    
    private static String encodeLocation(ArrayList<Double> doubleArray) {
        double[] array = new double[doubleArray.size()];
        for(int i = 0; i < array.length; i++) {
            array[i] = doubleArray.get(i);
        }
        return Base64.getEncoder().encodeToString(doubleToByteArray(array));
    }
    private static ArrayList<Double> decodeLocation(String base64Encoded) {
        double[] array = byteToDoubleArray(Base64.getDecoder().decode(base64Encoded));
        ArrayList<Double> list = new ArrayList<Double>(array.length);
        for(int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
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