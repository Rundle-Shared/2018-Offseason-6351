package org.usfirst.frc.team6351.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6351.robot.commands.*;

public class DriveTrain extends Subsystem {
	VictorSP DriveMotorFL = new VictorSP(1);
	VictorSP DriveMotorBL = new VictorSP(2);
	VictorSP DriveMotorFR = new VictorSP(3);
	VictorSP DriveMotorBR = new VictorSP(4);

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}

	public void setLeft(double speed) {
		DriveMotorFL.set(speed);
		DriveMotorBL.set(speed);
	}

	public void setRight(double speed) {
		DriveMotorFR.set(speed);
		DriveMotorBR.set(speed);
	}

	// TODO Auto-generated method stub

}
