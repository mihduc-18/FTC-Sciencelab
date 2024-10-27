package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class test extends OpMode {

    DcMotor frontleft, frontrigth, backleft, backright;

    @Override
    public void move() {
        this.frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.frontrigth.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.frontrigth.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);

        double vertical = 0;
        double horizontal = 0;
        double pivot = 0;

        vertical = -gamepad1.left_stick_y;  //tiến lùi
        horizontal = gamepad1.left_stick_x; //sang trái phải
        pivot = gamepad1.right_stick_x;     //quay

        frontleft.setPower(pivot + (-vertical + horizontal));
        backleft.setPower(pivot + (-vertical - horizontal));
        frontrigth.setPower(pivot + (-vertical - horizontal));
        backright.setPower(pivot + (-vertical + horizontal));

    }

    @Override
    public void init() {
        frontleft = hardwareMap.get(DcMotor.class,"frontleft motor");
        backleft = hardwareMap.get(DcMotor.class,"backleft motor");
        frontrigth = hardwareMap.get(DcMotor.class,"frontright motor");
        backright = hardwareMap.get(DcMotor.class,"backright motor");
    }

    @Override
    public void loop() {
    }
}
