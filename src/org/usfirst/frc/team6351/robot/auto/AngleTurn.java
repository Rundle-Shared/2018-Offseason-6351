package org.usfirst.frc.team6351.robot.auto;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AngleTurn extends Command {
	double currentAngle;
	double startAngle;
	double targetAngle;
	private double m_angle;
	double threshold = 2;
	
	public AngleTurn(double angle) {
		 m_angle = angle;
		 requires(Robot.driveTrain);
		 requires(Robot.sensors);

	}
	
	
	
	public void initialize() {
		//Robot.sensors.gyro.reset();
		startAngle = Robot.sensors.getGyroAngle();
		targetAngle = (startAngle + m_angle);
		
	}
	
	public void execute() {
		currentAngle = Robot.sensors.getGyroAngle();
		double error = Math.abs(targetAngle - currentAngle);
		double kP;
		
		if (error < 10) {
			kP = Math.pow((error*0.09), 2) + 0.1;
		}
		else {
			kP = 1;
		}
		
		if (targetAngle < currentAngle) {
			//turning left
			Robot.driveTrain.m_myRobot.arcadeDrive(0, (0.4*kP*-1));
			//Robot.driveTrain.setRight(0.4*kP);
			//Robot.driveTrain.setLeft(0.4*kP*-1);
		}
		else if (targetAngle > currentAngle) {
			//turning right
			Robot.driveTrain.m_myRobot.arcadeDrive(0, (0.4*kP));
			//Robot.driveTrain.setLeft(0.4*kP);
			//Robot.driveTrain.setRight(0.4*kP*-1);
		}
		
	}
	@Override
	protected boolean isFinished() {
		if (Math.abs(targetAngle - Robot.sensors.getGyroAngle()) < threshold) {
			return true;
		}
		else {
			return false;
			
		}
	}

}
