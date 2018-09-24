package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DifferentialDriveArcade extends Command {
	
	public DifferentialDrive m_myRobot;
	VictorSP DriveMotorFL = new VictorSP(1);
	VictorSP DriveMotorBL = new VictorSP(2);
	VictorSP DriveMotorFR = new VictorSP(3);
	VictorSP DriveMotorBR = new VictorSP(4);
	
	SpeedControllerGroup m_left = new SpeedControllerGroup(DriveMotorFL, DriveMotorBL);
	SpeedControllerGroup m_right = new SpeedControllerGroup(DriveMotorFR, DriveMotorBR);
	
	
	
	public DifferentialDriveArcade() {
		m_myRobot = new DifferentialDrive(m_left, m_right);
		requires(Robot.driveTrain);
	}

	public void initialize() {
		
	}
	
	public void execute() {
		
		double rightTrigger = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Right_Trigger);
		double leftTrigger = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Left_Trigger);
		double leftJoystickXAxis = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Left_X_Axis);
		
		if (Math.abs(rightTrigger) < RobotMap.TriggerDeadzone) {
			rightTrigger = 0;
		}
		else {
			rightTrigger *= 0.99;
		}
		
		if (Math.abs(leftTrigger) < RobotMap.TriggerDeadzone) {
			leftTrigger = 0;
		}
		else {
			leftTrigger *= 0.99;
		}
		if (Math.abs(leftJoystickXAxis) < RobotMap.TriggerDeadzone) {
			leftJoystickXAxis = 0;
		}
		else {
			leftJoystickXAxis *= 0.99;
		}

		double speed = (rightTrigger - leftTrigger);
		double rotation = leftJoystickXAxis;
		
		m_myRobot.arcadeDrive(speed, rotation);
		
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
