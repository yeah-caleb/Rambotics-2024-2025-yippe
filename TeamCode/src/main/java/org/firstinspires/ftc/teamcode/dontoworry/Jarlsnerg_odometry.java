package org.firstinspires.ftc.teamcode.dontoworry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsytems.Odometry;

@Autonomous
public class Jarlsnerg_odometry extends OpMode{

    Drivetrain drivetrain;
    Odometry odom;

    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);
        odom = new Odometry(hardwareMap);

        odom.setTargetPos(400, 0, 90);
        odom.resetEncoders();



    }

    public void driveToTarPos(Odometry odop) {
            if (odop.cur0 != odop.tar0) {
                drivetrain.turn0Clockwise(true);
            }
            else{
                drivetrain.rXmov = 0;
            }
            if (odop.curX != odop.tarX) {
                if (odop.curX < odop.tarX) {
                    drivetrain.moveX(true, odop.cur0);
                }
                else if(odop.curX > odop.tarX){
                    drivetrain.moveX(false, odop.cur0);
                }
                else{
                    drivetrain.Xmov = 0;
                }
            }
            if (odop.curY != odop.tarY) {
                if (odop.curY < odop.tarY) {
                    drivetrain.moveY(true, odop.cur0);
                }
                else if (odop.curY > odop.tarY) {
                    drivetrain.moveY(false, odop.cur0);
                }
                else{
                    drivetrain.Ymov = 0;
                }
            }

        if(odop.curPosIsTarPos()) {
            drivetrain.HALT();
        }
    }

    @Override
    public void loop() {

        telemetry.addData("X target", odom.tarX);
        odom.updateCurPos();
        drivetrain.coordinateBasedState();
        driveToTarPos(odom);
    }

}
