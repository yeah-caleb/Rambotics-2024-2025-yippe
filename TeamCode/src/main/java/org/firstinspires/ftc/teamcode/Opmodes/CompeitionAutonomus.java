package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsytems.Odometry;
import org.firstinspires.ftc.teamcode.Subsytems.Pathfinder;

@Autonomous(name="YarlsbergCanRun")
public class CompeitionAutonomus extends OpMode {

    Drivetrain drivetrain;
    Odometry odo;
    Pathfinder path;

    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);
        odo = new Odometry(hardwareMap);
        path = new Pathfinder();
        odo.resetEncoders();

        path.setTarPos(-200,-300,0);

    }

    @Override
    public void loop() {

        odo.updateCurPos();
        path.runToTargetPos(odo.curX,odo.curY,odo.cur0);
        drivetrain.autoSetter(path.x, path.y, path.theta);
        drivetrain.coordinateBasedState(odo.cur0);

        //drivetrain.shit();

        telemetry.addData("X Value", path.x);
        telemetry.addData("Y Value", path.y);
        telemetry.addData("rx Value", path.theta);

        telemetry.addData("tarPos X", path.tarPosX);
        telemetry.addData("tarPos Y", path.tarPosY);

        telemetry.addData("curX Value", odo.curX);
        telemetry.addData("curY Value", odo.curY);
        telemetry.addData("currx Value", odo.cur0);

        telemetry.addData("help", Math.abs(path.tarRotation-odo.cur0));
        telemetry.addData("help2", Math.toDegrees(odo.cur0));

    }
}
