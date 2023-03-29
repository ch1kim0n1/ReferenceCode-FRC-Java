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

        //intake
        CANSparkMax m_intake = new CANSparkMax(13, CANSparkMaxLowLevel.MotorType.kBrushed);
        
        // Create the speed controller groups
        leftMotors = new SpeedControllerGroup(leftMotor1, leftMotor2);
        rightMotors = new SpeedControllerGroup(rightMotor1, rightMotor2);

        int AutoMode = 0;
        
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

        //This is going to be basic push and go code
        if(AutoMode == 0){
            if(Timer.get() < 0){
                robotDrive.arcadeDrive(0,0);
            }
            else if(Timer.get() < 0.35){
                //moving backwards
                robotDrive.tankDrive(-0.5, -0.5);
            }
            else if (Timer.get() < 2){ //need to check 2 and replace as needed
                //moving forward out of the community zone
                robotDrive.tankDrive(0.65, 0.65);
            }
            else if (Timer.get() < 15){
                robotDrive.tankDrive(0, 0);
            }
            else{
                robotDrive.tankDrive(0, 0);
            }
        }
        //this mode will just move our of the community zone
        else if (AutoMode == 1){
            if(Timer.get() < 0){
                robotDrive.arcadeDrive(0,0);
            }
            else if (Timer.get() < 1.5){
                //moving forward out of the community zone
                robotDrive.tankDrive(0.65, 0.65);
            }
            else if (Timer.get() < 15){
                robotDrive.tankDrive(0, 0);
            }
            else{
                robotDrive.tankDrive(0, 0);
            }
        }
        //this code will be working with intake, so basically it will outtake and then go backwards
        else if(AutoMode == 2){
            if(Timer.get() < 0){
                //max power to intake to outtake the object
                m_intake.Set(1);
            }
            else if (Timer.get() < 1.5){
                m_intake.Set(1);
            }
            else if (Timer.get() < 4){
                m_intake.Set(0);
                //moving forward out of the community zone
                robotDrive.tankDrive(0.65, 0.65);
            }
            else if (Timer.get() < 15){
                robotDrive.tankDrive(0, 0);
            }
            else{
                robotDrive.tankDrive(0, 0);
            }
        }
    }
}