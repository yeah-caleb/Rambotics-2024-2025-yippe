package org.firstinspires.ftc.teamcode.Subsytems;

public class Pathfinder {

    double curPosX;
    double curPosY;
    double curPosTheta;

    double tarPosX;
    double tarPosY;
    double tarRotation;

    double x;
    double y;
    double theta;

    public void setTarPos(double x, double y, double theta)
    {
        tarPosX = x;
        tarPosY = y;
        tarRotation = theta;
    }

    public void pathfind(double curX, double curY, double curTheta)
    {

        if(curX != tarPosX && curY != tarPosY && curTheta != tarRotation)
        {

            if(curX < tarPosX)
            {
                curX = 1;
            }
            else if(curX)

        }

    }

}
