package cougartech.aerialassist.modules;

import edu.wpi.first.wpilibj.Joystick;

public class OI
{
    
    Joystick joy1;
    Joystick joy2;
    
    public OI(int joy1Port, int joy2Port)
    {
        joy1 = new Joystick(joy1Port);
        joy2 = new Joystick(joy2Port);
    }
    
    public boolean joy1GetButton(int button)
    {
        return joy1.getRawButton(button);
    }
    
    public boolean joy2GetButton(int button)
    {
        return joy2.getRawButton(button);
    }
}
