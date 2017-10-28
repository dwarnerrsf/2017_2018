package org.firstinspires.ftc.teamcode.Utilities;


public class MathFunctions {
    public static double clamp(double min, double max, double value) {
        return Math.max(min, Math.min(max, value));
    }
}
