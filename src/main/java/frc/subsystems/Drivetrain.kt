package frc.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import frc.robot.RobotMap
import harkerrobolib.subsystems.HSDrivetrain
import harkerrobolib.wrappers.HSTalon
import com.ctre.phoenix.motorcontrol.can.VictorSPX
import frc.commands.drivetrain.ArcadeDrive

/**
 * This class represents the drivetrain of the robot
 *
 * @author Chirag Kaushik
 * @author Finn Frankis
 * @version November 8, 2018
 */
object Drivetrain : HSDrivetrain(
        HSTalon(RobotMap.LEFT_TALON_DEVICENUMBER), HSTalon(RobotMap.RIGHT_TALON_DEVICENUMBER),
        VictorSPX(RobotMap.LEFT_FOLLOWER_DEVICENUMBER), VictorSPX(RobotMap.RIGHT_FOLLOWER_DEVICENUMBER)
) {
    fun talonInit() {
        resetMasters()
        followMasters()
    }

    fun configurePID() {
        leftMaster.config_kP(RobotMap.PID_SLOT, RobotMap.LEFT_TALON_KP, RobotMap.TIMEOUT)
        leftMaster.config_kI(RobotMap.PID_SLOT, RobotMap.LEFT_TALON_KI, RobotMap.TIMEOUT)
        leftMaster.config_kD(RobotMap.PID_SLOT, RobotMap.LEFT_TALON_KD, RobotMap.TIMEOUT)

        rightMaster.config_kP(RobotMap.PID_SLOT, RobotMap.RIGHT_TALON_KP, RobotMap.TIMEOUT)
        rightMaster.config_kI(RobotMap.PID_SLOT, RobotMap.RIGHT_TALON_KI, RobotMap.TIMEOUT)
        rightMaster.config_kD(RobotMap.PID_SLOT, RobotMap.RIGHT_TALON_KD, RobotMap.TIMEOUT)

        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, RobotMap.PID_SLOT, RobotMap.TIMEOUT)
        rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, RobotMap.PID_SLOT, RobotMap.TIMEOUT)
    }

    fun arcadeDrivePercentOutput(percentOutput: Double, turn: Double = 0.0) {
        leftMaster.set(ControlMode.PercentOutput, percentOutput + turn)
        rightMaster.set(ControlMode.PercentOutput, percentOutput - turn)
    }

    override fun initDefaultCommand() {
        defaultCommand = ArcadeDrive()
    }
}