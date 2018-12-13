package org.usfirst.frc.team6351.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.mindsensors.CANLight;
public class CANLights extends Subsystem {
	
	CANLight lightstrip;
	
	public CANLights(int input) {
		lightstrip = new CANLight(input);
	}
	

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public void setColor(int red, int green, int blue) {
		lightstrip.showRGB(red, green, blue);
	}
	
	public void setRegister(int index, int time, int red, int green, int blue) {
		lightstrip.writeRegister(index, time, red, green, blue);
	}
	
	public void showRegister(int index) {
		lightstrip.showRegister(index);
	}
	
	public void flash(int index) {
		lightstrip.flash(index);
	}

}
