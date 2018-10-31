/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6351.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6351.autoPID.AutoRoutine;
import org.usfirst.frc.team6351.autoPID.DrivePID;
import org.usfirst.frc.team6351.autoPID.TurnPID;
import org.usfirst.frc.team6351.robot.auto.AngleTurn;
import org.usfirst.frc.team6351.robot.auto.DriveStraight;
import org.usfirst.frc.team6351.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6351.robot.subsystems.Sensors;

/**
 * offseason tests
 */
public class Robot extends TimedRobot {

	public static OI m_oi;
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Sensors sensors = new Sensors();
	
	public static NetworkTableInstance networktables = NetworkTableInstance.getDefault();
	public NetworkTable limelight = networktables.getTable("limelight");

	public static NetworkTableEntry light = networktables.getTable("limelight").getEntry("ledMode");
	public static NetworkTableEntry camera = networktables.getTable("limelight").getEntry("camMode");
	public static double targetX;
	public static double targetY;
	public static double targetArea;
	public static double targetsVisible;
	public static LiveWindow lw = new LiveWindow();
	

	public Encoder encoderLeft = Robot.sensors.encoderLeft;
	
	
	//!!
    Command autoCommand = new AutoRoutine();
	//!!

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		m_oi = new OI();
		light.forceSetNumber(1);
		camera.forceSetNumber(1);
		limelight.getEntry("ledMode").forceSetNumber(1);
		
		
		
		
		

	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autoCommand.start();
		Robot.sensors.encoderLeft.reset();
		Robot.sensors.gyro.reset();
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		getLimelight();
		
		SmartDashboard.putNumber("get()", encoderLeft.get());
		SmartDashboard.putBoolean("getDirection()", encoderLeft.getDirection());
		SmartDashboard.putNumber("getDistane()", encoderLeft.getDistance());
		SmartDashboard.putNumber("getDistancePerPulse()", encoderLeft.getDistancePerPulse());
		SmartDashboard.putNumber("getRate()", encoderLeft.getRate());
		SmartDashboard.putNumber("getRaw()", encoderLeft.getRaw());
		SmartDashboard.putNumber("getEncodingScale()", encoderLeft.getEncodingScale());
		SmartDashboard.putNumber("GyroAngle()", Robot.sensors.getGyroAngle());
		SmartDashboard.putNumber("accelX()", Robot.sensors.getXAccel());
		SmartDashboard.putNumber("accelY()", Robot.sensors.getYAccel());
		SmartDashboard.putNumber("gyroRate()", Robot.sensors.getGyroRate());		


	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		getLimelight();
		SmartDashboard.putNumber("LimeCamMode", limelight.getEntry("ledMode").getDouble(0));
		

		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.add(driveTrain);
	}
	
	public void getLimelight() {
		targetX = limelight.getEntry("tx").getDouble(0);
		targetY = limelight.getEntry("ty").getDouble(0);
		targetArea = limelight.getEntry("ta").getDouble(0);
		targetsVisible = limelight.getEntry("tv").getDouble(0);
		
		
	}
	
	public void smartD(String key, double number) {
		SmartDashboard.putNumber(key, number);
	}
	
}
;