package org.usfirst.frc.team6351.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team6351.robot.commands.*;

public class DriveTrain extends Subsystem {
	VictorSP DriveMotorFL = new VictorSP(1);
	VictorSP DriveMotorBL = new VictorSP(2);
	VictorSP DriveMotorFR = new VictorSP(3);
	VictorSP DriveMotorBR = new VictorSP(4);
	
	SpeedControllerGroup m_left = new SpeedControllerGroup(DriveMotorFL, DriveMotorBL);
	SpeedControllerGroup m_right = new SpeedControllerGroup(DriveMotorFR, DriveMotorBR);

	public DifferentialDrive m_myRobot;
	
	public DriveTrain() {
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
	
	public void CombinedLimelight(double spd, double rot) {
		
	}

	// TODO Auto-generated method stub

}
