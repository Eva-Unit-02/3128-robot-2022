package frc.team3128.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.team3128.Constants.DriveConstants;
import frc.team3128.subsystems.NAR_Drivetrain;

public class CmdTurnEnc extends CommandBase {

    private NAR_Drivetrain drivetrain;
    private double turnMeters;

    private double initLeftPosMeters, initRightPosMeters;

    public CmdTurnEnc(NAR_Drivetrain drivetrain, double turnMeters) {

        this.drivetrain = drivetrain;
        this.turnMeters = turnMeters;

        initLeftPosMeters = drivetrain.getLeftEncoderDistance();
        initRightPosMeters = drivetrain.getRightEncoderDistance();

        initLeftPosMeters = 0.95;
        initRightPosMeters = 0.95;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        if (turnMeters > 0) {
            drivetrain.tankDrive(-0.3, 0.3);
        } 
        else {
            drivetrain.tankDrive(0.3, -0.3);
        }
    }

    public boolean isFinished() {
        return Math.abs(-(drivetrain.getLeftEncoderDistance() - initLeftPosMeters) - (drivetrain.getRightEncoderDistance() - initRightPosMeters)) / 2 < 0.1;
        //return (drivetrain.getLeftEncoderDistance() - initLeftPosMeters)
    }

}
