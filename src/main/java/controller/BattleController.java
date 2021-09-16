package controller;

import UI.Shi;
import UI.ShiPlaceHolder;
import UI.TaiwuPanel;
import Utils.TextureLoader;
import basemod.BaseMod;
import basemod.TopPanelItem;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import cards.AbstractTaiwuCard;
import cards.AttackType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import org.apache.logging.log4j.LogManager;
import reina.yui.Yui;
import reina.yui.YuiClickableObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/7 16:12
 */
public class BattleController implements OnStartBattleSubscriber, PostBattleSubscriber,PostInitializeSubscriber
{
    public static BattleController instance;

    public static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(BattleController.class.getName());

    private HashMap<AttackType,Integer> battleShi;
    private AttackType[] shiQueue;
    private int currentIndex;
    private YuiClickableObject panel;
    private ShiPlaceHolder[] shiPlaceHolders;
    private HashMap<AttackType, Texture> shiTextures;

    public BattleController()
    {
        instance = this;
        BaseMod.subscribe(this);
    }

    public void costShi(AttackType attackType)
    {
        for(int i=0;i<=currentIndex;i++)
        {
            if(shiPlaceHolders[i].shi.attackType==attackType)
            {
                Shi shi = shiPlaceHolders[i].shi;
                Yui.Companion.remove(shi);
                shiPlaceHolders[i].shi=null;
                for(int j=i+1;j<=currentIndex;j++)
                {
                    shiPlaceHolders[j-1].addShi(shiPlaceHolders[j].shi);
                }
                currentIndex--;
                if(currentIndex<0)
                    currentIndex=0;
                return;
            }

        }
    }

    private void initPanel()
    {
        if(panel==null)
        {
            panel = new TaiwuPanel();
        }
        Yui.Companion.add(panel);
        if(shiPlaceHolders==null)
        {
            shiPlaceHolders = new ShiPlaceHolder[6];
            float x = panel.getX()+65F;
            float y = panel.getY()+65F;
            float r = 55F;
            for(int i=0;i<6;i++)
            {
                float deg = i*60+30;
                shiPlaceHolders[i] = new ShiPlaceHolder(MathUtils.sinDeg(deg)*r+x,MathUtils.cosDeg(deg)*r+y);
                logger.info("shiPlaceHolder "+i+": x="+shiPlaceHolders[i].x+" y="+shiPlaceHolders[i].y);
            }
        }
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom)
    {
        battleShi = new HashMap<>();
        shiQueue = new AttackType[6];
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
        Yui.Companion.remove(panel);
        for(int i=0;i<6;i++)
        {
            Shi shi = shiPlaceHolders[i].shi;
            if(shi!=null)
            {
                Yui.Companion.remove(shi);
                shiPlaceHolders[i].shi = null;
            }
        }
    }


    public Shi addShi(AttackType attackType)
    {
        logger.info("add shi"+attackType.toString());
        Shi temp = new Shi(shiTextures.get(attackType),800,450,attackType);
        Yui.Companion.add(temp);
        logger.info("render shi");
        if(shiPlaceHolders[currentIndex].isEmpty())
        {
            shiPlaceHolders[currentIndex].addShi(temp);
        }
        else
        {
            for(int i = currentIndex;i>=0;i--)
            {
                if(i+1<6)
                    shiPlaceHolders[i+1].addShi(shiPlaceHolders[i].shi);
                else
                {
                    Yui.Companion.remove(shiPlaceHolders[i].shi);
                }
            }
            shiPlaceHolders[0].addShi(temp);
            currentIndex++;
        }
        if(currentIndex>=6)
            currentIndex=5;
        return temp;
    }


    public ArrayList<Shi> getAllShi()
    {
        ArrayList<Shi> result = new ArrayList<>();
        for(int i=0;i<6;i++)
        {
            if(!shiPlaceHolders[i].isEmpty())
                result.add(shiPlaceHolders[i].shi);
        }
        return result;
    }

    public ArrayList<AttackType> getAllAttackType()
    {
        ArrayList<AttackType> result = new ArrayList<>();
        for(int i=0;i<6;i++)
        {
            if(!shiPlaceHolders[i].isEmpty())
                result.add(shiPlaceHolders[i].shi.attackType);
        }
        return result;
    }

    public int getDifferentAttackType()
    {
        ArrayList<AttackType> result = new ArrayList<>();
        for(int i=0;i<6;i++)
        {
            if(!shiPlaceHolders[i].isEmpty()&&!result.contains(shiPlaceHolders[i].shi.attackType))
                result.add(shiPlaceHolders[i].shi.attackType);
        }
        return result.size();
    }

    public AttackType getRecentAttackType()
    {
        if(shiPlaceHolders[0].isEmpty())
            return null;
        else
            return shiPlaceHolders[0].shi.attackType;
    }

    @Override
    public void receivePostInitialize()
    {
        shiTextures = new HashMap<>();
        AttackType[] temp = AttackType.values();
        for(int i=0;i<temp.length;i++)
        {
            shiTextures.put(temp[i], TextureLoader.getTexture("img/ui/"+temp[i].toString()+".png"));
        }
    }
}
