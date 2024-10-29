package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.OpMode.DriveEngine.MecanumDrive;

import Subsystems.Claw;
import Subsystems.ClawArm;
import Subsystems.Lift;

import UtilityClasses.Controller;

@TeleOp(name = "New Woboto Tele", group = "TeleOp")
public class NewWobotoTele extends LinearOpMode {
    private MecanumDrive drive;
    private Lift lift;
    private ClawArm arm;
    private Claw claw;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap, 0);
        lift = new Lift(hardwareMap);
        arm = new ClawArm(hardwareMap);
        claw = new Claw(hardwareMap);

        waitForStart();

        ElapsedTime endGameTimer = new ElapsedTime();

        while (opModeIsActive()) {

            if (gamepad1.left_trigger > 0.1) {
                drive.slowMode();
            }


            drive.moveWithPower(
                        gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x,
                        gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x,
                        gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x,
                        gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x
                );
            }
        }
    }
/*
            //Lift and arm control
            if (con2.leftTriggerHeld) {
                if (lift.getPositionLeft() <= 0 || lift.getPositionRight() <= 0) {
                    arm.setTurrentPower(con2.leftTrigger * 0.5);
                    lift.brake();
                } else {
                    lift.setPower(-con2.leftTrigger * 0.5);
                    arm.setTurrentPower(0);
                }
            } else if (con2.rightTriggerHeld) {
                if (arm.turrentIn()) {
                    lift.setPower(con2.rightTrigger * 0.5);
                    arm.setTurrentPower(0);
                } else {
                    arm.setTurrentPower(-con2.rightTrigger * 0.5);
                    lift.brake();
                    arm.flipWrist();
                }
            } else {
                lift.brake();
                arm.setTurrentPower(0);
            }

            //Claw control
            if (con2.aPressed) {
                arm.flipElbow();
            }

            if (con2.bPressed) {
                claw.setPosition(claw.getPosition() == Claw.CLOSE_POSITION
                        ? Claw.OPEN_POSITION : Claw.CLOSE_POSITION);
            }

            telemetry.addData("Right Servo", arm.getRightArmPos());
            telemetry.addData("Left Servo", arm.getLeftArmPos());
            telemetry.addData("Lift Position", lift.getPositionLeft());
            telemetry.addData("Drive Powers", drive.getPowers());
            telemetry.update();

            if (endGameTimer.seconds() >= 90) {
                con1.rumble(3);
                con2.rumble(3);
                endGameTimer.reset();
            }
        }
    }
}
*/