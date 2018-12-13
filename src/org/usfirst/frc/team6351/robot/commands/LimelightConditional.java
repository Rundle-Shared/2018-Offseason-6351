package org.usfirst.frc.team6351.robot.commands;
import org.usfirst.frc.team6351.autoPID.LimeTurn;
import org.usfirst.frc.team6351.autoPID.LimeDrive;


import org.usfirst.frc.team6351.robot.Robot;


import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class LimelightConditional extends ConditionalCommand {
	double targetX;
	double targetY;
	double targetArea;
	double targetsVisible;
	

	
	public LimelightConditional() {
		super(new LimeDrive(), new LimeTurn());
	}
	
	protected void _initialize() {
		targetX = Robot.targetX;
		targetY = Robot.targetY;
		targetArea = Robot.targetArea;
		targetsVisible = Robot.targetsVisible;
		Robot.light.setNumber(0);
		Robot.camera.setNumber(0);
	}

	@Override
	protected boolean condition() {
		if (LimeTurn.isFinished == true) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean isFinished() {
		if (LimeTurn.isFinished == true && LimeDrive.isFinished == true) {
			return true;
		}
		else {
		return false;
		}			//relies on button press 
		// otherwise finish if targetX and targetArea criteria are met
	}
	protected void interrupted() {
		end();
	}
	
	protected void end() {
		//Robot.light.setNumber(1);
		//Robot.camera.setNumber(1); 
	}
	

}
