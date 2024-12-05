package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.OpMode.DriveEngine.MecanumDrive;

import Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawArm;

import org.firstinspires.ftc.teamcode.Subsystems.Lift;
import org.firstinspires.ftc.teamcode.UtilityClasses.Controller;

@TeleOp(name = "New Woboto Tele", group = "TeleOp")
public class NewWobotoTele extends LinearOpMode {
    private MecanumDrive drive;
    private Lift lift;
    private ClawArm arm;
    private Claw claw;
    private Controller controller;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap, 0);

        Servo servoLeft = hardwareMap.get(Servo.class, "leftArm");
        Servo servoRight = hardwareMap.get(Servo.class, "rightArm");
        arm = new ClawArm(hardwareMap);
        lift = new Lift(hardwareMap);
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

            if (gamepad1.left_trigger > 0.1) {
                drive.slowMode();
            }


            drive.moveWithPower(
                    -gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x,
                    -gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x,
                    -gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x,
                    -gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x

            );

            if (gamepad2.right_trigger != 0 && (lift.getPositionLeft() < 3600 ||
                    lift.getPositionRight() < 3600)) {
                lift.setPower(-gamepad2.right_trigger);}
            else if (gamepad2.left_trigger != 0 && (lift.getPositionLeft() > 0 ||
                    lift.getPositionRight() > 0)){
                lift.setPower(gamepad2.left_trigger);
                }

            //Lift and arm control
            //lift.setPower(gamepad2.right_trigger - gamepad2.left_trigger);
            /*if (gamepad2.left_trigger != 0) {
                if (lift.getPositionLeft() <= 15 || lift.getPositionRight() <= 15) {
                    arm.setTurrentPower(0.5);
                    arm.setPositionElbow(0.5);
                    lift.brake();
                } else {
                    lift.setPower(-0.5);
                    arm.setTurrentPower(0);
                }
            } else if (gamepad2.right_trigger != 0 && (lift.getPositionLeft() < 3600 || lift.getPositionRight() < 3600)) {
                lift.setPower(.5);
            }else if (gamepad2.right_trigger != 0) {
            if (arm.turrentIn()) {
                lift.setPower(0.5);
                arm.setTurrentPower(0);
            } else {
                arm.setTurrentPower(-0.5);
                lift.brake();
                arm.flipWrist();
            } else {
                //lift.brake();
                arm.setTurrentPower(0);*/
            }

            //Claw control
            /*if (con2.aPressed) {
                positionL = positionL == 0 ? 1 : 0;
                servoLeft.setPosition(((positionL) * servoLeft_MinLimit) + ((1-positionL)* servoLeft_MaxLimit));

                positionR = positionR == 0 ? 1 : 0;
                servoRight.setPosition(((1 - positionR) * servoRight_MinLimit) + (positionR * servoRight_MaxLimit));
            }*/
            if (con2.rightBumperPressed) {
                arm.setPositionElbow(1);
            } else if (con2.leftBumperPressed) {
                arm.setPositionElbow(0);
                arm.flipWrist();
            }
            if (gamepad2.b) {
                claw.setPosition(claw.getPosition() == Claw.CLOSE_POSITION
                        ? Claw.OPEN_POSITION : Claw.CLOSE_POSITION);
            }

            telemetry.addData("Right Servo", arm.getRightArmPos());
            telemetry.addData("Left Servo", arm.getLeftArmPos());
            telemetry.addData("Lift Position", lift.getPositionLeft());
            telemetry.addData("Drive Powers", drive.getPowers());
            telemetry.update();

            if (endGameTimer.seconds() >= 90) {
                gamepad2.rumble(3);
                gamepad2.rumble(3);
                endGameTimer.reset();
            }
        }
    }
