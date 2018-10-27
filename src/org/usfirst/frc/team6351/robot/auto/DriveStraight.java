package org.usfirst.frc.team6351.robot.auto;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {
	
	double dst, tme;
	double kP = 0.03;
	boolean timeDrive;
	
	public DriveStraight(double distance, double time) {
		requires(Robot.driveTrain);
		requires(Robot.sensors);
		
		dst = distance;
		
		if (time > 0) {
			timeDrive = true;
			tme = time;
			
		} else {
			timeDrive = false;
		}
	}
	
	public DriveStraight(double distance) {
		this(distance, 0.0);
	}
	
	
	protected void initialize() {
		Robot.sensors.resetEncoder();
		
		if (timeDrive == true) {
			double error = 0 - Robot.sensors.getGyroAngle();
			double turnPower = kP * error;
			Robot.driveTrain.m_myRobot.arcadeDrive(0.4, turnPower, false);
			Timer.delay(tme);
		}
	}
	protected void execute() {
		if (timeDrive == true) {
			Robot.driveTrain.setLeft(0);
			Robot.driveTrain.setRight(0);
			
		} else {
		double currentDistance = Robot.sensors.getDriveEncoderDistance();
		if (currentDistance >= dst - 3) {
			double error = 0 - Robot.sensors.getGyroAngle();
			double turnPower = error * kP;
			Robot.driveTrain.m_myRobot.arcadeDrive(0.4, turnPower, false);
		
		}
		}
	}
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		double currentDistance = Robot.sensors.getDriveEncoderDistance();
		if (timeDrive = true) {
			return true;
		} else {
			 if (currentDistance >= dst - 3) {
				return true;
			} else {
			return false;
			}
	}
	
	}
}
