package cougartech.aerialassist.modules;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import java.util.Date;

public class Shooter
{
    
    CANJaguar motor;
    CANJaguar motor2;
    DigitalInput stopSwitch;
    public DigitalInput ballDetect;
    Date nowTime;
    public boolean shooting = false;
    boolean reset = false;
    long sTime;
    
    public Shooter(int motorPort, int motor2Port, int stopPort, int ballDetectPort)
    {
        stopSwitch = new DigitalInput(stopPort);
        ballDetect = new DigitalInput(ballDetectPort);
        try
        {    
            motor = new CANJaguar(motorPort);
            motor2 = new CANJaguar(motor2Port);
            motor.enableControl();
            motor2.enableControl();
        }
        catch(CANTimeoutException ex)
        {
            ex.printStackTrace();
        }
    }
    
    
    public void shoot(long timePer, double motorPower)
    {
        nowTime = new Date();
      
        if(shooting)
        {
                if(nowTime.getTime() < (sTime + timePer))
                {
                    try
                    {
                        motor.setX(motorPower);
                        motor2.setX(motorPower);
                    }
                    catch(CANTimeoutException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                else
                {
                    try
                    {
                        motor.setX(0.0);
                        motor2.setX(0.0);
                    }
                    catch(CANTimeoutException ex)
                    {
                        ex.printStackTrace();
                    }
                    reset = true;
                    this.resetArm();
                }
         }
        else if(!shooting && !ballDetect.get())
        {
            sTime = nowTime.getTime();
            shooting = true;
        }
    }
    
    private void resetArm()
    {
       if(reset)
        {
            if(stopSwitch.get())
            {
                try
                {
                    motor.setX(0.0);
                    motor2.setX(0.0);
                }
                catch(CANTimeoutException ex)
                {
                    ex.printStackTrace();
                }
                reset  = false;
                shooting = false;
            }
            else
            {
                try
                {
                    motor.setX(-0.25);
                    motor2.setX(-0.25);
                }
                catch(CANTimeoutException ex)
                {
                    ex.printStackTrace();
                }                
            }
        }
    }
}
