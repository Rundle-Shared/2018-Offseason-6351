package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TriggerDrive extends Command {

	public TriggerDrive() {
		requires(Robot.driveTrain);
	}

	protected void initialize() {

	}

	protected void execute() {
		double rightTrigger = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Right_Trigger);
		double leftTrigger = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Left_Trigger);
		double leftJoystickXAxis = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Left_X_Axis);

		if (Math.abs(leftJoystickXAxis) < RobotMap.JoystickDeadzone) {
			leftJoystickXAxis = 0;
		}

		else {
			if (leftJoystickXAxis < 0) {
				SmartDashboard.putNumber("Original X Axis Input", leftJoystickXAxis);
				leftJoystickXAxis = (((leftJoystickXAxis + 0.99) * 0.99) / (-1 * RobotMap.TriggerDeadzone));
				SmartDashboard.putNumber("Modified Negative X Axis", leftJoystickXAxis);
			}
			if (leftJoystickXAxis > 0) {
				SmartDashboard.putNumber("Original X Axis Input", leftJoystickXAxis);
				leftJoystickXAxis = (((leftJoystickXAxis - RobotMap.TriggerDeadzone) * 0.99)
						/ (0.99 - RobotMap.TriggerDeadzone));
				SmartDashboard.putNumber("Modified Positive X Axis Input", leftJoystickXAxis);
			}
		}

		if (Math.abs(rightTrigger) < RobotMap.TriggerDeadzone) {
			rightTrigger = 0;
		} else {
			SmartDashboard.putNumber("Original Right Input", rightTrigger);
			rightTrigger = (((rightTrigger - RobotMap.TriggerDeadzone) / (1 - RobotMap.TriggerDeadzone)));
			SmartDashboard.putNumber("Modified Right Input", rightTrigger);

		}

		if (Math.abs(leftTrigger) < RobotMap.TriggerDeadzone) {
			leftTrigger = 0;
		}

		else {
			SmartDashboard.putNumber("Original Left Input", leftTrigger);
			leftTrigger = (((leftTrigger - RobotMap.TriggerDeadzone) / (1 - RobotMap.TriggerDeadzone)));
			SmartDashboard.putNumber("Modified Left Input", leftTrigger);

		}

		double rightMotorValue = (rightTrigger - leftTrigger + leftJoystickXAxis) * RobotMap.Drive_Scaling_Teleop;
		double leftMotorValue = (rightTrigger - leftTrigger - leftJoystickXAxis) * RobotMap.Drive_Scaling_Teleop
				* RobotMap.Curve_Reduction_Factor * (-1);

		SmartDashboard.putNumber("Right Motor Value", rightMotorValue);
		SmartDashboard.putNumber("Left Motor Value", leftMotorValue);

		if (rightMotorValue > RobotMap.MAX_ROBOT_SPEED) {
			rightMotorValue = RobotMap.MAX_ROBOT_SPEED;
		}

		if (rightMotorValue < RobotMap.MIN_ROBOT_SPEED) {
			rightMotorValue = RobotMap.MIN_ROBOT_SPEED;
		}

		if (leftMotorValue > RobotMap.MAX_ROBOT_SPEED) {
			leftMotorValue = RobotMap.MAX_ROBOT_SPEED;
		}

		if (leftMotorValue < RobotMap.MIN_ROBOT_SPEED) {
			leftMotorValue = RobotMap.MIN_ROBOT_SPEED;
		}

		SmartDashboard.putNumber("Final Right Motor Value", rightMotorValue);
		SmartDashboard.putNumber("Final Left Motor Value", leftMotorValue);

		Robot.driveTrain.setLeft(leftMotorValue);
		Robot.driveTrain.setRight(rightMotorValue);

	}

	@Override
	protected boolean isFinished() {

		return false;
	}

	protected void end() {

	}

	protected void intterupted() {

	}
}
