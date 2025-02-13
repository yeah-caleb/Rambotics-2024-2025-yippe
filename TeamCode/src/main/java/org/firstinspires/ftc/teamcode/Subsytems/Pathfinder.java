package org.firstinspires.ftc.teamcode.Subsytems;

import org.firstinspires.ftc.teamcode.utils.targetDogs;

import java.util.ArrayList;

public class Pathfinder {

    public double tarPosX;
    public double tarPosY;
    public double tarRotation;

    public double x;
    public double y;
    public double theta = 0.0;

    public double buffer = 20;

    public boolean isAtTarPos = false;

    public ArrayList<targetDogs> targetPositions = new ArrayList<targetDogs>();

    // iterator variables
    public int count = 0;

    public void setTarPos(double xp, double yp, double thetap)
    {
        tarPosX = xp;
        tarPosY = yp;
        tarRotation = thetap;
    }

    public void runToTargetPos(double curX, double curY, double curTheta, double tarX, double tarY, double tarTheta)
    {

        if(Math.abs(curX - tarX) <= buffer && Math.abs(curY - tarY) <= buffer)
        {
            isAtTarPos = true;

            x = 0;

            y = 0;

        }
        else{
            isAtTarPos = false;

            if(Math.abs(curX - tarX) <= buffer)
            {
                x = 0;
            }
            else if(curX < tarX)
            {
                x = 0.5;
            }
            else if(curX > tarX)
            {
                x = -0.5;
            }


            if(Math.abs(curY - tarY) <= buffer)
            {
                y = 0;
            }
            else if(curY < tarY)
            {
                y = 0.5;
            }
            else if(curY > tarY)
            {
                y = -0.5;
            }

            /*
            if(Math.abs(tarRotation-Math.toDegrees(curTheta)) >= 20)
            {
                theta = 0.5;
            }
            else
            {
                theta = 0;
            }

             */
        }
    }

    public void sequence(ArrayList<targetDogs> targets, double curX, double curY, double curTheta, Drivetrain drive){
       // setTarPos(targets.get(count).x, targets.get(count).y, targets.get(count).theta);

        if(!targets.get(count).precision){
            drive.precisionMode = false;
        }
        else {
            drive.precisionMode = true;
        }

        runToTargetPos(curX, curY, curTheta, targets.get(count).x, targets.get(count).y, targets.get(count).theta);

        if(isAtTarPos && count < targets.size()){
            count+=1;
        }
    }



    /*
    public void sequence(ArrayList<targetDogs> targetsp, double curx, double cury, double curTheta){
        if(godotPilled < targetsp.size()) {
            setTarPos(targetsp.get(godotPilled).x, targetsp.get(godotPilled).y, targetsp.get(godotPilled).theta);
        }
            if(!isAtTarPos){
                runToTargetPos(curx, cury, curTheta);
            }
            else{
                godotPilled += 1;
            }
    }
*/
}
