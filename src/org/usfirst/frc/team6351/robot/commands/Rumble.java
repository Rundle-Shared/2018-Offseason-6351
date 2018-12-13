package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Rumble extends Command {
	
	String side;
	double value;
	
	public Rumble(String type, double val) {
	
		side = type;
		value = val;
	}
	
	public void execute() {
		switch(side) {
		case "l" : Robot.m_oi.setRumbleL(value);
			break;
		case "r": Robot.m_oi.setRumbleR(value);
			break;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
		
		
	}
	
	@Override
	protected void end() {
		switch(side) {
		case "l" : Robot.m_oi.setRumbleL(0);
			break;
		case "r": Robot.m_oi.setRumbleR(0);
			break;
		}
	}
	
	protected void interrupted() {
		end();
	}

}
