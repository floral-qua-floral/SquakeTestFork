package fqf.qua_mario.mariostates;

import fqf.qua_mario.MarioClient;

import java.util.ArrayList;
import java.util.Arrays;

public class MarioGrounded extends MarioState {
	public static final MarioGrounded INSTANCE = new MarioGrounded();

	private MarioGrounded() {
		this.name = "Grounded";

		preTickTransitions = new ArrayList<>(Arrays.asList(
			CommonTransitions.FALL
		));

		postTickTransitions = new ArrayList<>(Arrays.asList(
			CommonTransitions.JUMP
		));
	}

	@Override
	public void tick() {
		double strafeSpeed = MarioClient.rightwardInput * 0.275;
		double strafeAccel = 0.065;
		if(MarioClient.forwardInput > 0) {
			double forwardMoveSpeed = MarioClient.forwardInput * (MarioClient.player.isSprinting() ? 0.45: 0.265);
			if (MarioClient.forwardVel > 0.275) {
				// Sprint Accel
				MarioClient.groundedAccel(forwardMoveSpeed, strafeSpeed, 0.025, strafeAccel);
			} else if (MarioClient.forwardVel <= 0.12) {
				// Walk Accel From Standstill or Backpedal
				MarioClient.groundedAccel(forwardMoveSpeed, strafeSpeed, 0.125, strafeAccel);
			}
			else if (MarioClient.forwardVel <= forwardMoveSpeed * 1.015) {
				// Walk Accel
				MarioClient.groundedAccel(forwardMoveSpeed, strafeSpeed, 0.045, strafeAccel);
			} else {
				// Overwalk
				MarioClient.groundedAccel(forwardMoveSpeed, strafeSpeed, 0.01, strafeAccel);
			}
		} else if(MarioClient.forwardInput < 0) {
			double forwardMoveSpeed = MarioClient.forwardInput * 0.25;
			if(MarioClient.forwardVel > 0.285) {
				// Transition to skid!
				MarioClient.changeState(MarioSkid.INSTANCE);
			} else if(MarioClient.forwardVel >= MarioClient.forwardInput) {
				// Backpedal Accel
				MarioClient.groundedAccel(forwardMoveSpeed, strafeSpeed, 0.1, strafeAccel);
			} else {
				// Overbackup
				MarioClient.groundedAccel(forwardMoveSpeed, strafeSpeed, 0.045, strafeAccel);
			}
		} else {
			// Not moving forward or backwards
			MarioClient.groundedAccel(0, strafeSpeed, 0.075, strafeAccel);
		}

		MarioClient.yVel = -0.1;
	}
}
