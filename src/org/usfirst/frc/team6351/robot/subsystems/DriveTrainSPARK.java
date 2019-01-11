package org.usfirst.frc.team6351.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team6351.robot.commands.*;

public class DriveTrainSPARK extends Subsystem {
	Spark DriveMotorFL = new Spark(3);
	Spark DriveMotorBL = new Spark(2);
	Spark DriveMotorFR = new Spark(0);
	Spark DriveMotorBR = new Spark(1);
	
	//SPARKS for AL is 3,2,0,1
	
	SpeedControllerGroup m_left = new SpeedControllerGroup(DriveMotorFL, DriveMotorBL);
	SpeedControllerGroup m_right = new SpeedControllerGroup(DriveMotorFR, DriveMotorBR);

	public DifferentialDrive m_myRobot;
	
	public DriveTrainSPARK() {
		m_myRobot = new DifferentialDrive(m_left, m_right);
	}
	
	@Override
	public void initDefaultCommand() {
		//!!!!!!
		setDefaultCommand(new DifferentialDriveVaried());
		//!!!!!
	}


		// TODO Auto-generated method stub
		
	

	public void setLeft(double speed) {
		DriveMotorFL.set(speed);
		DriveMotorBL.set(speed);
	}

	public void setRight(double speed) {
		DriveMotorFR.set(speed);
		DriveMotorBR.set(speed);
	}
	public void arcadeDrive(double speed, double rotation, boolean squared) {
		m_myRobot.arcadeDrive(speed, rotation, squared);
	}
	
	

	// TODO Auto-generated method stub

}
