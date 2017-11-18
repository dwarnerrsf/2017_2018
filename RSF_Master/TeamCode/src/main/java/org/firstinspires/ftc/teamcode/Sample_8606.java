package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="8606 Sample", group="Pushbot")
public class Sample_8606 extends Team_8606_Op {

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
            liftUp();
        }
        else if (gamepad2.left_trigger > 0.10d) {
            liftDown();
        }
        else {
            _lift.stop();
        }

        _driveModule.move(gamepad1);

        if (gamepad2.left_stick_y > 0.10d || gamepad2.right_stick_y > 0.10d) {
            gripOpen();
        }
        else if (gamepad2.left_stick_y < -0.10d || gamepad2.right_stick_y < -0.10d) {
            gripClose();
        }

        telemetry.addData("Lift: ", _lift.getCurrentPosition());
        telemetry.addData("Position: ", currentPosition);
        telemetry.addData("Max: ", liftPositions.length - 1);
    }
}
