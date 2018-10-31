package org.usfirst.frc.team6351.autoPID;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivePID extends PIDCommand {
	
	static double kP = 0.1;
	static double kI = 0;
	static double kD = 0;
	static double ekP = 0.05;
	double dst, rotation;
	boolean isFinished = false;
	int counter = 0;
	boolean onTarget = false;
	
	
	
	public DrivePID(double distance) {
		super(kP, kI, kD);
		dst = distance;
		
		requires(Robot.driveTrain);
		requires(Robot.sensors);
	}
	
	public void initialize() {
		setInputRange(-400, 400);
		//
		Robot.sensors.encoderLeft.reset();
		Robot.sensors.gyro.reset();
		//
		double currentDst = Robot.sensors.getDriveEncoderDistance();
		setSetpoint(currentDst + dst);
		getPIDController().setContinuous(false);
		getPIDController().setAbsoluteTolerance(2);
		getPIDController().setOutputRange(-0.35, 0.35);
		
		
		
		
		getPIDController().enable();
		
	}
	
	public void execute() {
		onTarget = getPIDController().onTarget();
		
		
		double error = 0-Robot.sensors.getGyroAngle();
		if (error < 2) {
			ekP = 0.1;
		}
		rotation = error*ekP;
		
		
		if (onTarget == true) {
			counter = counter +1;
			
			//changing this value will affect how long the system takes to adjust
			isFinished = counter >=20;
			
		} else {
			counter = 0;
		}
		
		SmartDashboard.putNumber("counter", counter);
		SmartDashboard.putBoolean("IsFin", isFinished);
		
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Robot.sensors.getDriveEncoderDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
		Robot.driveTrain.arcadeDrive(-1*output, rotation, false);
		SmartDashboard.putNumber("DriveOut", output);

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isFinished;
	}
	
	protected void interrupted() {
		end();
	}
	
	protected void end() {
		getPIDController().disable();
	}
	

}
