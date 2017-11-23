package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Modules.Drives.MecanumDriveModule;
import org.firstinspires.ftc.teamcode.Modules.MotorModule;


@TeleOp(name="Encoder Test", group="Pushbot")
public class EncoderTestOp extends OpMode {
    public MotorModule _motor = new MotorModule();

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        _motor.initialize(hardwareMap, "motor", DcMotorSimple.Direction.FORWARD);
        _motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        _motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        _motor.setPower(1.0d);
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        _motor.setTargetPosition(1680);

        telemetry.addData("Position", _motor.getCurrentPosition());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
