package org.firstinspires.ftc.teamcode.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsytems.JarlsArm;
import org.firstinspires.ftc.teamcode.Subsytems.Odometry;
import org.firstinspires.ftc.teamcode.Subsytems.Pathfinder;
import org.firstinspires.ftc.teamcode.utils.targetDogs;

import java.util.ArrayList;

@Autonomous(name="woahdude")
public class CheetoAuto extends OpMode {

    Drivetrain drivetrain;
    Odometry odo;
    Pathfinder path;
    JarlsArm arm;

    ArrayList<targetDogs> move = new ArrayList<>();

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
        odo = new Odometry(hardwareMap);
        path = new Pathfinder();
        arm = new JarlsArm(hardwareMap);

        odo.resetEncoders();
    }

    @Override
    public void loop() {

        path.sequence(path.targetPositions, odo.curX, odo.curY, odo.cur0, drivetrain);
        drivetrain.autoSetter(path.x,path.y,path.theta);
        drivetrain.coordinateBasedState(odo.cur0);

        odo.updateCurPos();

        arm.state(0,0);

    }

}
