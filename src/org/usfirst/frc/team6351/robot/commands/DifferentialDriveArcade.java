package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



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
		 
		 SmartDashboard.putNumber("rt", rightTrigger);
		 SmartDashboard.putNumber("lt", leftTrigger);
		 SmartDashboard.putNumber("lj", leftJoystickXAxis);
		
		
		 //defining speed
		 speed = (rightTrigger - leftTrigger)*-1;
		 rotation = leftJoystickXAxis;
		 
		 speed = speed * RobotMap.Drive_Scaling_Teleop;
		 
		 SmartDashboard.putNumber("speed", speed);
		 SmartDashboard.putNumber("rotation", rotation);
		 
		 
		
		Robot.driveTrain.m_myRobot.setDeadband(0.05);
		
		if (Math.abs(speed) < 0.03 && Math.abs(rotation) < 0.03) {
			Robot.driveTrain.arcadeDrive(0, 0, false);
		}
		else if(Math.abs(rotation) < 0.03) {
			
		
			if (Math.abs(speed) < RobotMap.TriggerDeadzone) {
					speed = 0;
			} else {
				//speed = applyDeadband(speed, 0.05);
				Robot.driveTrain.m_myRobot.arcadeDrive(speed, 0);
			}
			}
			
			else if (Math.abs(speed) < 0.03) {
			
				if (Math.abs(rotation) < RobotMap.JoystickDeadzone) {
					rotation = 0;
				} else {
					//rotation = applyDeadband(rotation, 0.05);
					Robot.driveTrain.m_myRobot.arcadeDrive(0, rotation);
					//Robot.driveTrain.setLeft(rotation);
					//Robot.driveTrain.setRight(rotation);
				}
				
			}
			
		else {
		//speed = applyDeadband(speed, 0.05);
		//rotation = applyDeadband(rotation, 0.05);
		Robot.driveTrain.arcadeDrive(speed, rotation, false);
		
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
	
	protected double applyDeadband(double value, double deadband) {
	    if (Math.abs(value) > deadband) {
	      if (value > 0.0) {
	        return (value - deadband) / (1.0 - deadband);
	      } else {
	        return (value + deadband) / (1.0 - deadband);
	      }
	    } else {
	      return 0.0;
	    }
	  }

}
