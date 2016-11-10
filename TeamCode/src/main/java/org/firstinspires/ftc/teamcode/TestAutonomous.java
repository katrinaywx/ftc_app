package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Robotics on 11/9/2016.
 */

@Autonomous(name="Test Auto", group="3965")
public class TestAutonomous extends VelocityVortexRobotBase {
    protected StateMachine stateMachine = new StateMachine(this);

    @Override
    public void start(){
        // Adding states to state machine
        stateMachine.add(new State[]{
            new FollowLineState("follow"),
        });

        // Adding transitions to state machine
        stateMachine.add(new Transition[]{
            new BelowRangeTrans("follow", null, 4),
        });

        // Setting Initial active state
        stateMachine.setActiveState("follow");
    }

    @Override
    public void loop(){
        stateMachine.step();
    }

    @Override
    public void stop(){
        stateMachine.stop();
    }
}
