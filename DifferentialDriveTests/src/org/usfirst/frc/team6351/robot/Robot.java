/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6351.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */

/*
 * Righjsfvt y joystick controls speed Right x controls turn
 */
public class Robot extends IterativeRobot {
	private DifferentialDrive m_myRobot;
	VictorSP DriveMotorFL = new VictorSP(1);
	VictorSP DriveMotorBL = new VictorSP(2);
	VictorSP DriveMotorFR = new VictorSP(3);
	VictorSP DriveMotorBR = new VictorSP(4);

	public Joystick xBoxControl = new Joystick(0);
	SpeedControllerGroup m_left = new SpeedControllerGroup(DriveMotorFL, DriveMotorBL);
	SpeedControllerGroup m_right = new SpeedControllerGroup(DriveMotorFR, DriveMotorBR);
	public double leftTrig = xBoxControl.getRawAxis(2);
	public double rightTrig = xBoxControl.getRawAxis(3);
	public double curveReduction;
	@Override
	public void robotInit() {
		m_myRobot = new DifferentialDrive(m_left, m_right);

	}

	@Override
	public void teleopPeriodic() {
		curveReduction = 0.95;
		m_myRobot.arcadeDrive(xBoxControl.getRawAxis(1), xBoxControl.getRawAxis(0));
	}
}
