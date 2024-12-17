package org.firstinspires.ftc.teamcode.dontoworry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsytems.Drivetrain;

@TeleOp(name = "Parent's Teleop")
public class Yuvisberg extends OpMode {

    Drivetrain drivetrain;

    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);

    }

    @Override
    public void loop() {

        drivetrain.GamepadInputs(0, gamepad1);

    }
}
