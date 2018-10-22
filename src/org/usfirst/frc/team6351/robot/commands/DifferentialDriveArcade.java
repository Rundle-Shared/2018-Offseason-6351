package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;



public class DifferentialDriveArcade extends Command {
	
	double rightTrigger = 0;
	double leftTrigger = 0;
	double leftJoystickXAxis = 0;
	
	
	
	private double speed = 0;
	private double rotation = 0;
	
	public DifferentialDriveArcade() {
		
		requires(Robot.driveTrain);
	}

	public void initialize() {
		
	}
	
	public void execute() {
		// importing controls
		 rightTrigger = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Right_Trigger);
		 leftTrigger = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Left_Trigger);
		 leftJoystickXAxis = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Left_X_Axis);
		
		
		 //defining speed
		 speed = (rightTrigger - leftTrigger);
		 rotation = leftJoystickXAxis;
		 
		 speed = speed * RobotMap.Drive_Scaling_Teleop;
		 
		
		if(Math.abs(speed) < 0.02) {
					
					/*
					 * if (Math.abs(speed) < RobotMap.TriggerDeadzone) {
					speed = 0;
				}
		
				if (Math.abs(speed) < RobotMap.TriggerDeadzone) {
					speed = 0;
				}
				*/
					
					
				if (Math.abs(rotation) < RobotMap.JoystickDeadzone) {
					rotation = 0;
				}
					 
			Robot.driveTrain.setLeft(rotation);
			Robot.driveTrain.setRight((rotation) * -1);
		}
		else {
			//Robot.driveTrain.m_myRobot.setDeadband(0.2);
		Robot.driveTrain.m_myRobot.arcadeDrive(speed, rotation);
		}
		
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void interrupted() {
		//end();
	}

}
