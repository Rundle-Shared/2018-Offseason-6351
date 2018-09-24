package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;


import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {
	
	public ArcadeDrive() {
		requires(Robot.driveTrain);
		
	}
	double leftMotorOutput;
	double rightMotorOutput;
	
	public void initialize() {
	
	}
	
	public void execute() {
		
		
		double rightTrigger = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Right_Trigger);
		double leftTrigger = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Left_Trigger);
		double leftJoystickXAxis = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Left_X_Axis);
		
		
		// Right Trigger
		if (Math.abs(rightTrigger) < RobotMap.TriggerDeadzone) {
			rightTrigger = 0;
		}
		else { 
			rightTrigger = rightTrigger * 0.99;	
			}
		
		// Left trigger
		if (Math.abs(leftTrigger) < RobotMap.TriggerDeadzone) {
			leftTrigger = 0;
		}
		else { 
			leftTrigger = leftTrigger * 0.99;
		}
		// Joystick Horizontal Axis
		if (Math.abs(leftJoystickXAxis) < RobotMap.JoystickDeadzone) {
			leftJoystickXAxis = 0;
		}
		else {
			leftJoystickXAxis = leftJoystickXAxis * 0.99;
		}

		
		double driveSpeed = (rightTrigger - leftTrigger);
		double driveRotation = leftJoystickXAxis;

		
		
		
		
		
		
	
	if (driveSpeed > 0) {
		
		if (driveRotation > 0) {
			
			leftMotorOutput = driveSpeed;
			rightMotorOutput = driveSpeed - driveRotation;
		}
			
		else if (driveRotation < 0 ){
			
			leftMotorOutput	 = driveSpeed + driveRotation;
			rightMotorOutput = driveSpeed;
		}
		else if (driveRotation == 0) {
			leftMotorOutput = driveSpeed;
			rightMotorOutput = driveSpeed;
		}
	}
	
	else if (driveSpeed == 0 ){
			
			leftMotorOutput = 0;
			rightMotorOutput = 0;
			}
		
		
	else {
			
			if (driveRotation > 0) {
				
				leftMotorOutput = driveSpeed + driveRotation;
				rightMotorOutput = driveSpeed;
			}
				
			else if (driveRotation < 0 ){
				
				leftMotorOutput	 = driveSpeed;
				rightMotorOutput = driveSpeed - driveRotation;
			}
			else if (driveRotation == 0) {
				leftMotorOutput = driveSpeed;
				rightMotorOutput = driveSpeed;
			}
	}
	
	leftMotorOutput = leftMotorOutput * RobotMap.Drive_Scaling_Teleop;
	rightMotorOutput = rightMotorOutput * RobotMap.Drive_Scaling_Teleop * RobotMap.Curve_Reduction_Factor * -1;
	
	if (rightMotorOutput > RobotMap.MAX_ROBOT_SPEED) {
		rightMotorOutput = RobotMap.MAX_ROBOT_SPEED;
	}

	if (rightMotorOutput < RobotMap.MIN_ROBOT_SPEED) {
		rightMotorOutput = RobotMap.MIN_ROBOT_SPEED;
	}

	if (leftMotorOutput > RobotMap.MAX_ROBOT_SPEED) {
		leftMotorOutput = RobotMap.MAX_ROBOT_SPEED;
	}

	if (leftMotorOutput < RobotMap.MIN_ROBOT_SPEED) {
		leftMotorOutput = RobotMap.MIN_ROBOT_SPEED;
	}
	
	Robot.driveTrain.setLeft(leftMotorOutput);
	Robot.driveTrain.setRight(rightMotorOutput);
	
	
	
	}
		
		

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void interrupted() {
		Robot.driveTrain.setLeft(0);
		Robot.driveTrain.setRight(0);
	}

}
