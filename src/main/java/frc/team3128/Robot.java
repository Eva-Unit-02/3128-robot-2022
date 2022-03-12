// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3128;

import java.util.ArrayList;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team3128.subsystems.ConstantsTest;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation.
 */
public class Robot extends TimedRobot {

    public static RobotContainer m_robotContainer = new RobotContainer();
    private Command m_autonomousCommand;


    public static void constructRobot(){
        m_robotContainer = new RobotContainer();
    }

    @Override
    public void robotInit(){
        LiveWindow.disableAllTelemetry();
        ConstantsInt.initTempConstants();
        //CameraServer.startAutomaticCapture();
    }

    @Override
    public void robotPeriodic(){
        m_robotContainer.updateDashboard();
        SmartDashboard.putNumber("Real Number",ConstantsInt.ShooterConstants.HIGH_kD);
        SmartDashboard.putNumber("Subsytem Number", m_robotContainer.test.getNum());
    
        // if(battVoltages.size() == 100) {
        //     battVoltages.remove(0);
        // }
        // battVoltages.add(RobotController.getBatteryVoltage());

        // for (double d : battVoltages) {
        //     voltageRollingAvg += d;
        // }
        // voltageRollingAvg /= battVoltages.size();
    }

    @Override
    public void autonomousInit() {
        // m_robotContainer.initPneumatics();
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();
        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        m_robotContainer.init();
        CommandScheduler.getInstance().cancelAll();
        m_robotContainer.stopDrivetrain();
    }

    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void simulationInit() {
        
    }

    @Override
    public void simulationPeriodic() {
        CommandScheduler.getInstance().run();
    }
    
    @Override
    public void disabledPeriodic() {

    }
}
