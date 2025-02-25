package org.firstinspires.ftc.teamcode.Opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsytems.JarlsArm;
import org.firstinspires.ftc.teamcode.Subsytems.Odometry;

@TeleOp(name = "YARRRRRG")
public class CompetitionJarlsbergianTeleop extends OpMode {

    Drivetrain drivetrain;
    Odometry odo;
    JarlsArm arm;

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
        odo = new Odometry(hardwareMap);
        arm = new JarlsArm(hardwareMap);
        odo.resetEncoders();
    }

    @Override
    public void loop() {
        drivetrain.GamepadInputs(odo.cur0, gamepad1);
        odo.updateCurPos();
        arm.gamepadInputs(gamepad1);

        odo.gamepadInputs(gamepad1);

        //arm.leftWrist.setPosition(0.5);
        //arm.rightWrist.setPosition(0.5);

        telemetry.addData("Rotation", Math.toDegrees(odo.cur0));
        telemetry.addData("X Value", odo.Xc);
        telemetry.addData("Y Value", odo.Xp);
        telemetry.addData("odoback", odo.Cn3);

        telemetry.addData("odoright", odo.odoRight.getCurrentPosition());
        telemetry.addData("odoleft", odo.odoLeft.getCurrentPosition());
        telemetry.addData("odoback", odo.odoBack.getCurrentPosition());


        //telemetry.addData("wrist", arm.leftWrist.getPosition());
        telemetry.addData("oooooh", gamepad1.dpad_up);
        telemetry.addData("shenanigans", arm.leftWrist.getPosition());
        telemetry.addData("CLAWWWW", arm.clawPow);

        telemetry.addData("curx", odo.curX);
        telemetry.addData("cury", odo.curY);

        telemetry.addData("elbowpower", arm.elbowPos);

    }
}
