package org.usfirst.frc.team6351.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
public class Sensors extends Subsystem {
	 
	public Encoder encoderLeft;
	public BuiltInAccelerometer accel;
	public ADXRS450_Gyro gyro;
	
	public Sensors() {
		accel = new BuiltInAccelerometer();
		encoderLeft = new Encoder(0, 1, true, Encoder.EncodingType.k4X); 
		gyro = new ADXRS450_Gyro();
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public double getGyroAngle() {
		return gyro.getAngle();
	}
	public double getXAccel() {
    	return accel.getX();
    }
    public double getYAccel() {
    	return accel.getY();
    }
    public double getDriveEncoderRaw() {
    	return encoderLeft.get();
    }
    public double getDriveEncoderDistance() {
    	//Convert counts to centimeters
    	double distanceIN = (encoderLeft.get()) / (19.1667);
    	return distanceIN ;
    }
    public void resetEncoder() {
    	encoderLeft.reset();
    }

}
