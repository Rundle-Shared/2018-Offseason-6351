package org.usfirst.frc.team6351.autoPID;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRoutine extends CommandGroup {
	
	public AutoRoutine() {
		
	
	
	addSequential(new DrivePID(120, 25));
	Timer.delay(0.15);
	addSequential(new TurnPID(180, 25));
	Timer.delay(0.15);
	addSequential(new DrivePID(120, 25));
	Timer.delay(0.15);
	
	
	}
}
