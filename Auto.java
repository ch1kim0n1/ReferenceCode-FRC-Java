package org.firstinspires.ftc.teamcode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;

public class BasicAuto extends TimedRobot {
    
    private MotorControllerGroup leftMotors;
    private MotorControllerGroup rightMotors;
    private DifferentialDrive robotDrive;
    
    @Override
    public void robotInit() {
        
        // Initialize the motors
        CANSparkMax leftMotor1 = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
        CANSparkMax leftMotor2 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
        CANSparkMax rightMotor1 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
        CANSparkMax rightMotor2 = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
        
        // Create the speed controller groups
        leftMotors = new SpeedControllerGroup(leftMotor1, leftMotor2);
        rightMotors = new SpeedControllerGroup(rightMotor1, rightMotor2);
        
        // Set the direction of the motors
        leftMotors.setInverted(false);
        rightMotors.setInverted(true);
        
        // Create the differential drive object
        robotDrive = new DifferentialDrive(leftMotors, rightMotors);
    }
    
    @Override
    public void autonomousInit() {
        
        // Reset the timer
        Timer.reset();
    }
    
    @Override
    public void autonomousPeriodic() {
        
        // Move forward for 3 seconds at half power
        if (Timer.get() < 3.0) {
            robotDrive.arcadeDrive(0.5, 0.0);
        }
        // Stop the robot after 3 seconds
        else {
            robotDrive.stopMotor();
        }
    }
}