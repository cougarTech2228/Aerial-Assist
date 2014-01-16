package cougartech.aerialassist.modules;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

public class Drivebase
{
    
    CANJaguar mLF;
    CANJaguar mLB;
    CANJaguar mRF;
    CANJaguar mRB;
    Joystick joy;
    
    public Drivebase(int mLFPort, int mLBPort, int mRFPort, int mRBPort)
    {
        joy = new Joystick(1);
        try
        {
            mLF = new CANJaguar(mLFPort, CANJaguar.ControlMode.kPercentVbus);
            mLB = new CANJaguar(mLBPort, CANJaguar.ControlMode.kPercentVbus);
            mRF = new CANJaguar(mRFPort, CANJaguar.ControlMode.kPercentVbus);
            mRB = new CANJaguar(mRBPort, CANJaguar.ControlMode.kPercentVbus);
        }
        catch(CANTimeoutException ex)
        {
            System.out.println("<CANTimeoutException>");
            System.out.println("Where: Drivebase Init");
            ex.printStackTrace();
            System.out.println("</CANTimeoutException>");
        }
    }
    
    public int joyDirection()
    {
        if(joy.getTwist() > 0.3)
        {
            return 8;
        }
        else if(joy.getTwist() < -0.3)
        {
            return 9;
        }
        else if(joy.getMagnitude() < 0.3)
        {
            return -1;
        }
        else if(joy.getDirectionDegrees() > -22.5 && joy.getDirectionDegrees() <= 22.5)
        {
            return 0;
        }
        else if(joy.getDirectionDegrees() > 22.5 && joy.getDirectionDegrees() <= 67.5)
        {
            return 1;
        }
        else if(joy.getDirectionDegrees() > 67.5 && joy.getDirectionDegrees() <= 112.5)
        {
            return 2;
        }
        else if(joy.getDirectionDegrees() > 112.5 && joy.getDirectionDegrees() <= 157.5)
        {
            return 3;
        }
        else if(joy.getDirectionDegrees() > 157.5 || joy.getDirectionDegrees() <= -157.5)
        {
            return 4;
        }
        else if(joy.getDirectionDegrees() > -157.5 && joy.getDirectionDegrees() <= -112.5)
        {
            return 5;
        }
        else if(joy.getDirectionDegrees() > -112.5 && joy.getDirectionDegrees() <= -67.5)
        {
            return 6;
        }
        else if(joy.getDirectionDegrees() > -67.5 && joy.getDirectionDegrees() <= -22.5)
        {
            return 7;
        }
        else
        {
            return -1;
        }
    }
    
    public void doDrive()
    {
        //Enables control of Jaguar
        try
        {
            mLF.enableControl();
            mLB.enableControl();
            mRF.enableControl();
            mRB.enableControl();
        }
        catch(CANTimeoutException ex)
        {
            System.out.println("<CANTimeoutException>");
            System.out.println("Where: Enable Control");
            ex.printStackTrace();
            System.out.println("</CANTimeoutException>");
        }
                
        //F
        if(this.joyDirection() == 0)
        {
            try
            {            
                mLF.setX(joy.getMagnitude());
                mLB.setX(joy.getMagnitude());
                mRF.setX(joy.getMagnitude());
                mRB.setX(joy.getMagnitude());
            }
            catch(CANTimeoutException ex)
            {
                ex.printStackTrace();
            }            
        }
        //FR
        else if(this.joyDirection() == 1)
        {
            try
            {            
                mLF.setX(-joy.getMagnitude());
                mLB.setX(0.0);
                mRF.setX(0.0);
                mRB.setX(-joy.getMagnitude());
            }
            catch(CANTimeoutException ex)
            {
                ex.printStackTrace();
            }    
        }
        //R
        else if(this.joyDirection() == 2)
        {
            try
            {            
                mLF.setX(joy.getMagnitude());
                mLB.setX(-joy.getMagnitude());
                mRF.setX(-joy.getMagnitude());
                mRB.setX(joy.getMagnitude());
            }
            catch(CANTimeoutException ex)
            {
                ex.printStackTrace();
            }
        }
        //BR
        else if(this.joyDirection() == 3)
        {
            try
            {            
                mLF.setX(0.0);
                mLB.setX(joy.getMagnitude());
                mRF.setX(joy.getMagnitude());
                mRB.setX(0.0);
            }
            catch(CANTimeoutException ex)
            {
                ex.printStackTrace();
            }
        }
        //B
        else if(this.joyDirection() == 4)
        {
            try
            {            
                mLF.setX(-joy.getMagnitude());
                mLB.setX(-joy.getMagnitude());
                mRF.setX(-joy.getMagnitude());
                mRB.setX(-joy.getMagnitude());
            }
            catch(CANTimeoutException ex)
            {
                ex.printStackTrace();
            }
        }
        //BL        
        else if(this.joyDirection() == 5)
        {
            try
            {            
                mLF.setX(joy.getMagnitude());
                mLB.setX(0.0);
                mRF.setX(0.0);
                mRB.setX(joy.getMagnitude());
            }
            catch(CANTimeoutException ex)
            {
                ex.printStackTrace();
            }
        }
        //L
        else if(this.joyDirection() == 6)
        {
            try
            {            
                mLF.setX(-joy.getMagnitude());
                mLB.setX(joy.getMagnitude());
                mRF.setX(joy.getMagnitude());
                mRB.setX(-joy.getMagnitude());
            }
            catch(CANTimeoutException ex)
            {
                ex.printStackTrace();
            }
        }
        //FL
        else if(this.joyDirection() == 7)
        {
            try
            {            
                mLF.setX(0.0);
                mLB.setX(-joy.getMagnitude());
                mRF.setX(-joy.getMagnitude());
                mRB.setX(0.0);
            }
            catch(CANTimeoutException ex)
            {
                ex.printStackTrace();
            }
        }
        //TR
        else if(this.joyDirection() == 8)
        {
            try
            {            
                mLF.setX((Math.abs(joy.getMagnitude()) - 0.25));
                mLB.setX((Math.abs(joy.getMagnitude()) - 0.25));
                mRF.setX(-(Math.abs(joy.getMagnitude()) - 0.25));
                mRB.setX(-(Math.abs(joy.getMagnitude()) - 0.25));
            }
            catch(CANTimeoutException ex)
            {
                ex.printStackTrace();
            }
        }
        //TL
        else if(this.joyDirection() == 9)
        {
            try
            {            
                mLF.setX(-(Math.abs(joy.getMagnitude()) - 0.25));
                mLB.setX(-(Math.abs(joy.getMagnitude()) - 0.25));
                mRF.setX((Math.abs(joy.getMagnitude()) - 0.25));
                mRB.setX((Math.abs(joy.getMagnitude()) - 0.25));
            }
            catch(CANTimeoutException ex)
            {
                ex.printStackTrace();
            }
        }
        //No movement
        else
        {
            try
            {            
                mLF.setX(0.0);
                mLB.setX(0.0);
                mRF.setX(0.0);
                mRB.setX(0.0);
            }
            catch(CANTimeoutException ex)
            {
                ex.printStackTrace();
            }
        }
    }
            
}
