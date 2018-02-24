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



@TeleOp(name="ViperBlue2", group="Pushbot")
public class ViperBlue2 extends BaseOp{
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

        _driveModule.initialize(hardwareMap);
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
            case 2: // is color sensor looking at red
                if (_colorSensor.red() >= 70) {
                    //looking at red, move forward to knock off red
                    step=3;
                }else{
                    //looking at blue move backward to knock off red
                    step=13;
                }
                break;
            case 3: //move forward for .5 seconds
                if (time < timeOffSet + 0.5d)
                {
                    _driveModule.move(1.0d, 1.0d);
                }
                else
                {
                    _driveModule.stop();
                    timeOffSet = time;
                    step++;
                }
                break;
            case 4: //lift color arm for 2 secs
                if (time < timeOffSet + 2.0d)
                {
                    _colorArm.setPosition(0.45d);
                }
                else
                {
                    timeOffSet = time;
                    step++;
                }
                break;
            case 5: //rotate counterclockwise for 1.5 secs
                if (time < timeOffSet + 1.5d)
                {
                    _driveModule.setMaxPower(1.0d);
                    _driveModule.move(-1.0d, 1.0d);
                }
                else
                {
                    _driveModule.stop();
                    timeOffSet = time;
                    step++;
                }
                break;
            case 6: //move forward for 1.0 secs
                if (time < timeOffSet + 1.0d)
                {
                    _driveModule.move(1.0d, 1.0d);
                }
                else
                {
                    _driveModule.stop();
                    timeOffSet = time;
                    step++;
                }
                break;
            case 7: //lower arm, open claws
//                _ArmMotor.setPower(0.9d);
//                _ArmMotor.getMotor().setTargetPosition(0);
                if (time < timeOffSet + 2.0d)
                {
                    _clawRed.setPosition(1.0d);
                    _clawBlue.setPosition(1.0d);
                }
                else
                {
                    timeOffSet = time;
                    step++;
                }
                break;
            case 8: //move forward for 0.5 seconds
                if (time < timeOffSet + 0.5d)
                {
                    _driveModule.setMaxPower(0.75d);
                    _driveModule.move(1.0d, 1.0d);
                }
                else
                {
                    _driveModule.stop();
                    step++;
                    timeOffSet = time;
                }
                break;
            //
            //
            //moving backward
            case 13: //move backward for .5 seconds
                if (time < timeOffSet + 0.5d)
                {
                    _driveModule.move(-1.0d, -1.0d);
                }
                else
                {
                    _driveModule.stop();
                    timeOffSet = time;
                    step++;
                }
                break;
            case 14: //lift color arm for 2 secs
                if (time < timeOffSet + 2.0d)
                {
                    _colorArm.setPosition(1.0d);
                }
                else
                {
                    timeOffSet = time;
                    step++;
                }
                break;
       /*     case 15: //rotate clockwise for 0.5 secs
                if (time < timeOffSet + 0.5d)
                {
                    _driveModule.setMaxPower(1.0d);
                    _driveModule.move(1.0d, -1.0d);
                }
                else
                {
                    _driveModule.move(0.0d, 0.0d);
                    timeOffSet = time;
                    step++;
                }
                break;
            case 16: //move forward over ramp for 4.0 second
                if (time < timeOffSet + 4.0d)
                {
                    _driveModule.setMaxPower(1.0d);
                    _driveModule.move(1.0d, 1.0d);
                }
                else
                {
                    _driveModule.move(0.0d, 0.0d);
                    timeOffSet = time;
                    step++;
                }
                break;
        */
        }
        telemetry.addData("Arm Position", _ArmMotor.getMotor().getCurrentPosition());
        //telemetry.addData("clawRedPosition", clawRedPosition);
        //telemetry.addData("clawBluePosition", clawBluePosition);
        telemetry.addData( "color red",_colorSensor.red());
        telemetry.addData( "color blue",_colorSensor.blue());
        telemetry.addData("step", step);
    }

}