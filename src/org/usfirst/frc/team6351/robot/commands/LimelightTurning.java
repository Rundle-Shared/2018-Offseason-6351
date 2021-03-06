package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import edu.wpi.first.wpilibj.command.Command;

public class LimelightTurning extends Command {
	
	double targetX;
	double targetY;
	double targetArea;
	double targetsVisible;
	double leftMotorVal;
	double rightMotorVal;
	
	double xChange;
	double yChange;
	
	public LimelightTurning() {
		requires(Robot.driveTrain);
	}
	
	public void initialize() {
		targetX = Robot.targetX;
		targetY = Robot.targetY;
		targetArea = Robot.targetArea;
		targetsVisible = Robot.targetsVisible;
		
		leftMotorVal = 0;
		rightMotorVal = 0;
		
	Robot.light.forceSetNumber(0);
	Robot.camera.forceSetNumber(0);
		
	}
	
	public void execute() {
		targetX = Robot.targetX;
		targetY = Robot.targetY;
		targetArea = Robot.targetArea;
		targetsVisible = Robot.targetsVisible;
		
		double error = Math.abs(targetX);
		//horizontal distance from 0,0
		double kP;
		// Steering constant to reduce speeds, similarly to a scaling factor
		
		if (error < 3.0) {
			// reducing the scaling factor as the target gets closer to the center
			kP =0;
		}
		else {
			kP = 0.4;
		}
		
		
			
			
			if (targetArea > 30) {
					// Too close, object is x% of frame. back up
				yChange = -0.99*RobotMap.Drive_Scaling_Auto;
			}
			else if (targetArea < 5) {
					// Too far, object is x% of frame, get closer
				yChange = 0.99*RobotMap.Drive_Scaling_Auto;
			}
			else {
				yChange = 0;
			}
		
	
			
			
				
			
			if (targetX < -10) {
				xChange = -1 * kP;
				}
			else if (targetX > 10) {
				xChange = 1 * kP;
			}
			else {
				xChange = 0;
			}
			
			
		
			
		
			
		SmartDashboard.putNumber("tx", targetX);
		SmartDashboard.putNumber("ty", targetY);
		SmartDashboard.putNumber("targetArea", targetArea);
		SmartDashboard.putNumber("xChange", xChange);
		SmartDashboard.putNumber("yChange", yChange);
		SmartDashboard.putNumber("leftMotorVal", leftMotorVal);
		SmartDashboard.putNumber("rightMotorVal", rightMotorVal);
		
		
		Robot.driveTrain.m_myRobot.arcadeDrive(yChange, xChange);
			
		}
		
		
		
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		
		return false;
	}
	
	public void interrupted() {
		Robot.light.forceSetNumber(1);
		Robot.camera.forceSetNumber(1);
		
	}

}
