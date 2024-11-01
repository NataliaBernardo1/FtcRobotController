package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.OpMode.DriveEngine.MecanumDrive;

import Subsystems.Claw;
import Subsystems.ClawArm;
import Subsystems.Lift;
import UtilityClasses.Controller;

@TeleOp (name="WobotoTele", group = "TeleOp")
public class WobotoTele extends LinearOpMode {
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

        Controller con1 = new Controller(gamepad1),
        con2 = new Controller(gamepad2);

        waitForStart();

        ElapsedTime endGameTimer = new ElapsedTime();

        while(opModeIsActive()){
            con1.update();
            con2.update();

            //Slow mode
            if(con1.leftTriggerPressed)
                drive.slowMode();
            else if (con1.leftTriggerReleased)
                drive.slowMode();

            //Robot-oriented movement
            drive.moveWithPower(
                    con1.leftStick.y + con1.leftStick.x + con1.rightStick.x,
                    con1.leftStick.y - con1.leftStick.x + con1.rightStick.x,
                    con1.leftStick.y + con1.leftStick.x - con1.rightStick.x,
                    con1.leftStick.y - con1.leftStick.x - con1.rightStick.x
            );

            //Control of lift/turrent
            if(con2.leftTriggerHeld){ //lift down & turrent out
                if(lift.getPositionLeft() <= 15 || lift.getPositionRight() <= 15){
                    arm.setTurrentPower(con2.leftTrigger * 0.5);
                    //arm.setPositionElbow(1);
                    lift.brake();
                }else {
                    lift.setPower(-con2.leftTrigger*.5);
                    arm.setTurrentPower(0);
                }

            } else if(con2.rightTriggerHeld){ //lift up & turret in
                if(arm.turrentIn()){
                    lift.setPower(con2.rightTrigger*.5);
                    arm.setTurrentPower(0);
                }else {
                    arm.setTurrentPower(-con2.rightTrigger*.5);
                    lift.brake();
                    //arm.setPositionElbow(0);

                    //flips cone as turrent is coming in
                    arm.flipWrist();

                }

            } else {
                lift.brake();
                arm.setTurrentPower(0);
            }

            telemetry.addData("right servo", arm.getRightArmPos());
            telemetry.addData("left servo", arm.getLeftArmPos());
            telemetry.addLine();

            telemetry.addData("Lift position", lift.getPositionLeft());
            telemetry.addData("powers", drive.getPowers());
            telemetry.update();

            //suggestion: add button to load cone onto lift, maybe a button to extend clow outwards, auto cycle button?

            /*if(con2.aPressed){
                arm.flipElbow();
            }
            if(con2.bPressed){
                claw.setPosition(claw.getPosition() == Claw.CLOSE_POSITION
                        ? Claw.OPEN_POSITION : Claw.CLOSE_POSITION);
            }*/

            if (con2.bPressed) {
                claw.setPosition(Claw.CLOSE_POSITION);
            } else {
                claw.setPosition(Claw.OPEN_POSITION);
            }


            if(endGameTimer.seconds() >= 90){
                con1.rumble(3);
                con2.rumble(3);
                endGameTimer.reset();
            }
        }
    }
}
