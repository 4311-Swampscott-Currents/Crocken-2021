package frc.robot;

import java.util.HashMap;

import org.swampscottcurrents.serpentframework.*;

public class MainJoystick extends ConfigurableMultiJoystick {
    public static final String RUN_TEST_MOTOR_BUTTON = "Test Motor Button";
    public static final String SWAP_DRIVE_ORIENTATION_BUTTON = "Swap Driving Orientation Button";

    public static final String X_AXIS = "X Axis";
    public static final String Y_AXIS = "Y Axis";
    public static final String Z_AXIS = "Z Axis";
    public static final String THROTTLE_AXIS = "Throttle Axis";

    /** This function returns a map of all default button bindings.  It should be overridden when defining new behaviors. */
    public HashMap<String, Integer> getDefaultButtonBindings() {
        HashMap<String, Integer> data = super.getDefaultButtonBindings();
        data.put(RUN_TEST_MOTOR_BUTTON, 0);
        return data;
    }

    /** This function returns a map of all default axes bindings.  It should be overridden when defining new behaviors. */
    public HashMap<String, Integer> getDefaultAxes() {
        HashMap<String, Integer> data = super.getDefaultAxes();
        data.put(X_AXIS, 0);
        data.put(Y_AXIS, 1);
        data.put(Z_AXIS, 2);
        data.put(THROTTLE_AXIS, 3);
        return data;
    }
}
