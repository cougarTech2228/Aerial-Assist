package cougartech.aerialassist;

import cougartech.aerialassist.modules.Drivebase;
import cougartech.aerialassist.modules.Gatherer;
import cougartech.aerialassist.modules.Sensorbase;
import cougartech.aerialassist.modules.Shooter;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class RobotMain extends IterativeRobot
{

    RobotMap robotMap;
    Drivebase drive;
    Shooter shooter;
    Gatherer gatherer;
    Sensorbase sensor;
    Joystick joyG;
    NetworkTable netTable;
    boolean wantShoot;

    public void robotInit()
    {
        drive = new Drivebase(robotMap.dbLF, robotMap.dbLB, robotMap.dbRF, robotMap.dbRB, robotMap.j1);
        joyG = new Joystick(robotMap.j2);
        shooter = new Shooter(robotMap.sM1, robotMap.sM2, robotMap.sStopSwitch, robotMap.sBallDetect);
        gatherer = new Gatherer(robotMap.gA1, robotMap.gA2, robotMap.gTilter);
    }

    public void autonomousPeriodic()
    {
        if((sensor.getDistance(1, robotMap.sonar) / 12) > 14)
        {
            drive.driveForward(0.75);
        }
        else if((sensor.getDistance(1, robotMap.sonar) / 12) < 14)
        {
            drive.driveForward(-0.5);
        }
        else
        {
            //Check for hot goal and shoot
        }
            
    }

    public void teleopPeriodic()
    {
        
        drive.doDrive();
        
        //SHOOTER
        //Far
        if(wantShoot && shooter.shooting)
        {
            shooter.shoot(305, 0.85);
        }
        else if(!wantShoot && drive.driveJoy.getRawButton(1))
        {
            wantShoot = true;
        }
        
        //Short
        if(wantShoot && shooter.shooting)
        {
            shooter.shoot(285, 1.0);
        }
        else if(!wantShoot && drive.driveJoy.getRawButton(2))
        {
            wantShoot = true;
        }
        
        //End of cycle reset
        if(!shooter.shooting)
        {
            wantShoot = false;
        }
        
        //GATHERER
        //Tilt
        if(joyG.getY() >= 0.2)
        {
            //Foward
            gatherer.tiltArm(1);
        }
        else if(joyG.getY() <= -0.2)
        {
            //Backwards
            gatherer.tiltArm(-1);
        }
        else
        {
            //Not moving
            gatherer.tiltArm(0);
        }
        
        //Arm
        if(joyG.getRawButton(1))
        {
            //In
            gatherer.armDirection(1);
        }
        else if(joyG.getRawButton(2))
        {
            //Out
            gatherer.armDirection(-1);
        }
        else
        {
            //No Movement
            gatherer.armDirection(0);
        }
        
        //DISTANCE        
        if((sensor.getDistance(1, robotMap.sonar) / 12) >= 7 && (sensor.getDistance(1, robotMap.sonar) / 12) <= 9)
        {
            netTable.putBoolean("near", true);
            netTable.putBoolean("far", false);
        }
        else if((sensor.getDistance(1, robotMap.sonar) / 12) >= 13 && (sensor.getDistance(1, robotMap.sonar) / 12) <= 15)
        {
            netTable.putBoolean("near", false);
            netTable.putBoolean("far", true);
        }
        else
        {
            netTable.putBoolean("near", false);
            netTable.putBoolean("far", false);
        }
        
        //MISC NETABLE
        netTable.putBoolean("ball", shooter.ballDetect.get());
        netTable.putNumber("sonarDistance", sensor.getDistance(1, robotMap.sonar));
    }

    public void testPeriodic()
    {
    }
}
