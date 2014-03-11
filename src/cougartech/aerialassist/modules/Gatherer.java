package cougartech.aerialassist.modules;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

public class Gatherer
{
    Jaguar tilter;
    Jaguar arm1;
    Jaguar arm2;    
    
    public Gatherer(int arm1Port, int arm2Port, int tilterPort)
    {
        arm1 = new Jaguar(arm1Port);
        arm2 = new Jaguar(arm2Port);
        tilter = new Jaguar(tilterPort);
        /*
        try
        {
            tilter = new CANJaguar(tilterPort);
            tilter.enableControl();
        }
        catch(CANTimeoutException ex)
        {
            ex.printStackTrace();
        }*/        
    }
    
    /*
     * armDirection
     * 
     * @param direction
     *      Direction for arms to spin
     * @param speed
     *      Speed at which the arms spin at
     */
    public void armDirection(int direction, double speed)
    {
        if(direction == 1)
        {
            arm1.set(speed);
            arm2.set(-speed);
        }
        else if(direction == -1)
        {
            arm1.set(-speed);
            arm2.set(speed);
        }
        else
        {
            arm1.set(0.0);
            arm2.set(0.0);
        }
    }

    /*
     * tiltArm
     * 
     * @param direction
     *      Direction of the tilter to move
     */
    public void tiltArm(int direction)
    {
        if(direction == 1)
        {   
            tilter.set(0.5);
            /*
            try
            {
                tilter.setX(0.5);
            }
            catch(CANTimeoutException ex)
            {
                ex.printStackTrace();
            }*/
        }
        else if(direction == -1)
        {
            tilter.set(-0.5);
            /*
            try
            {
                tilter.setX(-0.5);
            }
            catch(CANTimeoutException ex)
            {
                ex.printStackTrace();
            }*/
        }
        else
        {
            tilter.set(0.0);
            /*
           try
            {
                tilter.setX(0.0);
            }
            catch(CANTimeoutException ex)
            {
                ex.printStackTrace(); 
            }*/
        }
    }
}
