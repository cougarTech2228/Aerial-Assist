package cougartech.aerialassist;

import cougartech.aerialassist.modules.Drivebase;
import cougartech.aerialassist.modules.Gatherer;
import cougartech.aerialassist.modules.OI;
import cougartech.aerialassist.modules.Sensorbase;
import cougartech.aerialassist.modules.Shooter;
import edu.wpi.first.wpilibj.IterativeRobot;

public class RobotMain extends IterativeRobot
{

    RobotMap robotMap;
    Drivebase drive;
    Shooter shooter;
    Gatherer gatherer;
    Sensorbase sensor;
    OI oi;

    public void robotInit()
    {
        drive = new Drivebase(robotMap.dbLF, robotMap.dbLB, robotMap.dbRF, robotMap.dbRB);
    }

    public void autonomousPeriodic()
    {
    }

    public void teleopPeriodic()
    {
    }

    public void testPeriodic()
    {
    }
}
