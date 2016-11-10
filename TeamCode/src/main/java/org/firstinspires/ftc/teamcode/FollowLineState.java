package org.firstinspires.ftc.teamcode;

/**
 * Created by Derek Williams of team 3965 on 11/8/2016.
 */

public class FollowLineState extends State{
    protected StateMachine lineFollowStateMachine = new StateMachine(getStateMachine().robot);

    FollowLineState(String name) {
        this.name = name;
    }

    @Override
    public void start() {
        // Adding states to state machine
        lineFollowStateMachine.add(new State[]{
            new WaddleState("waddle_right", 100, false),
            new WaddleState("waddle_left", 100, true),
        });

        // Adding transitions to state machine
        lineFollowStateMachine.add(new Transition[]{
            new LineDetectedTrans("waddle_right", "waddle_left"),
            new LineUndetectedTrans("waddle_left", "waddle_right"),
        });

        lineFollowStateMachine.setActiveState("waddle_right");
    }

    @Override
    public void loop() {
        lineFollowStateMachine.step();
    }

    @Override
    public void stop() {
        lineFollowStateMachine.stop();
    }
}
