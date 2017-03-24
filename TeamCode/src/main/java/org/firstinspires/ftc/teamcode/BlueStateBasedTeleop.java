package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Derek Williams of team 3965 on 2/1/2017.
 */

@TeleOp(name="Blue vvsb Teleop", group="3965")
public class BlueStateBasedTeleop extends StateBasedTeleop {
    @Override
    public void start() {
        color = BLUE;
        super.start();
    }
}
