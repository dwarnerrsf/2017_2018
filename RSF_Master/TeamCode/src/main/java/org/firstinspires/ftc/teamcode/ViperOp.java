package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.teamcode.Modules.MotorModule;


@TeleOp(name="ViperOp", group="Pushbot")
public class ViperOp extends BaseOp {
    private MotorModule _ArmMotor = new MotorModule();
    private Servo _clawWrist = null ;
    private Servo _clawBlue = null ;
    private Servo _clawRed = null ;

    private Servo _colorArm = null;
    private ColorSensor _colorSensor = null;

    private int ArmPosition = 0;
    private boolean clawBlueOpen = false;
    private boolean clawRedOpen = false;

    private double clawBluePosition = 0d;
    private double clawRedPosition = 0d;

    @Override
    public void init() {
        _ArmMotor.initialize(hardwareMap, "Arm", DcMotor.Direction.REVERSE);
        _ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _clawWrist = hardwareMap.servo.get("clawWrist");
        _clawRed = hardwareMap.servo.get("clawRed");
        _clawBlue = hardwareMap.servo.get("clawBlue");
        _colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        _colorArm = hardwareMap.servo.get("colorArm");

    }

    @Override
    public void start() {
        _clawWrist.setPosition(0.0d); //set wrist to blue on bottom
        _clawRed.setPosition(clawRedPosition); //set red claw closed at start
        _clawBlue.setPosition(clawBluePosition); //set blue claw closed at start

        _ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void loop() {

        //region init variables
        //_ArmMotor.setPower(.75d);
        //endregion

        //drive
        //_driveModule.setPower(1.0d);
        _driveModule.move(gamepad1);
        //keep colorArm straight up
        _colorArm.setPosition(0.35d);

        //DRIVER #2 (GAMEPAD2)
        //region ArmMotor control
        //increment ArmPosition
        if (gamepad2.dpad_up) {
            if (ArmPosition <= 3000){
                ArmPosition += 12;
            }
            _ArmMotor.setPower(0.65d);
            _ArmMotor.getMotor().setTargetPosition(ArmPosition);
        }
        //set arm at back, high position
        if (gamepad2.dpad_down)
        {
            if(ArmPosition >= -500){
                ArmPosition-=12;
            }
            _ArmMotor.setPower(.65d);
            _ArmMotor.getMotor().setTargetPosition(ArmPosition);
        }
        //endregion

        //region Wrist control
        if (gamepad2.y) //blue goes to bottom
        {
            _clawRed.setPosition(0.0d);
            _clawBlue.setPosition(0.0d);
            _clawWrist.setPosition(0.0d);

        }
        else if (gamepad2.a) //red goes to bottom
        {
            _clawRed.setPosition(0.0d);
            _clawBlue.setPosition(0.0d);
            _clawWrist.setPosition(1.0d);
        }
        //endregion

        //region clawRed controls
        if (gamepad2.left_trigger > 0.7d)
        {
            if(clawRedPosition>0.0d)
                clawRedPosition-=0.0125d;
            _clawRed.setPosition(clawRedPosition);
        }
        else if (gamepad2.left_bumper)
        {
            if(clawRedPosition<1.0d)
                clawRedPosition+=0.0125d;
            _clawRed.setPosition(clawRedPosition);
        }
        //endregion

        //region clawBlue controls
        if (gamepad2.right_trigger > 0.7d)
        {
            if(clawBluePosition>0.0d)
                clawBluePosition-=0.0125d;
            _clawBlue.setPosition(clawBluePosition);
        }
        else if (gamepad2.right_bumper)
        {
            if(clawBluePosition<1.0d)
                clawBluePosition+=0.0125d;
            _clawBlue.setPosition(clawBluePosition) ;
        }
        //endregion
        
        //region relic arm
        if (gamepad2.y) {
            _relicArm.forward();
        }
        else if (gamepad2.a) {
            _relicArm.reverse();
        }
        else {
            _relicArm.stop();
        }

        if (gamepad2.dpad_up) {
            _wrist.setPosition(0.850d);
        }
        else if (gamepad2.dpad_down) {
            _wrist.setPosition(0.00d);
        }
        
        if (gamepad2.dpad_right) {
            _claw.setPosition(0.660d);
        }
        if (gamepad2.b) {
            _claw.setPosition(1.0d);
        }
        else if (gamepad2.x) {
            _claw.setPosition(0.00d);
        }
        //endregion

        telemetry.addData("Arm Position", _ArmMotor.getMotor().getCurrentPosition());
        telemetry.addData("clawRedPosition", clawRedPosition);
        telemetry.addData("clawBluePosition", clawBluePosition);
    }
}
