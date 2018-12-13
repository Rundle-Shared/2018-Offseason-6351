package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LimelightTurningNew extends Command {
	
	double targetX;
	double targetY;
	double targetArea;
	double targetsVisible;
	double leftMotorVal;
	double rightMotorVal;
	
	double xChange;
	double yChange;
	
	public LimelightTurningNew() {
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
				
		double kP = 0.05;
		
		if (targetArea > 18) {
				// Too close, object is x% of frame. back up
			yChange = -0.99*RobotMap.Drive_Scaling_Auto;
		}
		else if (targetArea < 12) {
				// Too far, object is x% of frame, get closer
			yChange = 0.99*RobotMap.Drive_Scaling_Auto;
		}
		else {
			yChange = 0;
		}
		
		if (targetX < -3) {
			xChange = -1 * kP;
			}
		else if (targetX > 3) {
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
