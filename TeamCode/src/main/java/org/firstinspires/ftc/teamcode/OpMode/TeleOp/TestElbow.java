package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.OpMode.DriveEngine.MecanumDrive;

import Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawArm;

import org.firstinspires.ftc.teamcode.UtilityClasses.Controller;

@TeleOp(name = "Test Elbow", group = "TeleOp")
public class TestElbow extends LinearOpMode {
    private MecanumDrive drive;
    private ClawArm arm;
    private Claw claw;


    @Override
    public void runOpMode() throws InterruptedException {
        Servo servoLeft = hardwareMap.get(Servo.class, "leftArm");
        Servo servoRight = hardwareMap.get(Servo.class, "rightArm");
        arm = new ClawArm(hardwareMap);
        claw = new Claw(hardwareMap);

        Controller con1 = new Controller(gamepad1);
        Controller con2 = new Controller(gamepad2);

        double servoLeft_MinLimit = 0;
        double servoLeft_MaxLimit = 1;
        double servoRight_MinLimit = 0;
        double servoRight_MaxLimit = 1;
        double positionR = 0, positionL = 0;

        waitForStart();

        ElapsedTime endGameTimer = new ElapsedTime();

        while (opModeIsActive()) {
            con1.update();
            con2.update();

            /*if (con1.aPressed) {
                arm.flipElbow();
            }*/
            if (con1.aPressed) {
                positionL = positionL == 0 ? 1 : 0;
                servoLeft.setPosition(((positionL) * servoLeft_MinLimit) + ((1-positionL)* servoLeft_MaxLimit));

                positionR = positionR == 0 ? 1 : 0;
                servoRight.setPosition(((1 - positionR) * servoRight_MinLimit) + (positionR * servoRight_MaxLimit));
            }

            telemetry.addData("Right Servo", arm.getRightArmPos());
            telemetry.addData("Left Servo", arm.getLeftArmPos());
            telemetry.update();
        }
    }
}
