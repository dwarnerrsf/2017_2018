package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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

    private int ArmPosition = 0;

    @Override
    public void init() {
        initialize();
        _ArmMotor.initialize(hardwareMap, "Arm", DcMotor.Direction.REVERSE);
        _ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _clawWrist = hardwareMap.servo.get("clawWrist");
        _clawRed = hardwareMap.servo.get("clawRed");
        _clawBlue = hardwareMap.servo.get("clawBlue");

    }

    @Override
    public void start() {
        _clawWrist.setPosition(0.0d); //set wrist to blue on bottom
        _clawRed.setPosition(0.0d); //set red claw closed at start
        _clawBlue.setPosition(0.0d); //set blue claw closed at start

        _ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void loop() {

        //region init variables
        _ArmMotor.setPower(.5d);
        //endregion

        //DRIVER #1 (GAMEPAD1)
        //region close claws if moving
        /* NOTE: need to think about this more before implementing
        if(gamepad1.dpad_up||gamepad1.dpad_left||gamepad1.dpad_right||gamepad1.dpad_down)
        {
            _clawBlue.setPosition(1.0d) ;
            _clawRed.setPosition(1.0d) ;
        }
        */
        //endregion

        //drive
        _driveModule.move(gamepad1);

        //DRIVER #2 (GAMEPAD2)
        //region ArmMotor control
        //increment ArmPosition
        if ( gamepad2.dpad_up)
        {
            ArmPosition = (ArmPosition> 2520) ? 2520 : ArmPosition++;
            _ArmMotor.getMotor().setTargetPosition(ArmPosition);
        }
        //set arm at back, high position
        if (gamepad2.dpad_down)
        {
            ArmPosition = (ArmPosition< 0) ? 0 : ArmPosition--;
            _ArmMotor.getMotor().setTargetPosition(ArmPosition);
        }

        //region Wrist control
        if (gamepad2.y) //blue goes to bottom
        {
            _clawWrist.setPosition(0.0d);

        }
        else if (gamepad2.a) //red goes to bottom
        {
            _clawWrist.setPosition(0.9d);
        }
        //endregion

        //region clawRed controls
        if (gamepad2.left_trigger > 0.7d)
        {
            _clawRed.setPosition(0.0d);
        }
        else if (gamepad2.left_bumper)
        {
            _clawRed.setPosition(1.0d);
        }
        //endregion

        //region clawBlue controls
        if (gamepad2.right_trigger > 0.7d)
        {
            _clawBlue.setPosition(0.0d);
        }
        else if (gamepad2.right_bumper)
        {
            _clawBlue.setPosition(1.0d) ;
        }
        //endregion

        telemetry.addData("Arm Position", _ArmMotor.getMotor().getCurrentPosition());
    }
}
