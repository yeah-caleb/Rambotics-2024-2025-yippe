package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsytems.Odometry;
import org.firstinspires.ftc.teamcode.Subsytems.Pathfinder;

@Autonomous(name="AutoRight")
public class AutoRight extends OpMode {

    Drivetrain drivetrain;
    Odometry odo;
    Pathfinder path;


    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);
        odo = new Odometry(hardwareMap);
        path = new Pathfinder();
        odo.resetEncoders();

        path.setTarPos(100, 300, 0 );


    }

    @Override
    public void loop() {

        odo.updateCurPos();

        path.runToTargetPos(odo.curX,odo.curY,odo.cur0);

        drivetrain.autoSetter(path.x, path.y, path.theta);
        drivetrain.coordinateBasedState(odo.cur0);


    }
}
