package org.firstinspires.ftc.teamcode.Subsytems;

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

    public void setTarPos(double xp, double yp, double thetap)
    {
        tarPosX = xp;
        tarPosY = yp;
        tarRotation = thetap;
    }

    public void runToTargetPos(double curX, double curY, double curTheta)
    {

        if(curX != tarPosX || curY != tarPosY || curTheta != tarRotation)
        {

            if(Math.abs(curX - tarPosX) <= 100)
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


            if(Math.abs(curY - tarPosY) <= 100)
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

            if(Math.abs(tarRotation-Math.toDegrees(curTheta)) >= 20)
            {
                theta = 0.5;
            }
            else
            {
                theta = 0;
            }

        }
    }

}
