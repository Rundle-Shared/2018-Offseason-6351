package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Same but Joystick dictates speed

public class DifferentialDriveAlternate extends Command {
	
	double rightTrigger = 0;
	double leftTrigger = 0;
	double leftJoystickYAxis = 0;
	double test;
	
	
	
	private double turnAxis;
	private double speed = 0;
	private double rotation = 0;
	
	public DifferentialDriveAlternate() {
		
		requires(Robot.driveTrain);
	}

	public void initialize() {
		
	}
	
	public void execute() {
		// importing controls
		 rightTrigger = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Right_Trigger);
		 leftTrigger = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Left_Trigger);
		 leftJoystickYAxis = Robot.m_oi.driverControllerAxisVaule(RobotMap.Controller1_Left_Y_Axis);
		 
		 SmartDashboard.putNumber("rt", rightTrigger);
		 SmartDashboard.putNumber("lt", leftTrigger);
		 SmartDashboard.putNumber("lj", leftJoystickYAxis);
		
		
		 //defining speed
		 speed = (leftJoystickYAxis)*-1;
		 turnAxis = rightTrigger - leftTrigger;
		 
		 speed = speed * RobotMap.Drive_Scaling_Teleop;
		 
		 if (Math.abs(speed) < 0.10) {
			 speed = 0;
		 }
		 
		 
		 
		if (turnAxis < 0) {
			rotation = (-0.4321*(Math.pow(-1*(turnAxis+0.1), 2)))-0.25;
		}
		else if (turnAxis > 0) {
			rotation = (0.4321*(Math.pow(turnAxis-0.1, 2)))+0.25;
		}
		 
		 SmartDashboard.putNumber("speed", speed);
		 SmartDashboard.putNumber("rotation", rotation);
		 
		 
		
		Robot.driveTrain.m_myRobot.setDeadband(0.02);
		
		if (speed != 0 && Math.abs(rotation) !=0) {
			test = 0;
			Robot.driveTrain.m_myRobot.arcadeDrive(speed, rotation, false);
		}
		else if (speed !=0 || Math.abs(rotation) !=0) {
			if (speed != 0 && rotation == 0) {
				if (test == 0) {
					//Robot.sensors.gyro.reset();
					test++;
				}
				//double eKp = 0.05;
				//double currentAngle = Robot.sensors.getGyroAngle();
				//double error = 0- currentAngle;
				//double turnPower = error*eKp + 0.15; //takes it to clear the deadband
				
				Robot.driveTrain.m_myRobot.arcadeDrive(speed, 0, false);
				//Robot.driveTrain.m_myRobot.tankDrive(speed*0.95, -1*speed);
			}
			else if (speed == 0 && Math.abs(rotation) !=0) {
				test = 0;
				Robot.driveTrain.m_myRobot.arcadeDrive(0, rotation, false);
				//Robot.driveTrain.m_myRobot.curvatureDrive(0, rotation, true);
				
				//FLmotor.set(rotation);
				//BLmotor.set(rotation);
				//FRmotor.set(rotation);
				//BRmotor.set(rotation);
				
			}
		}
		else {
			test = 0;
			Robot.driveTrain.m_myRobot.arcadeDrive(0, 0);
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
