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
    public Joystick driveJoy;
    
    public Drivebase(int mLFPort, int mLBPort, int mRFPort, int mRBPort, int joyPort)
    {
        driveJoy = new Joystick(joyPort);
        try
        {
            mLF = new CANJaguar(mLFPort, CANJaguar.ControlMode.kPercentVbus);
            mLB = new CANJaguar(mLBPort, CANJaguar.ControlMode.kPercentVbus);
            mRF = new CANJaguar(mRFPort, CANJaguar.ControlMode.kPercentVbus);
            mRB = new CANJaguar(mRBPort, CANJaguar.ControlMode.kPercentVbus);
            mLF.enableControl();
            mLB.enableControl();
            mRF.enableControl();
            mRB.enableControl();
        }
        catch(CANTimeoutException ex)
        {
            System.out.println("<CANTimeoutException>");
            System.out.println("Where: Drivebase Init");
            ex.printStackTrace();
            System.out.println("</CANTimeoutException>");
        }
    }
    
    public void driveForward(double speed)
    {
        try
        {            
            mLF.setX(speed);
            mLB.setX(speed);
            mRF.setX(speed);
            mRB.setX(speed);
        }
        catch(CANTimeoutException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public int joyDirection()
    {
        if(driveJoy.getTwist() > 0.3)
        {
            return 8;
        }
        else if(driveJoy.getTwist() < -0.3)
        {
            return 9;
        }
        else if(driveJoy.getMagnitude() < 0.3)
        {
            return -1;
        }
        else if(driveJoy.getDirectionDegrees() > -22.5 && driveJoy.getDirectionDegrees() <= 22.5)
        {
            return 0;
        }
        else if(driveJoy.getRawButton(6))
        {
            return 1;
        }
        else if(driveJoy.getDirectionDegrees() > 67.5 && driveJoy.getDirectionDegrees() <= 112.5)
        {
            return 2;
        }
        else if(driveJoy.getRawButton(4))
        {
            return 3;
        }
        else if(driveJoy.getDirectionDegrees() > 157.5 || driveJoy.getDirectionDegrees() <= -157.5)
        {
            return 4;
        }
        else if(driveJoy.getRawButton(3))
        {
            return 5;
        }
        else if(driveJoy.getDirectionDegrees() > -112.5 && driveJoy.getDirectionDegrees() <= -67.5)
        {
            return 6;
        }
        else if(driveJoy.getRawButton(5))
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
        //F
        if(this.joyDirection() == 0)
        {
            try
            {            
                mLF.setX(driveJoy.getMagnitude());
                mLB.setX(driveJoy.getMagnitude());
                mRF.setX(driveJoy.getMagnitude());
                mRB.setX(driveJoy.getMagnitude());
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
                mLF.setX(-driveJoy.getMagnitude());
                mLB.setX(0.0);
                mRF.setX(0.0);
                mRB.setX(-driveJoy.getMagnitude());
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
                mLF.setX(driveJoy.getMagnitude());
                mLB.setX(-driveJoy.getMagnitude());
                mRF.setX(-driveJoy.getMagnitude());
                mRB.setX(driveJoy.getMagnitude());
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
                mLB.setX(driveJoy.getMagnitude());
                mRF.setX(driveJoy.getMagnitude());
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
                mLF.setX(-driveJoy.getMagnitude());
                mLB.setX(-driveJoy.getMagnitude());
                mRF.setX(-driveJoy.getMagnitude());
                mRB.setX(-driveJoy.getMagnitude());
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
                mLF.setX(driveJoy.getMagnitude());
                mLB.setX(0.0);
                mRF.setX(0.0);
                mRB.setX(driveJoy.getMagnitude());
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
                mLF.setX(-driveJoy.getMagnitude());
                mLB.setX(driveJoy.getMagnitude());
                mRF.setX(driveJoy.getMagnitude());
                mRB.setX(-driveJoy.getMagnitude());
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
                mLB.setX(-driveJoy.getMagnitude());
                mRF.setX(-driveJoy.getMagnitude());
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
                mLF.setX((Math.abs(driveJoy.getMagnitude()) - 0.25));
                mLB.setX((Math.abs(driveJoy.getMagnitude()) - 0.25));
                mRF.setX(-(Math.abs(driveJoy.getMagnitude()) - 0.25));
                mRB.setX(-(Math.abs(driveJoy.getMagnitude()) - 0.25));
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
                mLF.setX(-(Math.abs(driveJoy.getMagnitude()) - 0.25));
                mLB.setX(-(Math.abs(driveJoy.getMagnitude()) - 0.25));
                mRF.setX((Math.abs(driveJoy.getMagnitude()) - 0.25));
                mRB.setX((Math.abs(driveJoy.getMagnitude()) - 0.25));
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
