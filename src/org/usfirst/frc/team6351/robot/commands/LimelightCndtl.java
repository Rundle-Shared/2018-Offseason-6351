package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.autoPID.LimeDrive;
import org.usfirst.frc.team6351.autoPID.LimeTurn;
import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LimelightCndtl extends Command {
	double turn;
	double speed = 0;
	
	private Command lTurn = new LimeTurn();
	private Command lDrive = new LimeDrive();	
	
	public LimelightCndtl() {
		requires(Robot.driveTrain);
	}
	
	public void initialize() {
		Robot.light.forceSetNumber(0);
		Robot.camera.forceSetNumber(0);
		
		lTurn.start();
		lDrive.start();
	}

	public void execute() {
		turn = LimeTurn.value;
		speed = LimeDrive.value;
		
		SmartDashboard.putNumber("turn", turn);
		SmartDashboard.putNumber("speed", speed);
		SmartDashboard.putData(lTurn);
		SmartDashboard.putData(lDrive);
		
		Robot.driveTrain.m_myRobot.arcadeDrive(speed, turn);
		
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	protected void isInterrupted() {
		Robot.light.forceSetNumber(1);
		Robot.camera.forceSetNumber(1);
		
		lTurn.cancel();
		lDrive.cancel();
		}
}
