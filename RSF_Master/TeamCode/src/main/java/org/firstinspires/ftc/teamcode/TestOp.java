package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.teamcode.Inputs.Dpad;
import org.firstinspires.ftc.teamcode.Modules.MotorModule;



@TeleOp(name="TestOp", group="Pushbot")
public class TestOp extends BaseOp{
    private MotorModule _ArmMotor = new MotorModule();
    private Servo _clawWrist = null ;
    private Servo _clawBlue = null ;
    private Servo _clawRed = null ;

    private Servo _colorArm = null;
    private ColorSensor _colorSensor = null;

    private int step = 1; //each autonomous is made up of steps
    private double start = 0;
    private Gamepad gp = null;

    private double timeOffSet = 0.0d;

    @Override
    public void init() {
        _ArmMotor.initialize(hardwareMap, "Arm", DcMotor.Direction.REVERSE);
        _ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _clawWrist = hardwareMap.servo.get("clawWrist");
        _clawRed = hardwareMap.servo.get("clawRed");
        _clawBlue = hardwareMap.servo.get("clawBlue");
        _colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        _colorArm = hardwareMap.servo.get("colorArm");

        gp = new Gamepad(); //Gamepad is used to push to driveModule.Move()
        _clawBlue.setPosition(0.0d) ;
        _clawRed.setPosition(0.0d) ;

    }

    @Override
    public void start() {
        _clawWrist.setPosition(0.0d); //set wrist to blue on bottom
        _clawRed.setPosition(0.0d); //set red claw closed at start
        _clawBlue.setPosition(0.0d); //set blue claw closed at start
        //_colorArm.setPosition(0.5d);

        _ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        timeOffSet = time;
        _driveModule.setMaxPower(0.25d);
        telemetry.addData("Arm Position", _ArmMotor.getMotor().getCurrentPosition());
    }


    @Override
    public void loop() {


        switch (step)
        {
            case 1: //lower color arm for 2 secs
                if (time < timeOffSet + 2.0d)
                {
                    _colorArm.setPosition(0.0d);
                }
                else
                {
                    timeOffSet = time;
                    step++;
                }
                break;
            case 2: //lower color arm for 2 secs
                if (time < timeOffSet + 2.0d)
                {
                    _colorArm.setPosition(0.35d);
                }
                else
                {
                    timeOffSet = time;
                    step++;
                }
                break;
            case 3: //lower color arm for 2 secs
                if (time < timeOffSet + 2.0d)
                {
                    _colorArm.setPosition(0.4d);
                }
                else
                {
                    timeOffSet = time;
                    step=1;
                }
                break;

       }
        telemetry.addData("Arm Position", _ArmMotor.getMotor().getCurrentPosition());
        //telemetry.addData("clawRedPosition", clawRedPosition);
        //telemetry.addData("clawBluePosition", clawBluePosition);
        telemetry.addData( "color red",_colorSensor.red());
        telemetry.addData( "color blue",_colorSensor.blue());
        telemetry.addData("step", step);
    }

}