package org.usfirst.frc.team6351.autoPID;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class DrivePID extends PIDCommand {
	
	static double kP = 0;
	static double kI = 0;
	static double kD = 0;
	static double ekP = 0;
	double dst, rotation;
	boolean isFinished = false;
	
	
	
	public DrivePID(double distance) {
		super(kP, kI, kD);
		dst = distance;
		
		requires(Robot.driveTrain);
		requires(Robot.sensors);
	}
	
	public void initialize() {
		setInputRange(-400, 400);
		//Robot.sensors.encoderLeft.reset();
		double currentDst = Robot.sensors.getDriveEncoderDistance();
		setSetpoint(currentDst + dst);
		getPIDController().setContinuous(false);
		getPIDController().setAbsoluteTolerance(5);
		getPIDController().setOutputRange(0.5, 0.5);
		
		//Robot.sensors.gyro.reset();
		
		
		getPIDController().enable();
		
	}
	
	public void execute() {
		boolean onTarget = getPIDController().onTarget();
		int counter = 0;
		
		double error = 0-Robot.sensors.getGyroAngle();
		rotation = error*ekP;
		
		
		if (onTarget == true) {
			counter = counter +1;
			isFinished = counter >=5;
		} else {
			counter = 0;
		}
		
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
		Robot.driveTrain.arcadeDrive(output, rotation, false);

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
