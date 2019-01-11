package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class DifferentialDriveVaried extends Command {
	
	double rightTrigger = 0;
	double leftTrigger = 0;
	double leftJoystickXAxis = 0;
	boolean reset;
	
	
	
	private double turnAxis;
	private double speed = 0;
	private double rotation = 0;
	

	public DifferentialDriveVaried() {
		
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
		 turnAxis = leftJoystickXAxis;
		 
		 speed = speed * RobotMap.Drive_Scaling_Teleop;
				 
		 
		 //PARABOLIC SPEED FUNCTION
		 if (Math.abs(turnAxis) < 0.10) {
				rotation = 0;
			}
			else if (turnAxis < -0.10) {
				rotation = (-0.4321*(Math.pow(-1*(turnAxis+0.1), 2)))-0.25;
			}
			else if (turnAxis > 00.10) {
				rotation = (0.4321*(Math.pow(turnAxis-0.1, 2)))+0.25;
			}
		 
		 SmartDashboard.putNumber("speed", speed);
		 SmartDashboard.putNumber("rotation", rotation);
		 
		 
		//Small deadband to prevent virtual glitch
		Robot.driveTrain.m_myRobot.setDeadband(0.02);
		
		//Calling different drive actions based off inputs to ensure smoother operation
		
		//regular drive
		if (speed != 0 && Math.abs(rotation) !=0) {
			reset = true;
			Robot.driveTrain.m_myRobot.arcadeDrive(speed, rotation, false);
		}
		//Drive straight only or turn only
		else if (speed !=0 || Math.abs(rotation) !=0) {
			if (speed != 0 && rotation == 0) {
				if (reset == true) {
					//Robot.sensors.gyro.reset();
					reset = false;
				}
				//double eKp = 0.05;
				//double currentAngle = Robot.sensors.getGyroAngle();
				//double error = 0- currentAngle;
				//double turnPower = error*eKp + 0.1; //takes it to clear the deadband
				
				Robot.driveTrain.m_myRobot.arcadeDrive(speed, 0, false);
			}
			
			else if (speed == 0 && Math.abs(rotation) !=0) {
				reset = true;
				Robot.driveTrain.m_myRobot.arcadeDrive(0, rotation, false);
			}
		}
		//Stop
		else {
			reset = true;
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
