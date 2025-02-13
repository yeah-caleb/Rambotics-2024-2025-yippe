package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsytems.JarlsArm;
import org.firstinspires.ftc.teamcode.Subsytems.Odometry;
import org.firstinspires.ftc.teamcode.Subsytems.Pathfinder;
import org.firstinspires.ftc.teamcode.utils.targetDogs;

import java.util.ArrayList;

@Autonomous(name="YarlsbergCanRun")
public class CompeitionAutonomus extends OpMode {

    Drivetrain drivetrain;
    Odometry odo;
    Pathfinder path;
    JarlsArm arm;

    ArrayList<targetDogs> sequance = new ArrayList<>();


    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);
        odo = new Odometry(hardwareMap);
        path = new Pathfinder();
        arm = new JarlsArm(hardwareMap);

        odo.resetEncoders();

        path.targetPositions.add(new targetDogs(1200, 0, 0, true));
        path.targetPositions.add(new targetDogs(1200,-140,0, true));
        path.targetPositions.add(new targetDogs(100,-140,0, false));

        path.targetPositions.add(new targetDogs(1200,-140,0, true));
        path.targetPositions.add(new targetDogs(1200,-250,0, true));
        path.targetPositions.add(new targetDogs(150,-250,0, false));

        path.targetPositions.add(new targetDogs(1200,-250,0, true));
        path.targetPositions.add(new targetDogs(1200,-420,0, true));
        path.targetPositions.add(new targetDogs(200,-420,0, false));

    }

    @Override
    public void loop() {

        path.sequence(path.targetPositions, odo.curX, odo.curY, odo.cur0, drivetrain);
        drivetrain.autoSetter(path.x,path.y,path.theta);
        drivetrain.coordinateBasedState(odo.cur0);

        odo.updateCurPos();

        arm.state(0, 0);

        telemetry.addData("At Target Pos?", path.isAtTarPos);

        telemetry.addData("Current X", odo.curX);
        telemetry.addData("Current Y", odo.curY);

        telemetry.addData("Current Path", path.count);

        telemetry.addData("Targets List Size", path.targetPositions.size());

        telemetry.addData("Target Y", path.targetPositions.get(path.count).y);
        telemetry.addData("Target X", path.targetPositions.get(path.count).x);

        telemetry.addData("Target Positions X Double check", path.tarPosX);
        telemetry.addData("Target Positions Y Double check", path.tarPosY);

    }

}
