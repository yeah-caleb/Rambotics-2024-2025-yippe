package org.firstinspires.ftc.teamcode.practice;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "fart")
public class DriverControl extends OpMode {

    public Gamepad garner;

    public DcMotorEx topRight;
    public DcMotorEx topLeft;
    public DcMotorEx bottomLeft;
    public DcMotorEx bottomRight;

    @Override
    public void init() {

        topRight = hardwareMap.get(DcMotorEx.class, "rightFront");
        topLeft = hardwareMap.get(DcMotorEx.class, "leftFront");
        bottomRight = hardwareMap.get(DcMotorEx.class, "rightRear");
        bottomLeft = hardwareMap.get(DcMotorEx.class, "leftRear");

        garner = gamepad1;
    }

    @Override
    public void loop() {


        float y = garner.left_stick_y;
        float x = garner.left_stick_x;

        topRight.setPower(y-x);
        topLeft.setPower(y+x);
        bottomRight.setPower(y+x);
        bottomLeft.setPower(y-x);

        if(garner.a){

            topRight.setPower(0.8);
            topLeft.setPower(0.8);
            bottomRight.setPower(0.8);
            bottomLeft.setPower(0.8);

        }

    }
}
