package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Blink extends Command {
	int red;
	int green;
	int blue;
	
	public Blink(int red, int blue, int green) {
		requires(Robot.canlight);
		this.red = red;
		this.blue = blue;
		this.green = green;
	}

	protected void execute() {
		Robot.canlight.setColor(red, green, blue);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	protected void interrupted() {
		Robot.canlight.setColor(0, 0, 0);
	}

}
