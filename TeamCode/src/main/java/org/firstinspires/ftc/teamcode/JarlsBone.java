package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.vision.apriltag.AprilTagCanvasAnnotator;

public class JarlsBone {

    //Hardware Init

    //Motors
    DcMotorEx RM;
    DcMotorEx LM;
    //Forearm Servo
    ServoImplEx Forearm_RS;
    ServoImplEx Forearm_LS;
    //Linear slide Sevro
    ServoImplEx FINGER_RS;
    ServoImplEx FINGER_LS;
    double[] endpPos;
    double[] endpTransl;
    //The default position of the arms shouldn't be 0 rad, offset.
    double AangleDefault;
    double BangleDefault;
    double alpha;
    double beta;
    double arm1L;
    double arm2L;
    boolean armConfig;


    JarlsBone(HardwareMap hm){
        RM = hm.get(DcMotorEx.class, "armMotor");
        LM = hm.get(DcMotorEx.class, "Odometry_Pod_Right");
        Forearm_RS= hm.get(ServoImplEx.class, "Right_Servo_Finger");
        Forearm_LS= hm.get(ServoImplEx.class, "Left_Servo_Finger");
        FINGER_RS = hm.get(ServoImplEx.class, "Right_Servo_Forearm");
        FINGER_LS = hm.get(ServoImplEx.class, "Left_Servo_Forearm");

        FINGER_LS.setDirection(ServoImplEx.Direction.REVERSE);

        RM.setDirection(DcMotorEx.Direction.REVERSE);
        LM.setDirection(DcMotorEx.Direction.REVERSE);

        RM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Position of the endpoint at minimum physical rotation
        //Inaccurate and crudely measured but should be precise enough
        endpPos = new double[]{246.0, 220.0};
        AangleDefault = 0.881749;
        BangleDefault = 0.853496;
        arm1L = 432;
        arm2L = 338;
        boolean armConfig = true;
    }

    public void gamepadInputsArm(Gamepad gmpad) {
        //convert boolean to float (using ternary operator)
        //and add to endpoint position
        endpPos[0] += gmpad.dpad_right ? 1 : 0;
        endpPos[0] -= gmpad.dpad_left ? 1 : 0;

        endpPos[1] += gmpad.dpad_up ? 1 : 0;
        endpPos[1] -= gmpad.dpad_down ? 1 : 0;

        //!!! atan2() is (y,x), NOT (x,y) !!! //

        double distance = Math.sqrt(Math.pow(endpPos[0], 2) + Math.pow(endpPos[1], 2));
        double Cangle = Math.atan2(endpPos[1], endpPos[0]);

        //translated endpoint logic
        if (distance > Math.abs(arm2L - arm1L) && distance <= arm1L + arm2L) {
            endpTransl = endpPos;
        } else if (distance < Math.abs(arm2L - arm1L)) {
            //flip cos and sin because atan2's coord arguments are reversed (y,x)
            endpTransl[0] = Math.abs(arm2L - arm1L) * Math.cos(Cangle);
            endpTransl[1] = Math.abs(arm2L - arm1L) * Math.sin(Cangle);
        } else if (distance > arm1L + arm2L) {
            endpTransl[0] = (arm1L + arm2L) * Math.cos(Cangle);
            endpTransl[1] = (arm1L + arm2L) * Math.sin(Cangle);
        }

        //SSS law of cosines to find missing angles test
        double Aangle = Math.acos(Math.pow(arm1L,2) + Math.pow(distance, 2) - Math.pow(arm2L, 2) / (2 * arm1L * arm2L));
        double Bangle = Math.acos(Math.pow(arm2L,2) + Math.pow(arm1L, 2) - Math.pow(distance, 2) / (2 * arm2L * arm1L));


        //translation into correct servo angles
        alpha = (armConfig ? 1 : 0) * Aangle + Cangle; //parallel to ground.
        beta = Math.PI - Bangle;
    }
}
