package org.firstinspires.ftc.teamcode.practice;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Teleop (name = BeginnersBlockOpMode)
public class BeginnersBlockOpMode
{
    public Gamepad Blocko;
    Blocko = gamepad1;

    public DcMotorEx rightFront;
    public DcMotorEx leftFront;
    public DcMotorEx rightRear;
    public DcMotorEx leftRear;

    public void init()
    {
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
    }

    public void loop()
    {
        double lx = Blocko.left_stick_x;
        double ly = Blocko.left_stick_y;

        if(lx > 0) {
            rightFront.setPower(-lx);
            leftFront.setPower(lx);
            rightRear.setPower(lx);
            leftRear.setPower(-lx);
        }

        if(ly > 0) {
            rightFront.setPower(ly);
            leftFront.setPower(ly);
            rightRear.setPower(ly);
            leftRear.setPower(ly);
        }

        if(lx < 0) {
            rightFront.setPower(lx);
            leftFront.setPower(-lx);
            rightRear.setPower(-lx);
            leftRear.setPower(lx);
        }

        if(ly < 0) {
            rightFront.setPower(-ly);
            leftFront.setPower(-ly);
            rightRear.setPower(-ly);
            leftRear.setPower(-ly);
        }

        double rx = Blocko.right_stick_x

        if(rx > 0)
        {
            rightFront.setPower(-rx);
            leftFront.setPower(rx);
            rightRear.setPower(-rx);
            leftRear.setPower(rx);
        }

        if(rx < 0)
        {
            rightFront.setPower(rx);
            leftFront.setPower(-rx);
            rightRear.setPower(rx);
            leftRear.setPower(-rx);
        }

    }
}