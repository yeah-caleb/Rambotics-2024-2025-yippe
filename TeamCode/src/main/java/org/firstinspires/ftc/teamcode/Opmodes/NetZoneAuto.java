package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsytems.Odometry;
import org.firstinspires.ftc.teamcode.Subsytems.Pathfinder;
import org.firstinspires.ftc.teamcode.utils.targetDogs;

import java.util.ArrayList;

@Autonomous(name="Yuvisberg")
public class NetZoneAuto extends OpMode {

    Drivetrain drivetrain;
    Odometry odo;
    Pathfinder path;

    ArrayList<targetDogs> move = new ArrayList<>();

    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);
        odo = new Odometry(hardwareMap);
        path = new Pathfinder();
        odo.resetEncoders();

        move.add(new targetDogs(500,0,0));
        move.add(new targetDogs(0,0,0));

    }

    @Override
    public void loop() {

        odo.updateCurPos();
        path.sequence(move, odo.curX, odo.curY, odo.cur0);

        drivetrain.autoSetter(path.x, path.y, path.theta);
        drivetrain.coordinateBasedState(odo.cur0);
        odo.gamepadInputs(gamepad1);

        telemetry.addData("godotpilled", path.godotPilled);
        telemetry.addData("tarpos????", path.isAtTarPos);
        telemetry.addData("tarpos????", path.wahooo);



    }
}
