package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsytems.Odometry;
import org.firstinspires.ftc.teamcode.Subsytems.Pathfinder;
import org.firstinspires.ftc.teamcode.utils.targetDogs;

import java.util.ArrayList;

@Autonomous(name="YarlsbergCanRun")
public class CompeitionAutonomus extends OpMode {

    Drivetrain drivetrain;
    Odometry odo;
    Pathfinder path;

    ArrayList<targetDogs> sequance = new ArrayList<>();


    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);
        odo = new Odometry(hardwareMap);
        path = new Pathfinder();
        odo.resetEncoders();

        path.targetPositions.add(new targetDogs(500, 300, 0));
        path.targetPositions.add(new targetDogs(100,100,0));
        //path.setTarPos(-500,300,0);

        //sequance.add(new targetDogs(200,100,0));
        //sequance.add(new targetDogs(50, 50, 0));
    }

    @Override
    public void loop() {

        odo.updateCurPos();

        path.sequence(path.targetPositions, odo.curX, odo.curY, odo.cur0);
        drivetrain.autoSetter(path.x,path.y,path.theta);
        drivetrain.coordinateBasedState(odo.cur0);

        telemetry.addData("At Target Pos?", path.isAtTarPos);

        telemetry.addData("Current X", odo.curX);
        telemetry.addData("Current Y", odo.curY);

        telemetry.addData("Current Path", path.count);



        /*
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

        telemetry.addData("pill", path.godotPilled);
    //    telemetry.addData("tarpos", path.is);

*/

    }

}
