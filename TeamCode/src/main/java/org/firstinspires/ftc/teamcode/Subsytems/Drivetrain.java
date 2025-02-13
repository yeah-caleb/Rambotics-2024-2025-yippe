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
    public boolean precisionMode = false;

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

    // Functions for field centric movement

    public void translate(double xp, double yp, double thetap, double rotation)
    {

        Xmov = (xp * Math.cos(rotation) - (yp * Math.sin(rotation)));
        Ymov = (xp * Math.sin(rotation) + (yp * Math.cos(rotation)));
        rXmov = thetap;

    }

    // State functions. These should Always go inside the Loop() function of an opmode

    //This one is for teleop
    public void GamepadInputs(double rotation, Gamepad gmpad){

        if(gmpad.a){
            precisionMode = true;
        }
        if(gmpad.b){
            precisionMode = false;
        }

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

        if(precisionMode == false) {
            LMFront.setPower(leftFrontPower);
            RMFront.setPower(rightFrontPower);
            LMBack.setPower(leftBackPower);
            RMBack.setPower(rightBackPower);
        }
        if(precisionMode == true) {
            LMFront.setPower(leftFrontPower/2);
            RMFront.setPower(rightFrontPower/2);
            LMBack.setPower(leftBackPower/2);
            RMBack.setPower(rightBackPower/2);
        }

    }

    // Auto Setter Function
    public void autoSetter(double xp, double yp, double rXp){
        Xmov = xp;
        Ymov = yp;
        rXmov = rXp;
    }

    // This one is for auto
    public void coordinateBasedState(double cur0p)
    {

        double Xmov2 = (Xmov * Math.cos(cur0p) - (Ymov * Math.sin(cur0p)));
        double Ymov2 = (Xmov * Math.sin(cur0p) + (Ymov * Math.cos(cur0p)));

        double leftFrontPower = Ymov2+Xmov2+rXmov;
        double rightFrontPower = -Ymov2+Xmov2-rXmov;
        double leftBackPower = -Ymov2+Xmov2+rXmov;
        double rightBackPower = Ymov2+Xmov2-rXmov;

        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }

        if(precisionMode == false) {
            LMFront.setPower(leftFrontPower);
            RMFront.setPower(rightFrontPower);
            LMBack.setPower(leftBackPower);
            RMBack.setPower(rightBackPower);
        }
        if(precisionMode == true) {
            LMFront.setPower(leftFrontPower/1.60);
            RMFront.setPower(rightFrontPower/1.60);
            LMBack.setPower(leftBackPower/1.60);
            RMBack.setPower(rightBackPower/1.60);
        }

        /*
        LMFront.setPower(leftFrontPower);
        RMFront.setPower(rightFrontPower);
        LMBack.setPower(leftBackPower);
        RMBack.setPower(rightBackPower);
*/
    }

    // Tool functions
    public void HALT(){
        Xmov = 0;
        Ymov = 0;
        rXmov = 0;
    }

}
