package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Modules.MotorModule;
import org.firstinspires.ftc.teamcode.Utilities.MathFunctions;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="8606 Sample", group="Pushbot")
public class Sample_8606 extends BaseOp {
    private MotorModule _extender = new MotorModule();
    private MotorModule _retractor = new MotorModule();
    private MotorModule _lift = new MotorModule();

    private int[] liftPositions = new int[] { 0, 657, 1164, 1715 };
    private int currentPosition = 0;

    private Servo _claw = null;
    private Servo _gripper = null;

    private Servo _leftGlyph = null;
    private Servo _rightGlyph = null;

    @Override
    public void init() {
        initialize();

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
        _lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            _driveModule.setMaxPower(0.25d);
        }
        else if (gamepad1.b) {
            _driveModule.setMaxPower(0.50d);
        }
        else if (gamepad1.x) {
            _driveModule.setMaxPower(0.75d);
        }
        else if (gamepad1.y) {
            _driveModule.setMaxPower(1.0d);
        }

        if (gamepad2.y) {
            _extender.forward();
        }
        else if (gamepad2.a) {
            _retractor.forward();
        }
        else {
            _extender.stop();
            _retractor.stop();
        }

        if (gamepad2.dpad_up) {
            _claw.setPosition(1.0d);
        }
        else if (gamepad2.dpad_down) {
            _claw.setPosition(0.05d);
        }

        if (gamepad2.x) {
            _gripper.setPosition(1.0d);
        }
        else if (gamepad2.b) {
            _gripper.setPosition(0.0d);
        }

        if (gamepad2.right_trigger > 0.10d) {
            _lift.forward();
        }
        else if (gamepad2.left_trigger > 0.10d) {
            _lift.reverse();
        }
        else {
            _lift.stop();
        }

        _driveModule.move(gamepad1);

        if (gamepad2.left_stick_y > 0.10d) {
            _leftGlyph.setPosition(0.0d);
        }
        else if (gamepad2.left_stick_y < -0.10d) {
            _leftGlyph.setPosition(0.30d);
        }

        if (gamepad2.right_stick_y > 0.10d) {
            _rightGlyph.setPosition(0.0d);
        }
        else if (gamepad2.right_stick_y < -0.10d) {
            _rightGlyph.setPosition(0.40d);
        }

        telemetry.addData("Lift: ", _lift.getCurrentPosition());
        telemetry.addData("Position: ", currentPosition);
        telemetry.addData("Max: ", liftPositions.length - 1);
    }
}
