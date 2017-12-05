package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

import org.firstinspires.ftc.teamcode.Modules.MotorModule;



@TeleOp(name="ViperAutoFacingCrypto", group="Pushbot")
public class ViperAutoFacingCrypto extends BaseOp{
    private MotorModule _ArmMotor = new MotorModule();
    private Servo _clawWrist = null ;
    private Servo _clawBlue = null ;
    private Servo _clawRed = null ;

    private int step = 1; //each autonomous is made up of steps
    private double start = 0;
    private Gamepad gp = null;

    @Override
    public void init() {
        initialize();
        _ArmMotor.initialize(hardwareMap, "Arm", DcMotor.Direction.FORWARD);
        _ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _clawWrist = hardwareMap.servo.get("clawWrist");
        _clawRed = hardwareMap.servo.get("clawRed");
        _clawBlue = hardwareMap.servo.get("clawBlue");

        gp = new Gamepad(); //Gamepad is used to push to driveModule.Move()

    }

    @Override
    public void start() {
        _clawWrist.setPosition(0.5d);
        //_ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        resetStartTime();
        start = time;
        gp.dpad_up = true;

    }

    @Override
    public void loop() {

        switch (step)
        {
            case 1: //move forward for 5 seconds
                if (time - start < 5)
                {
                    _driveModule.move(gp);
                }
                else
                    step++;
                break;
            case 2: //move arm down to front
                break;
            case 3: //open blue claw
                break;
            case 4: //move reverse for 5 seconds
                break;
        }


    }

}