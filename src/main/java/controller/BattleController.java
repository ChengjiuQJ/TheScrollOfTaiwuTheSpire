package controller;

import UI.TaiwuPanel;
import basemod.BaseMod;
import basemod.TopPanelItem;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import cards.AttackType;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;
import org.apache.logging.log4j.LogManager;

import java.rmi.Remote;
import java.util.HashMap;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/7 16:12
 */
public class BattleController implements OnStartBattleSubscriber, PostBattleSubscriber, PostInitializeSubscriber
{
    public static BattleController instance;

    public static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(BattleController.class.getName());

    private HashMap<AttackType,Integer> battleShi;
    private AttackType[] shiQueue;
    private int headIndex;
    private int currentIndex;
    private TopPanelItem panel;

    public BattleController()
    {
        instance = this;
        BaseMod.subscribe(this);
    }

    private void initPanel()
    {

    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom)
    {
        battleShi = new HashMap<>();
        shiQueue = new AttackType[6];
        headIndex=0;
        currentIndex = 0;
        initPanel();
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom)
    {
        HidePanel();
    }

    public void HidePanel()
    {

    }

    public void gainShi(AttackType[] types,int[] counts)
    {
        int length = 0;
        if(types.length!=counts.length)
        {
            logger.warn("the count of attackType and counts was not matched");
            length = Math.min(types.length,counts.length);
        }
        else
            length = types.length;
        for(int i=0;i<length;i++)
        {
            for(int j=0;j<counts[i];j++)
                addToShiQueue(types[i]);
        }
    }

    private void addToShiQueue(AttackType attackType)
    {
        if(shiQueue[currentIndex]==null)
        {
            shiQueue[currentIndex]=attackType;
            int value = battleShi.getOrDefault(attackType, 0);
            battleShi.put(attackType,value+1);
        }
        else
        {
            AttackType old = shiQueue[currentIndex];
            int value = battleShi.containsKey(old)? battleShi.get(attackType):0;
            battleShi.put(old,value-1);
            headIndex++;
            headIndex%=6;
            shiQueue[currentIndex]=attackType;
            value = battleShi.getOrDefault(attackType, 0);
            battleShi.put(attackType,value+1);
        }
        currentIndex++;
        currentIndex%=6;
        logger.info("get shi:"+attackType.toString());
    }

    public void consumeShi(AttackType[] types,int[] counts)
    {
        int length = 0;
        if(types.length!=counts.length)
        {
            logger.warn("the count of attackType and counts was not matched");
            length = Math.min(types.length,counts.length);
        }
        else
            length = types.length;
        for(int i=0;i<length;i++)
        {
            for(int j=0;i<counts[i];j++)
                removeShiFromQueue(types[i]);
        }
    }

    private void removeShiFromQueue(AttackType type)
    {
        int index = headIndex;
        while(shiQueue[index]!=type)
        {
            index++;
            index%=6;
        }
        shiQueue[index]=null;
        int index2 = (index+1)%6;
        while (index2!=currentIndex)
        {
            shiQueue[index2-1<0?index2+5:index2-1]=shiQueue[index2];
            index2++;
        }
    }

    @Override
    public void receivePostInitialize()
    {
        panel =new TaiwuPanel();
        BaseMod.addTopPanelItem(panel);
    }
}
