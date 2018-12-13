package org.usfirst.frc.team6351.autoPID;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class LimeDrive extends PIDCommand {
	static double kP = 0.1;
	static double kI = 0;
	static double kD = 0;
	
	public static boolean isFinished = false;
	boolean onTarget = false;
	
	int counter = 0;
	int threshold = 25;
	
	public static double value = 0;
	
	public LimeDrive() {
		super(kD, kI, kD);
	}
	
	protected void initialize() {
		getPIDController().setAbsoluteTolerance(3);
		getPIDController().setContinuous(false);
		setInputRange(0, 100);
		getPIDController().setOutputRange(-0.35, 0.35);
		setSetpoint(37);
		
		getPIDController().enable();
	}
	
	protected void execute() {
		onTarget = getPIDController().onTarget();
		if (onTarget == true) {
			counter ++;
			isFinished = counter >= threshold;	
		}
		else {
			counter = 0;
		}
	
	}
	
	
	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Robot.targetArea;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		value = output;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void interrupted() {
		getPIDController().disable();
	}

}
