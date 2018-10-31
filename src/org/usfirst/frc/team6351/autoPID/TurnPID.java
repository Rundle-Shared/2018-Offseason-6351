package org.usfirst.frc.team6351.autoPID;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnPID extends PIDCommand {
	
	double m_angle;
	static double kP = 0.1;
	static double kI = 0;
	static double kD = 0;
	int counter = 0;
	
	boolean isFinished = false;
	
	public TurnPID(double angle) {
		super(kP, kI, kD);
		requires(Robot.driveTrain);
		requires(Robot.sensors);
		m_angle = angle;
	}
	
	
	protected void initialize() {
		
		getPIDController().setPID(kP, kI, kD);
		//
		Robot.sensors.gyro.reset();
		Robot.sensors.encoderLeft.reset();
		//
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
		
		if (onTarget == true) {
			counter = counter+1;
			
			//changing this value will affect how long the system takes to adjust
			isFinished = counter >= 20;
		}
		else {
			counter = 0;
		}
		SmartDashboard.putBoolean("onTar", onTarget);
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Robot.sensors.getGyroAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Robot.driveTrain.setLeft(output);
		Robot.driveTrain.setRight(output);
		SmartDashboard.putNumber("output", output);

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