package cougartech.aerialassist;

import cougartech.aerialassist.modules.Drivebase;
import cougartech.aerialassist.modules.Gatherer;
import cougartech.aerialassist.modules.Sensorbase;
import cougartech.aerialassist.modules.Shooter;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class RobotMain extends IterativeRobot
{

    RobotMap robotMap;
    Drivebase drive;
    Shooter shooter;
    Gatherer gatherer;
    Sensorbase sensor;
    Joystick joy1;
    Joystick joy2;
    boolean wantShoot;

    public void robotInit()
    {
        drive = new Drivebase(robotMap.dbLF, robotMap.dbLB, robotMap.dbRF, robotMap.dbRB);
        joy1 = new Joystick(robotMap.j1);
        joy2 = new Joystick(robotMap.j2);
        shooter = new Shooter(robotMap.sM1, robotMap.sM2, robotMap.sStopSwitch, robotMap.sBallDetect);
        gatherer = new Gatherer(robotMap.gA1, robotMap.gA2, robotMap.gTilter);
    }

    public void autonomousPeriodic()
    {
    }

    public void teleopPeriodic()
    {
        gatherer.initTilter();
        
        if(wantShoot && shooter.shooting)
        {
            shooter.shoot();
        }
        else if(!wantShoot && joy1.getRawButton(1))
        {
            wantShoot = true;
        }
        
        if(!shooter.shooting)
        {
            wantShoot = false;
        }
        
        
        if(joy2.getRawButton(5))
        {
            gatherer.armDirection(0);
            gatherer.tiltArm(1);
        }
        else if(joy2.getRawButton(3))
        {
            gatherer.armDirection(1);
            gatherer.tiltArm(1);
        }
        else if(joy2.getRawButton(6))
        {
            gatherer.armDirection(0);
            gatherer.tiltArm(-1);
        }
        else if(joy2.getRawButton(4))
        {
            gatherer.armDirection(-1);
            gatherer.tiltArm(-1);
        }
        else
        {
            gatherer.armDirection(0);
            gatherer.tiltArm(0);
        }
    }

    public void testPeriodic()
    {
    }
}
