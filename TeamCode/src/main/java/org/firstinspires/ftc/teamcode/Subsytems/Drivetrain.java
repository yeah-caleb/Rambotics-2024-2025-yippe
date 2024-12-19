package org.firstinspires.ftc.teamcode.Subsytems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {

    /* This is the class used to control the functionality of the Drivetrain.
     It controls the Mecanum Wheels and has functions for Field Centric Movement*/

    // Motor Initialization
    DcMotorEx RMFront = null;
    DcMotorEx LMFront = null;
    DcMotorEx RMBack = null;
    DcMotorEx LMBack = null;

    /*Variables used for Autonomus moving stats*/
    double Xmov = 0.0;
    double Ymov = 0.0;
    double rXmov = 0.0;

    // Teleop Moving Stats
    double x;
    double y;

    //Other Variables
    double max;

    public Drivetrain(HardwareMap hwMap)
    {
        RMFront = hwMap.get(DcMotorEx.class, "rightFront");
        LMFront = hwMap.get(DcMotorEx.class, "leftFront");
        RMBack = hwMap.get(DcMotorEx.class, "rightRear");
        LMBack = hwMap.get(DcMotorEx.class, "leftRear");

        RMFront.setDirection(DcMotorEx.Direction.REVERSE);
        LMBack.setDirection(DcMotorEx.Direction.REVERSE);

        RMFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LMFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RMBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LMBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // Functions for Autonomus field centric movement

    public void translate(double xp, double yp, double thetap, double rotation)
    {

        Xmov = (xp * Math.cos(rotation) - (yp * Math.sin(rotation)));
        Ymov = (xp * Math.sin(rotation) + (yp * Math.cos(rotation)));
        rXmov = thetap;

    }

    // State functions. These should Always go inside the Loop() function of an opmode

    //This one is for teleop
    public void GamepadInputs(double rotation, Gamepad gmpad){

        float xt = gmpad.left_stick_x;
        float yt = -gmpad.left_stick_y;
        float rx = gmpad.right_stick_x;

        x = (xt * Math.cos(rotation) - (yt * Math.sin(rotation)));
        y = (xt * Math.sin(rotation) + (yt * Math.cos(rotation)));

        double leftFrontPower = y+x+rx;
        double rightFrontPower = y-x-rx;
        double leftBackPower = y-x+rx;
        double rightBackPower = y+x-rx;

        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }

        LMFront.setPower(leftFrontPower);
        RMFront.setPower(rightFrontPower);
        LMBack.setPower(leftBackPower);
        RMBack.setPower(rightBackPower);

    }

    // This one is for auto
    public void coordinateBasedState()
    {
        RMFront.setPower(-Xmov+Ymov-rXmov);
        LMFront.setPower(Xmov+Ymov+rXmov);
        RMBack.setPower(Xmov+Ymov-rXmov);
        LMBack.setPower(-Xmov+Ymov+rXmov);
    }

    // Tool functions
    public void HALT(){
        Xmov = 0;
        Ymov = 0;
        rXmov = 0;
    }





}
