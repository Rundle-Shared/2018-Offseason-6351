package org.usfirst.frc.team6351.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class Sensors extends Subsystem {
	 
	public Encoder encoderLeft;
	public BuiltInAccelerometer accel;
	
	public Sensors() {
		accel = new BuiltInAccelerometer();
		encoderLeft = new Encoder(0, 1, true, Encoder.EncodingType.k4X); 
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
