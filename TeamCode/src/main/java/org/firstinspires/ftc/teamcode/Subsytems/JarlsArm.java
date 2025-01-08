package org.firstinspires.ftc.teamcode.Subsytems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class JarlsArm {

    DcMotorEx rightMotor;
    DcMotorEx leftMotor;

    Servo leftElbow;
    Servo rightElbow;

    Servo leftArm;
    Servo rightArm;

    public Servo leftWrist;
    public Servo rightWrist;

    CRServo claw;

    double elbowPos;
    double forearmPos;
    double wristPos;
    double clawPow;

    public JarlsArm(HardwareMap hwMap){
        rightMotor = hwMap.get(DcMotorEx.class, "rightArm");
        leftMotor = hwMap.get(DcMotorEx.class, "leftArm");

        rightElbow = hwMap.get(Servo.class, "RElb");
        leftElbow = hwMap.get(Servo.class, "LElb");
        rightArm = hwMap.get(Servo.class, "RLink");
        leftArm = hwMap.get(Servo.class, "LLink");
        rightWrist = hwMap.get(Servo.class, "RWrist");
        leftWrist = hwMap.get(Servo.class, "LWrist");

        claw = hwMap.get(CRServo.class, "Claw");

        rightElbow.setDirection(Servo.Direction.REVERSE);
        rightWrist.setDirection(Servo.Direction.REVERSE);

        rightMotor.setDirection(DcMotorEx.Direction.REVERSE);

        rightArm.setPosition(0.5);
        leftArm.setPosition(0.5);

    }


    public void gamepadInputs(Gamepad gmpad){

        double uppies = gmpad.left_trigger - gmpad.right_trigger;

        leftMotor.setPower(uppies);
        rightMotor.setPower(uppies);

        if(gmpad.left_bumper){
            elbowPos += 0.05;
        }
        if(gmpad.right_bumper){
            elbowPos -= 0.05;
        }

        if(gmpad.dpad_up){
            wristPos += 0.05;
        }
        else if(gmpad.dpad_down){
            wristPos -= 0.05;
        }

        //if(gmpad.a){

        //}

        leftElbow.setPosition(elbowPos);
        rightElbow.setPosition(elbowPos);

        rightWrist.setPosition(0.5);
        leftWrist.setPosition(0.5);

        //claw.setPower(1);


    }

}
