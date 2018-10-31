package org.usfirst.frc.team6351.autoPID;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRoutine extends CommandGroup {
	
	public AutoRoutine() {
		
	
	
	addSequential(new DrivePID(84));
	Timer.delay(0.5);
	addSequential(new TurnPID(180));
	Timer.delay(0.5);
	addSequential(new DrivePID(72));
	
	}
}
