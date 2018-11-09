package frc.robot.commands.drivetrain

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.command.Command
import frc.robot.OI
import frc.robot.subsystems.Drivetrain

class ArcadeDrive : Command() {
    override fun execute() {
        val leftX = OI.gamePad.leftX
        val leftY = OI.gamePad.leftY

        Drivetrain.arcadeDrivePercentOutput(leftY, leftX)
    }

    override fun interrupted() {
        Drivetrain.applyToMasters({talon -> talon.set(ControlMode.PercentOutput, 0.0)})
    }

    override fun end() {
        Drivetrain.applyToMasters({talon -> talon.set(ControlMode.PercentOutput, 0.0)})
    }

    override fun isFinished(): Boolean {
        return false
    }
}