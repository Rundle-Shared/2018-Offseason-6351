package org.usfirst.frc.team6351.autoPID;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivePID extends PIDCommand {
	
	//CONTROL Constants - Alter to find Balance
	static double kP = 0.1;
	static double kI = 0;
	static double kD = 0.0; //super small increase
	//Constant for driving straight
	static double ekP = 0.05;
	double dst, rotation;
	boolean finished = false;
	//Threshold is the minimum time in target to return true, counter tracks time in target zone
	int threshold;
	int counter = 0;
	boolean onTarget = false;
	
	
	
	
	public DrivePID(double distance, int thresh) {
		super(kP, kI, kD);
		dst = distance;
		threshold = thresh;
		
		requires(Robot.driveTrain);
		requires(Robot.sensors);
		
		//this.setName("DriveTrain", "DrivePID");
		LiveWindow.add(this.getPIDController());
	}
	
	public void initialize() {
		//Setting variables for PID control
		setInputRange(-400, 400);
		//
		Robot.sensors.encoderLeft.reset();
		//Robot.sensors.gyro.reset();
		rotation = Robot.sensors.getGyroAngle();
		//
		double currentDst = Robot.sensors.getDriveEncoderDistance();
		setSetpoint(currentDst + dst);
		//Absolute Tolerance is range, output range is speed, and continues determines if range is spectrum or scale
		getPIDController().setContinuous(false);
		getPIDController().setAbsoluteTolerance(2);
		getPIDController().setOutputRange(-0.4, 0.4);
		
		getPIDController().enable();
		
	}
	
	public void execute() {
		onTarget = getPIDController().onTarget();
		
		
		double error = rotation - Robot.sensors.getGyroAngle();
		
		rotation = error*ekP;
		
		
		if (onTarget == true) {
			counter = counter +1;
			
			//changing this value will affect how long the system takes to adjust
			finished = counter >= threshold;
			
		} else {
			counter = 0;
		}
		
		SmartDashboard.putNumber("counter", counter);
		SmartDashboard.putBoolean("IsFin", finished);
		
		LiveWindow.add(this.getPIDController());
		
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
		return finished;
	}
	
	protected void interrupted() {
		end();
	}
	
	protected void end() {
		getPIDController().disable();
	}
	

}
