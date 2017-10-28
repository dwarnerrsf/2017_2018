package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="TestOp", group="Pushbot")
public class TestOp extends BaseOp {
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

        _driveModule.move(gamepad1);
    }
}
