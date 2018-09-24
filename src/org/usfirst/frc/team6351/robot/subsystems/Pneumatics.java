package org.usfirst.frc.team6351.robot.subsystems;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {
	static Compressor compressor;

	public Pneumatics() {
		compressor = new Compressor();

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void start() {
		if (Robot.isReal()) {
			compressor.stop();

		}
	}

}
