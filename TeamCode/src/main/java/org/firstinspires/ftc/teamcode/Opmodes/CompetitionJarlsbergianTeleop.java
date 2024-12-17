package org.firstinspires.ftc.teamcode.Opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsytems.Odometry;

@TeleOp(name = "YARRRRRG")
public class CompetitionJarlsbergianTeleop extends OpMode {

    Drivetrain drivetrain;
    Odometry odo;
    //CheetoFingers arm;

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
        odo = new Odometry(hardwareMap);
        //arm = new CheetoFingers(hardwareMap);
        odo.resetEncoders();
    }

    @Override
    public void loop() {
        drivetrain.GamepadInputs(odo.cur0, gamepad1);
        //arm.gamepadInputsArm(gamepad1);
        odo.updateCurPos();

        telemetry.addData("Rotation", Math.toDegrees(odo.cur0));
        telemetry.addData("X Value", odo.Xc);
        telemetry.addData("Y Value", odo.Xp);
        telemetry.addData("odoback", odo.Cn3);

        //telemetry.addData("Right Finger", arm.FINGER_RS.getPosition());
        //telemetry.addData("Left Finger", arm.FINGER_LS.getPosition());


    }
}
