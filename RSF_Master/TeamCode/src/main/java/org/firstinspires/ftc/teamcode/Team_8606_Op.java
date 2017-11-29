package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Modules.MotorModule;


@TeleOp(name="8606 Base", group="Pushbot")
public class Team_8606_Op extends BaseOp {
    protected MotorModule _extender = new MotorModule();
    protected MotorModule _retractor = new MotorModule();
    protected MotorModule _lift = new MotorModule();

    protected int[] liftPositions = new int[] { 0, 657, 1164, 1715 };
    protected int currentPosition = 0;

    protected Servo _claw = null;
    protected Servo _gripper = null;

    protected Servo _leftGlyph = null;
    protected Servo _rightGlyph = null;

    protected Servo _knocker = null;

    protected ColorSensor _color = null;

    @Override
    public void initializeModules() {
        _driveModule.initialize(hardwareMap);

        _extender.initialize(hardwareMap, "extender", DcMotor.Direction.FORWARD);
        _extender.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        _retractor.initialize(hardwareMap, "retractor", DcMotor.Direction.FORWARD);
        _retractor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        _extender.setMaxPower(1.0d);
        _retractor.setMaxPower(1.0d);

        _extender.setPower(0.0d);
        _retractor.setPower(0.0d);

        _lift.initialize(hardwareMap, "lift", DcMotor.Direction.REVERSE);
        _lift.setMaxPower(1.0d);
        _lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        _claw = hardwareMap.get(Servo.class, "claw");
        _claw.setDirection(Servo.Direction.REVERSE);
        _claw.setPosition(0.05d);

        _gripper = hardwareMap.get(Servo.class, "gripper");
        _gripper.setPosition(0.0d);

        _leftGlyph = hardwareMap.get(Servo.class, "leftGlyph");
        _leftGlyph.setPosition(1.0d);

        _rightGlyph = hardwareMap.get(Servo.class, "rightGlyph");
        _rightGlyph.setDirection(Servo.Direction.REVERSE);
        _rightGlyph.setPosition(1.0d);

        _knocker = hardwareMap.get(Servo.class, "knocker");
        _knocker.setDirection(Servo.Direction.REVERSE);
        _knocker.setPosition(0.0);

        _color = hardwareMap.get(ColorSensor.class, "color");
    }

    public void gripOpen() {
        _leftGlyph.setPosition(0.50d);
        _rightGlyph.setPosition(0.50d);
    }

    public void gripClose() {
        _leftGlyph.setPosition(0.0d);
        _rightGlyph.setPosition(0.0d);
    }

    public void liftDown() {
        _lift.reverse();
    }

    public void liftUp() {
        _lift.forward();
    }

    public void stop() {
        _driveModule.stop(

        );
        _extender.setPower(0.0d);
        _retractor.setPower(0.0d);
        _lift.setPower(0.0d);
    }
}
