package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Inputs.Dpad;
import org.firstinspires.ftc.teamcode.Modules.Drives.DriveModule;
import org.firstinspires.ftc.teamcode.Modules.MotorModule;


@TeleOp(name="8606 Auto Blue 2", group="Pushbot")
public class Team_8606_Auto_Blue2 extends Team_8606_Op {
    private int _stage = 0;

    @Override
    public void init() {
        initializeModules();

        _driveModule.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void start() {
        _driveModule.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        gripClose();

        _lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        _knocker.setPosition(0.625d);

        resetStartTime();
    }

    @Override
    public void loop() {
        switch (_stage) {
            case 0:
                nextStage();
                break;
            case 1:
                if (time > 2.0d) {
                    nextStage();
                }
                break;
            case 2:
                _lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                _lift.setPower(1.0d);
                _lift.setTargetPosition(400);
                nextStage();
                break;
            case 3:
                _knocker.setPosition(0.0d);
                nextStage();
                break;
            case 4:
                if (_driveModule.getAveragePosition() > 2500) {
                    _driveModule.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    nextStage();
                }
                else {
                    _driveModule.setPower(0.20d);
                }
                break;
            case 5:
                if (_driveModule.getMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER)) {
                    nextStage();
                }
                break;
            case 6:
                _driveModule.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                if (_driveModule.getCurrentPosition(DriveModule.DriveMotor.FrontRight) > 1343) {
                    _driveModule.stop();
                    _driveModule.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    nextStage();
                }
                else {
                    _driveModule.setMaxPower(0.20d);
                    _driveModule.move(Dpad.Direction.Right);
                }
                break;
            case 7:
                if (_driveModule.getMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER)) {
                    nextStage();
                }
                break;
            case 8:
                _driveModule.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                if (_driveModule.getAveragePosition() > 900) {
                    _driveModule.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    nextStage();
                }
                else {
                    _driveModule.setPower(0.20d);
                }
                break;
            default:
                stop();
        }

        telemetry.addData("Time: ", time);
        telemetry.addData("Stage: ", _stage);
        telemetry.addData("Average: ", _driveModule.getAveragePosition());
        telemetry.addData("BackLeft: ", _driveModule.getCurrentPosition(DriveModule.DriveMotor.BackLeft));
        telemetry.addData("BackRight: ", _driveModule.getCurrentPosition(DriveModule.DriveMotor.BackRight));
        telemetry.addData("FrontLeft: ", _driveModule.getCurrentPosition(DriveModule.DriveMotor.FrontLeft));
        telemetry.addData("FrontRight: ", _driveModule.getCurrentPosition(DriveModule.DriveMotor.FrontRight));
        telemetry.addData("Lift: ", _lift.getCurrentPosition());
        telemetry.addData("Red: ", _color.red());
        telemetry.addData("Green: ", _color.green());
        telemetry.addData("Blue: ", _color.blue());
    }

    public void goToStage(int stage) {
        _stage = stage;

        resetStartTime();
    }

    public void nextStage() {
        goToStage(_stage + 1);
    }

    public void previousStage() {
        goToStage(_stage - 1);
    }
}
