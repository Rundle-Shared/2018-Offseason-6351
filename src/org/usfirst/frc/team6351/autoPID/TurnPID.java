package org.usfirst.frc.team6351.autoPID;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnPID extends PIDCommand {
	
	double m_angle;
	static double kP = 0.01;
	static double kI = 0;
	static double kD = 0;
	int threshold;
	int counter = 0;
	double rotation;
	
	boolean finished = false;
	
	public TurnPID(double angle, int thresh) {
		super(kP, kI, kD);
		requires(Robot.driveTrain);
		requires(Robot.sensors);
		m_angle = angle;
		threshold = thresh;
		
		//this.setName("DriveTrain", "TurnPID");
		LiveWindow.add(this.getPIDController());
	}
	
	
	protected void initialize() {
		
		getPIDController().setPID(kP, kI, kD);
		//
		//Robot.sensors.gyro.reset();
		Robot.sensors.encoderLeft.reset();
		//
		double currentAngle = Robot.sensors.getGyroAngle();
		setInputRange(-180, 180);
		getPIDController().setContinuous(true);
		setSetpoint(currentAngle + m_angle);
		getPIDController().setAbsoluteTolerance(2);
		getPIDController().setOutputRange(-0.4, 0.4);
		
		getPIDController().enable();
		
	}
	
	public void execute() {
		boolean onTarget = getPIDController().onTarget();
		
		if (onTarget == true) {
			counter = counter+1;
			
			//changing this value will affect how long the system takes to adjust
			finished = counter >= threshold;
		}
		else {
			counter = 0;
		}
		SmartDashboard.putBoolean("onTar", onTarget);
		LiveWindow.add(getPIDController());
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Robot.sensors.getGyroAngle();
		//return Robot.sensors.NavX.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Robot.driveTrain.setLeft(output);
		Robot.driveTrain.setRight(output);
		SmartDashboard.putNumber("output", output);
		//Robot.driveTrain.m_myRobot.arcadeDrive(0, output, true);
		/*
		if (output < 0) {
			 rotation = (-0.4*(Math.pow(-1*(output), 2)));
		}
		else if (output > 0) {
			 rotation = (0.4*(Math.pow(output, 2)));
		}
		else if (output == 0) {
			 rotation = 0;
		}
		Robot.driveTrain.m_myRobot.arcadeDrive(0, rotation, true);
		*/
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
;