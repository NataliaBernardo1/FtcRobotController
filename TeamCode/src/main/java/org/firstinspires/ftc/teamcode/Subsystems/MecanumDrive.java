package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


//import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

@TeleOp
public class MecanumDrive {
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    public MecanumDrive(HardwareMap hardwareMap) {
    }

    public void init(Path hardwareMap) {
        //frontLeftMotor = hardwareMap.DcMotor.get("frontLeftDriveMotor");
        //frontRightMotor = hardwareMap.DcMotor.get("frontRightDriveMotor");
        //backLeftMotor = hardwareMap.DcMotor.get("backLeftDriveMotor");
        //backRightMotor = hardwareMap.DcMotor.get("backRightDriveMotor");

        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void setPowers(double frontLeftPower, double frontRightPower, double
            backLeftPower, double backRightPower) {
        double maxSpeed = 1.0;
        maxSpeed = Math.max(maxSpeed, Math.abs(frontLeftPower));
        maxSpeed = Math.max(maxSpeed, Math.abs(frontRightPower));
        maxSpeed = Math.max(maxSpeed, Math.abs(backLeftPower));
        maxSpeed = Math.max(maxSpeed, Math.abs(backRightPower));

        frontLeftPower /= maxSpeed;
        frontRightPower /= maxSpeed;
        backRightPower /= maxSpeed;
        backLeftPower /= maxSpeed;

        frontLeftMotor.setPower(frontLeftPower/5);
        frontRightMotor.setPower(frontRightPower/5);
        backLeftMotor.setPower(backLeftPower/5);
        backRightMotor.setPower((backRightPower/5));

    }

    public void drive(double forward, double right, double rotate) {
        double frontLeftPower = forward + right + rotate;
        double frontRightPower = forward - right - rotate;
        double backRightPower = forward + right - rotate;
        double backLeftPower = forward - right + rotate;
        setPowers(frontLeftPower, frontRightPower, backRightPower, backLeftPower);
    }

    public void setMode(DcMotor.RunMode runWithoutEncoder) {
    }

    public Subsystems.AutoTransferPose.Trajectory trajectoryBuilder(Pose2d pose2d) {
        return null;
    }

    public void setPoseEstimate(Subsystems.AutoTransferPose.Pose2d startPose) {
    }

    public void followTrajectory(Trajectory traj) {
    }

    public void followTrajectory(Subsystems.AutoTransferPose.Trajectory traj) {
    }

    public Subsystems.AutoTransferPose.Trajectory trajectoryBuilder(Subsystems.AutoTransferPose.Pose2d startPose) {
        return null;
    }

    private class Trajectory {
    }

    public class Pose2d {
        public double getX() {
            return 0;
        }

        public double getY() {
            return 0;
        }
    }

    private class Path {
    }

    //public Object getPoseEstimate() {
   //    return poseEstimate;
    }

    //public BaseTrajectoryBuilder<T> trajectoryBuilder(a a, a a) {
    //}
//}
