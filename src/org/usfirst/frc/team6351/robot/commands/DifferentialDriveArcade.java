package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class DifferentialDriveArcade extends Command {
	
	double rightTrigger = 0;
	double leftTrigger = 0;
	double leftJoystickXAxis = 0;
	int test = 0;
	double kRamping = 0.2; //max speed increase per time cycle. Results in gradual acceleration, 1 second to hit full speed
	double prevSpeed = 0;
	
	
	
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
		 // trying to ramp motor acceleration to a max increase of 0.2 per 20ms
		 
		 
		 
		
		Robot.driveTrain.m_myRobot.setDeadband(0.1);
		
		
		if(Math.abs(rotation) < 0.125) {
			
		
			
				//speed = applyDeadband(speed, 0.05);
				if (test == 0) {
					Robot.sensors.gyro.reset();
					test = test+1;
				}
				double eKp = 0.05;
				double currentAngle = Robot.sensors.getGyroAngle();
				double error = 0- currentAngle;
				double turnPower = error*eKp + 0.1; //takes it to clear the deadband
				Robot.driveTrain.m_myRobot.arcadeDrive(speed, turnPower);
			
		}
			
		else if (Math.abs(speed) == 0) {
			test = 0;
			
					//rotation = applyDeadband(rotation, 0.05);
					Robot.driveTrain.m_myRobot.arcadeDrive(0, rotation);
					//Robot.driveTrain.setLeft(rotation);
					//Robot.driveTrain.setRight(rotation);
				
				
			}
		
		else if (Math.abs(speed) == 0 && Math.abs(rotation) == 0) {
			test = 0;
			Robot.driveTrain.arcadeDrive(0, 0, false);
		}
			
		else {
			test = 0;
		//speed = applyDeadband(speed, 0.05);
		//rotation = applyDeadband(rotation, 0.05);
		Robot.driveTrain.arcadeDrive(speed, rotation, true);
		
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
