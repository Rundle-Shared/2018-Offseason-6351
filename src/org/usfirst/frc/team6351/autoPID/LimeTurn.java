package org.usfirst.frc.team6351.autoPID;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class LimeTurn extends PIDCommand {
	static double kP = 0.1;
	static double kI = 0;
	static double kD = 0;
	
	public static boolean isFinished = false;
	
	int counter = 0;
	int threshold = 25;
	boolean onTarget = false;
	
	public static double value = 0;
	
	public LimeTurn() {
		super(kP, kI, kD);
	}
	
	protected void initialize() {
		getPIDController().setAbsoluteTolerance(3);
		getPIDController().setContinuous(true);
		setInputRange(-180,180);
		getPIDController().setOutputRange(-0.35, 0.35);
		setSetpoint(0);
		
		
		getPIDController().enable();
	}
	
	protected void execute() {
		boolean onTarget = getPIDController().onTarget();
		
		if (Robot.targetsVisible < 1) {
			Robot.driveTrain.arcadeDrive(0, 0.35, true);
		}
		else {
		if (onTarget == true) {
			counter = counter+1;
			
			//changing this value will affect how long the system takes to adjust
			isFinished = counter >= threshold;
		}
		else {
			counter = 0;
		} 
		}
	}
	

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Robot.targetX;
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
	
	public static double PIDOutput() {
		return value;
	}
	

}
