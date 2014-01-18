package cougartech.aerialassist.modules;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

public class Shooter
{
    CANJaguar m1;
    CANJaguar m2;
            
    public Shooter(int m1Port, int m2Port)
    {
        try
        {    
            m1 = new CANJaguar(m1Port, CANJaguar.ControlMode.kPosition);
            m2 = new CANJaguar(m2Port, CANJaguar.ControlMode.kPosition);
        }
        catch(CANTimeoutException ex)
        {
            ex.printStackTrace();
        }
    }
}
