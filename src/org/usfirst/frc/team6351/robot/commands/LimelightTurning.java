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
		
	Robot.light.setNumber(0);
		
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
			kP = Math.pow((0.10*error), 2)+0.1;
		}
		else {
			kP = 0.4;
		}
		
		
		if (targetsVisible == 1 && ((error < 10))) {
			
			
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
		
	
			
			} else {
				
			
			if (targetX < -10) {
				xChange = -1 * kP;
				}
			else if (targetX > 10) {
				xChange = 1 * kP;
			}
			else {
				xChange = 0;
			}
			
			}
		
			
		if (yChange < 0) {
			//too close
			leftMotorVal = yChange;
			rightMotorVal = yChange * -1;
		}
		else if (yChange > 0) {
			// too far
			leftMotorVal = yChange;
			rightMotorVal = yChange*-1;
		}
		else if (xChange < 0) {
			//too far left
			leftMotorVal = 0;
			rightMotorVal = xChange;
		}
		else if (xChange > 0) {
			// too far right
			leftMotorVal = xChange;
			rightMotorVal = 0;
		}
			
		SmartDashboard.putNumber("tx", targetX);
		SmartDashboard.putNumber("ty", targetY);
		SmartDashboard.putNumber("targetArea", targetArea);
		SmartDashboard.putNumber("xChange", xChange);
		SmartDashboard.putNumber("yChange", yChange);
		SmartDashboard.putNumber("leftMotorVal", leftMotorVal);
		SmartDashboard.putNumber("rightMotorVal", rightMotorVal);
		
		
		Robot.driveTrain.setLeft(leftMotorVal);
		Robot.driveTrain.setRight(rightMotorVal);
			
		}
		
		
		
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		Robot.light.setNumber(1);
		return false;
	}
	
	public void interrupted() {
	
	}

}
