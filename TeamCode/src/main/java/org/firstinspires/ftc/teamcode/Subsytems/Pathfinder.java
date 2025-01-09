package org.firstinspires.ftc.teamcode.Subsytems;

import org.firstinspires.ftc.teamcode.utils.targetDogs;

import java.util.ArrayList;

public class Pathfinder {

    double curPosX;
    double curPosY;
    double curPosTheta;

    public double tarPosX;
    public double tarPosY;
    public double tarRotation;

    public double x;
    public double y;
    public double theta = 0.0;

    public boolean isAtTarPos = false;

    // iterator variables
    public int godotPilled = 0;


    public void setTarPos(double xp, double yp, double thetap)
    {
        tarPosX = xp;
        tarPosY = yp;
        tarRotation = thetap;
    }

    public void runToTargetPos(double curX, double curY, double curTheta)
    {

        if(Math.abs(curX - tarPosX) <= 50 || Math.abs(curY - tarPosY) <= 50 || Math.abs(tarRotation-Math.toDegrees(curTheta)) <= 20)
        {
            isAtTarPos = false;

            if(Math.abs(curX - tarPosX) <= 50)
            {
               x = 0;
            }
            else if(curX < tarPosX)
            {
                x = 0.5;
            }
            else if(curX > tarPosX)
            {
                x = -0.5;
            }


            if(Math.abs(curY - tarPosY) <= 50)
            {
               y = 0;
            }
            else if(curY < tarPosY)
            {
                y = 0.5;
            }
            else if(curY > tarPosY)
            {
                y = -0.5;
            }

            if(Math.abs(tarRotation-Math.toDegrees(curTheta)) <= 20)
            {
                theta = 0.5;
            }
            else
            {
                theta = 0;
            }

        }
        else{
            isAtTarPos = true;
        }
    }

    public void sequence(ArrayList<targetDogs> targetsp, double curx, double cury, double curTheta){
        if(godotPilled <= targetsp.size()){
            setTarPos(targetsp.get(godotPilled).x,targetsp.get(godotPilled).y,targetsp.get(godotPilled).theta);
            if(!isAtTarPos){
                runToTargetPos(curx, cury, curTheta);
            }
            else{
                godotPilled += 1;
            }
        }

        /*
        for(targetDogs target : targetsp){
            setTarPos(target.x, target.y, target.theta);
            if(!isAtTarPos){
                runToTargetPos(curx, cury, curTheta);
            }
            else{

            }
        }
        */

    }

}
