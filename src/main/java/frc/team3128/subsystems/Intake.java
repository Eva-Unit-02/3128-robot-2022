package frc.team3128.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team3128.Constants.IntakeConstants;
import frc.team3128.common.hardware.motor.NAR_TalonSRX;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;


public class Intake extends SubsystemBase {


    private static Intake instance;
    private DoubleSolenoid m_solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, IntakeConstants.INTAKE_SOLENOID_FORWARD_CHANNEL_ID, IntakeConstants.INTAKE_SOLENOID_FORWARD_CHANNEL_ID);
    private NAR_TalonSRX m_intake;


    public static synchronized Intake getInstance() {
        if (instance == null) {
            instance = new Intake();
        }
        return instance;
    }

    public Intake() {
        configMotors();
    }

    private void configMotors () {
        m_intake = new NAR_TalonSRX(IntakeConstants.INTAKE_MOTOR_ID);
    }

    public void runIntake(){
        m_intake.set(ControlMode.PercentOutput, IntakeConstants.INTAKE_MOTOR_POWER);
    }

    public void stopIntake(){
        m_intake.set(ControlMode.PercentOutput, 0);
    }

    public void retractIntake(){
        m_solenoid.set(kReverse);
    }

    public void ejectIntake(){
        m_solenoid.set(kForward);

    }
    public void off() {
        m_solenoid.set(kOff); 
    }





}

