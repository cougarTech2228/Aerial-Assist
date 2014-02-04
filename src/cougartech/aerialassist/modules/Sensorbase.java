package cougartech.aerialassist.modules;

import edu.wpi.first.wpilibj.AnalogModule;

public class Sensorbase
{

    double d1;
    double d2;
    double d3;
    double dAvg;
    double scaleFactor = 5 / 512;

    public Sensorbase()
    {
    }

    public double getDistance(int module, int port)
    {
        d1 = AnalogModule.getInstance(module).getVoltage(port);
        d2 = AnalogModule.getInstance(module).getVoltage(port);
        d3 = AnalogModule.getInstance(module).getVoltage(port);
        dAvg = (d1 + d2 + d3) / 3;

        if (dAvg <= 6)
        {
            return -1;
        }
        else if (dAvg >= 253)
        {
            return -2;
        }
        else
        {
            return dAvg;
        }        
    }
}
