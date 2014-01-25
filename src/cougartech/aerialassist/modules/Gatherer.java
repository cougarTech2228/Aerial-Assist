package cougartech.aerialassist.modules;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

public class Gatherer
{
    CANJaguar tilter;
    Talon arm1;
    Talon arm2;    
    
    public Gatherer(int arm1Port, int arm2Port, int tilterPort)
    {
        arm1 = new Talon(arm1Port);
        arm2 = new Talon(arm2Port);
        
        try
        {
            tilter = new CANJaguar(tilterPort);
        }
        catch(CANTimeoutException ex)
        {
            ex.printStackTrace();
        }        
    }
    
    public void armDirection(int direction)
    {
        if(direction == 1)
        {
            arm1.set(0.5);
            arm2.set(-0.5);
        }
        else if(direction == -1)
        {
            arm1.set(-0.5);
            arm2.set(0.5);
        }
        else
        {
            arm1.set(0.0);
            arm2.set(0.0);
        }
    }
    
    public void initTilter()
    {
        try
        {
            tilter.enableControl();
            tilter.configSoftPositionLimits(forwardLimitPosition, reverseLimitPosition);
        }
        catch(CANTimeoutException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void tilt(int direction)
    {
        
    }
}
