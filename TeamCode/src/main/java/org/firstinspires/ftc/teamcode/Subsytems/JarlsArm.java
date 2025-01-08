package org.firstinspires.ftc.teamcode.Subsytems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class JarlsArm {

    DcMotorEx rightMotor;
    DcMotorEx leftMotor;

    ServoImplEx leftElbow;
    ServoImplEx rightElbow;

    ServoImplEx leftArm;
    ServoImplEx rightArm;

    ServoImplEx leftWrist;
    ServoImplEx rightWrist;

    ServoImplEx claw;

    JarlsArm(HardwareMap hwMap){
        rightMotor = hwMap.get(DcMotorEx.class, "rightArm");
        leftMotor = hwMap.get(DcMotorEx.class, "leftArm");

        rightElbow = hwMap.get(ServoImplEx.class, "RElb");
        leftElbow = hwMap.get(ServoImplEx.class, "LElb");
        rightArm = hwMap.get(ServoImplEx.class, "RLink");
        leftArm = hwMap.get(ServoImplEx.class, "LLink");
        rightWrist = hwMap.get(ServoImplEx.class, "RWrist");
        leftWrist = hwMap.get(ServoImplEx.class, "LWrist");

        claw = hwMap.get(ServoImplEx.class, "claw");
    }


}
