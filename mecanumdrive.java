package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Sample_TeleOp", group="TeleOp")
public class Sample_TeleOp extends OpMode {

    private DcMotor leftFront, rightFront;
    private DcMotor leftRear, rightRear;
    private double theta;

    @Override
    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.FORWARD);
        rightRear.setDirection(DcMotor.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        double power = gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;


        if (turn > 0) {
            theta = -Math.PI / 2; // Quẹo phải
        } else if (turn < 0) {
            theta = Math.PI / 2; // Quẹo trái
        } else {
            theta = 0; // Không quẹo
        }

        double sin = Math.sin(theta - Math.PI / 4);
        double cos = Math.cos(theta - Math.PI / 4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));


        double leftFrontPower = (power * cos / max) + turn;
        double rightFrontPower = (power * sin / max) - turn;
        double leftRearPower = (power * sin / max) + turn;
        double rightRearPower = (power * cos / max) - turn;


        leftFront.setPower(Range.clip(leftFrontPower, -1.0, 1.0));
        rightFront.setPower(Range.clip(rightFrontPower, -1.0, 1.0));
        leftRear.setPower(Range.clip(leftRearPower, -1.0, 1.0));
        rightRear.setPower(Range.clip(rightRearPower, -1.0, 1.0));
    }
}