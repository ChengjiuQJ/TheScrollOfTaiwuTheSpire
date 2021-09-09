package Utils;

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
    public static void log(String info)
    {
        logger.info(info);
    }
    public static void log(Object[] objects)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<objects.length;i++)
        {
            sb.append(objects[i].toString());
        }
        log(sb.toString());
    }
}
