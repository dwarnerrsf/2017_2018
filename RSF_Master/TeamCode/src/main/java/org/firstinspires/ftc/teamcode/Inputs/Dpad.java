package org.firstinspires.ftc.teamcode.Inputs;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.Utilities.Convert;


public class Dpad {
    public enum Direction {
        Down,
        DownLeft,
        DownRight,
        Left,
        None,
        Right,
        Up,
        UpLeft,
        UpRight
    }

    private Direction _dircetion = Direction.None;

    public Dpad(Direction direction) {
        _dircetion = direction;
    }

    public Dpad(Gamepad gamepad) {
        _dircetion = Convert.toDirection(gamepad);
    }

    public Direction getDirection() {
        return _dircetion;
    }
}
