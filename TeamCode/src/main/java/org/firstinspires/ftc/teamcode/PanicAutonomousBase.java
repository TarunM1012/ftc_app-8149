package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utils.MecanumDrive;

public class PanicAutonomousBase extends LinearOpMode {
    static int SERVO_DEGREES = 180;
    MecanumDrive drive = new MecanumDrive();
    boolean isFar = false;
    Servo gemSensorArm;
    ColorSensor gemSensor;
    teamColor currentTeam, detectedBall;

    @Override
    public void runOpMode() throws InterruptedException {
        drive.InitMotors(hardwareMap); // Init Drive Train

        // Other Init
        gemSensorArm = hardwareMap.servo.get("Gem Arm Servo");
        gemSensor = hardwareMap.colorSensor.get("Gem Sensor");
        gemSensorArm.setPosition(20 / SERVO_DEGREES);

//        drive.motorRightA = hardwareMap.dcMotor.get("motorRightA");
//        drive.motorRightB = hardwareMap.dcMotor.get("motorRightB");
//        drive.motorLeftA = hardwareMap.dcMotor.get("motorLeftA");
//        drive.motorLeftB = hardwareMap.dcMotor.get("motorLeftB");

        // Show the viewforia camera!


        waitForStart(); // Wait for the start

        // Step 1a. Lower the arm.
        gemSensorArm.setPosition(154 / SERVO_DEGREES);
        sleep(2000);

        // Step 1b. Figure out the color of the ball on the left side.
        if (gemSensor.red() >= 100) {
            detectedBall = teamColor.red;
        } else if (gemSensor.blue() >= 100) {
            detectedBall = teamColor.blue;
        }

        // Step 1c. Twist and stop!

        if (detectedBall == currentTeam) {
            drive.update(0, 0, -0.5);
            sleep(1000);
            drive.stop();
        } else if (detectedBall != currentTeam) {
            drive.update(0, 0, 0.5);
            sleep(1000);
            drive.stop();
        }

    }

    enum teamColor {red, blue}


}
