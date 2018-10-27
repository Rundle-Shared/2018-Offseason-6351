package org.usfirst.frc.team6351.autoPID;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class TurnPID extends PIDCommand {
	
	double m_angle;
	static double kP = 0;
	static double kI = 0;
	static double kD = 0;
	
	boolean isFinished = false;
	
	public TurnPID(double angle) {
		super(kP, kI, kD);
		requires(Robot.driveTrain);
		requires(Robot.sensors);
		m_angle = angle;
	}
	
	
	protected void initialize() {
		
		getPIDController().setPID(kP, kI, kD);
		//Robot.sensors.gyro.reset();
		double currentAngle = Robot.sensors.getGyroAngle();
		setInputRange(-180, 180);
		getPIDController().setContinuous(true);
		setSetpoint(currentAngle + m_angle);
		getPIDController().setAbsoluteTolerance(2);
		getPIDController().setOutputRange(-0.5, 0.5);
		
		getPIDController().enable();
		
	}
	
	public void execute() {
		boolean onTarget = getPIDController().onTarget();
		int counter = 0;
		if (onTarget == true) {
			counter = counter+1;
			isFinished = counter >= 5;
		}
		else {
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
		Robot.driveTrain.setLeft(-output);
		Robot.driveTrain.setRight(output);

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
;