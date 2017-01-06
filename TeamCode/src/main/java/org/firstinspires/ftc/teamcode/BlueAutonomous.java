package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Derek Williams on 10/11/2016.
 */

@Autonomous(name="AutoBlue", group="3965")
public class BlueAutonomous extends VelocityVortexAutonomous {
    @Override
    public void init() {
        super.init();
        turnSpeed = -turnSpeed;
        color = BLUE;
    }

    @Override
    public void start(){
        super.start();

        // Adding states to state machine
        stateMachine.add(new State[]{
                // Driving to first beacon
                new DriveState("forward1", driveSpeed),
                new TurnState("turn1", -turnSpeed),
                new DriveState("forward2", driveSpeed),
                new TurnState("turn1a", -turnSpeed),

                // Pressing first button
                new EdgeFollowState("follow1", followSpeed),
                new PushButtonState("push1", color),

                // Throwing particles into vortex
                new FlickParticleState("throw"),

                // Driving to second beacon
                new DriveState("reverse1", -driveSpeed),
                new TurnState("turn2", turnSpeed),
                new DriveState("forward3", driveSpeed),
                new TurnState("turn3", -turnSpeed),

                // Pressing second beacon
                new EdgeFollowState("follow2", followSpeed),
                new PushButtonState("push2", color),

                // Pushing the capball and parking
                new DriveState("reverse2", -driveSpeed),
                new TurnState("turn4", turnSpeed),
                new DriveState("forward4", -driveSpeed),

        });

        // Adding transitions to state machine
        stateMachine.add(new Transition[]{
                // Driving to first beacon
                new ProgressReachedTrans("forward1", "turn1", cmToEnc(55.0)),
                new ProgressReachedTrans("turn1", "forward2", rotsToEnc(0.12)),  //0.135
                new ProgressReachedTrans("forward2", "turn1a", cmToEnc(95.0)),
                new ProgressReachedTrans("turn1a", "follow1", rotsToEnc(0.15)),

                // Pressing first button
                new BelowRangeTrans("follow1", "push1", rangeToBeacon),
                new TimeElapsedTrans("push1", "throw", 1),

                // Throwing particles into vortex
                new TimeElapsedTrans("throw", "reverse1", throwDuration),

                // Driving to second beacon
                new AboveRangeTrans("reverse1", "turn2", rangeFromBeacon),
                new ProgressReachedTrans("turn2", "forward3", rotsToEnc(0.23)),
                new ProgressReachedTrans("forward3", "turn3", cmToEnc(100.0)),
                new ProgressReachedTrans("turn3", "follow2", rotsToEnc(0.25)),

                // Pressing second button
                new BelowRangeTrans("follow2", "push2", rangeToBeacon),
                new TimeElapsedTrans("push2", "reverse2", 1),

                // Pushing the capball and parking
                new AboveRangeTrans("reverse2", "turn4", rangeFromBeacon),
                new ProgressReachedTrans("turn4", "forward4", rotsToEnc(0.15)),
                new ProgressReachedTrans("forward4", null, cmToEnc(120.0)),
        });

        // Setting Initial active state
        stateMachine.setActiveState("forward1");
    }
}
