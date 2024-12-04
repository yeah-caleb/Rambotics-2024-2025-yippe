package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
   This class creates the odometry pods, tracks measurements from the odometry pods,
   translates the measurements into X, Y, and Theta positions for the robot, and
   it gives tool functions to the odometry pods for various uses in the autonomus
*/

public class Odometry_Info {


    // Odometry Pod Initialization
    DcMotorEx odoRight = null;
    DcMotorEx odoLeft = null;
    DcMotorEx odoBack = null;

    /* THE FOLLOWING IS THE LIST OF THE STATIC VARIABLES, WHAT THEY ARE, AND WHEN
       THEY NEED TO BE CHANGED
    *
    *  OdoTPR - The amount of ticks per revoloution of the odometry pod.
    *           should only ever be changed when the odometry pods are changed out
    *
    *  C - this is the circumfrence of the odometry sensor. its 2 * PI * Radius.
    *      The current radius is 16, this should be changed when odometry pods
    *      are changed, and only the 16 should be changed to the new radius value
    *      and nothing else
    *
    *  L - This is the distance between the two parrallel odometry sensors. This
    *      variable should only be changed when the odometry sensors are moved
    *      (This will probably happen with every iteration of the robot)
    *
    *  B - This is the distance between the perpindicular odometry sensor and the
    *      center of the robot (The center line that is parrallel to the sensor).
    *      This variable should only be changed when the odometry sensors are moved
    *      (This will probably happen with every iteration of the robot)
    *
    * */

    static double odoTPR = 2000.0;
    static double C = 2*Math.PI*16;
    static double L = 190.504;
    static double B = 42.382;

    /* Variables to notate the current positions of the robot*/
    double Xc = 0.0;
    double Xp = 0.0;
    double Theta0 = 0.0;

    double curX = 0.0;
    double curY = 0.0;
    double cur0 = 0.0;

    /*Target Position Variables*/
    double tarX = 0.0;
    double tarY = 0.0;
    double tar0 = 0.0;

    /* Distance Variables for the odo in MM. 1 = right, 2 = left, 3 = back */
    double Cn1 = 0.0;
    double Cn2 = 0.0;
    double Cn3 = 0.0;


    // Constructor (You should know that)
    Odometry_Info(HardwareMap hwMap){
        odoRight = hwMap.get(DcMotorEx.class, "Odometry_Pod_Right");
        odoLeft = hwMap.get(DcMotorEx.class, "Odometry_Pod_Left");
        odoBack = hwMap.get(DcMotorEx.class, "Odometry_Pod_Back");

        odoRight.setDirection(DcMotorEx.Direction.REVERSE);
        odoLeft.setDirection(DcMotorEx.Direction.REVERSE);
    }

    /* This is the state function, should be placed in loop. This is a lot of math
    on Odometry, a good resource to use to understand the basics of Odometry is
    this game manual: https://gm0.org/en/latest/docs/software/concepts/odometry.html */

    public void updateCurPos(){

        Cn1 = C*(odoRight.getCurrentPosition()/odoTPR);
        Cn2 = C*(odoLeft.getCurrentPosition()/odoTPR);
        Cn3 = C*(odoBack.getCurrentPosition()/odoTPR);

        Xc = ((Cn1+Cn2)/2);
        Theta0 = cur0;
        cur0 = ((Cn1-Cn2)/L);
        Xp = (Cn3 - (B*cur0));

        curX = (Xc*Math.cos(Theta0) - Xp*Math.sin(Theta0));
        curY = (Xc*Math.sin(Theta0) + Xp*Math.cos(Theta0));
    }

    /* Tool Functions */

    public void setTargetPos(double x, double y, double O){
        tarX = x;
        tarY = y;
        tar0 = O;
    }

    public boolean curPosIsTarPos() {
        if (curX != tarX && curY != tarY && cur0 != tar0) {
            return false;
        }
        else{
            return true;
        }
    }

    /* the RUN_WITHOUT_ENCODERS runmode isn't turning off the encoders,
    its making the motors move based on power inputs and not encoder inputs */
    public void resetEncoders(){
        odoLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        odoRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        odoBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        odoLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        odoRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        odoBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

}
