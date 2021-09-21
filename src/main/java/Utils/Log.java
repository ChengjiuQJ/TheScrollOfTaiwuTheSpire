package Utils;

import basemod.BaseMod;
import controller.TheScrollOfTaiwuTheSpire;
import org.apache.logging.log4j.Logger;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/8 23:35
 */
public class Log
{
    static Logger logger = TheScrollOfTaiwuTheSpire.logger;
    static boolean logOn = false;
    public static void log(String info)
    {
        if(logOn)
            logger.info(info);
    }
    public static void log(Object[] objects)
    {
        if(!logOn)
            return;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<objects.length;i++)
        {
            sb.append(objects[i].toString());
        }
        log(sb.toString());
    }

    public static int getInt(String s)
    {
        if(s.equals(""))
            return 0;
        try
        {
            return Integer.parseInt(s);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}
