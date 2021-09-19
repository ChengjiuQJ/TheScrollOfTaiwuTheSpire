package controller;

import DynamicVariables.S1;
import DynamicVariables.S2;
import DynamicVariables.S3;
import DynamicVariables.S4;
import Utils.Log;
import basemod.BaseMod;
import basemod.interfaces.*;
import cards.AbstractTaiwuCard;
import cards.AttackType;
import cards.CardColor;
import characters.Taiwu;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.apache.logging.log4j.LogManager;
import relics.FuYuJianBing;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpireInitializer
public class TheScrollOfTaiwuTheSpire implements EditCardsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber,EditStringsSubscriber, EditKeywordsSubscriber
{

    public static TheScrollOfTaiwuTheSpire instance;
    public HashMap<String,String[]> cardsData;
    public static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(TheScrollOfTaiwuTheSpire.class.getName());
    public static TheScrollOfTaiwuTheSpire getInstance()
    {
        return instance;
    }
    public static final String[] dynamicV = new String[]{
            "D","B","M","C1","C2","S1","S2","S3","S4"
    };
    public TheScrollOfTaiwuTheSpire()
    {
        BaseMod.subscribe(this);
        BattleController battleController = new BattleController();
        CardColor.initalize();
    }




    /**
     * 被mod框架直接调用的初始化方法
     */
    public static void initialize()
    {
        instance = new TheScrollOfTaiwuTheSpire();
    }

    private void loadCardData()
    {
        try
        {
            BufferedReader br = new BufferedReader(Gdx.files.internal("data/cards.CSV").reader("utf-8"));
            String[] data = new String[0];
            try
            {
                br.readLine();
                br.readLine();
                data = br.readLine().split(",",-1);
                logger.info(Arrays.toString(data));
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            cardsData = new HashMap<>();
            while (data.length>0&&!data[0].equals(""))
            {
                logger.info(Arrays.toString(data));
                cardsData.put(data[0],data);
                String temp = br.readLine();
                if(temp!=null)
                    data = temp.split(",",-1);
                else
                    data = new String[0];
            }
            br.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveEditCards()
    {
        logger.info("start adding cards");

        for(Map.Entry<String,String[]> e : cardsData.entrySet())
        {
            AbstractTaiwuCard card = AbstractTaiwuCard.initCard(e.getKey(),e.getValue());
            if(card==null)
                Log.log("card "+e.getKey()+" not be added");
            else
            {
                BaseMod.addCard(card);
                logger.info("add card:"+e.getKey());
            }
        }
        BaseMod.addDynamicVariable(new S1());
        BaseMod.addDynamicVariable(new S2());
        BaseMod.addDynamicVariable(new S3());
        BaseMod.addDynamicVariable(new S4());
        logger.info("adding cards done");
    }

    @Override
    public void receiveEditCharacters()
    {
        BaseMod.addCharacter(new Taiwu(CardCrawlGame.playerName), "img/char/太吾selectButton.png", "img/char/太吾_portrait.png",TAIWU_CLASS);
    }

    @SpireEnum
    public static AbstractPlayer.PlayerClass TAIWU_CLASS;

    @Override
    public void receiveEditRelics()
    {
        logger.info("start adding relics");
        BaseMod.addRelicToCustomPool(new FuYuJianBing(),CardColor.QUANZHANG);
        logger.info("adding relics done");
    }

    @Override
    public void receiveEditStrings()
    {
        loadCardData();
        BaseMod.loadCustomStrings(CardStrings.class,setCardDescription());
        BaseMod.loadCustomStringsFile(RelicStrings.class,"taiwuLocalization/zhs/taiwuRelics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class,"taiwuLocalization/zhs/taiwuPowers.json");
    }
    private String setCardDescription()
    {
        HashMap<String,CardStrings> cardStrings = new HashMap<>();
        for(Map.Entry<String,String[]> e : cardsData.entrySet())
        {
            String id = e.getKey();
            String[] data  =e.getValue();
            CardStrings temp = new CardStrings();
            temp.NAME = id;
            temp.DESCRIPTION = getGongFaDescription(data[20],data[19],data[22]);
            temp.UPGRADE_DESCRIPTION = data[27].equals("")?temp.DESCRIPTION:getGongFaDescription(data[27],data[19],data[22]);
            temp.EXTENDED_DESCRIPTION = getGongFaDescriptionEX(data[23],data[22],data[28]);
            cardStrings.put(id,temp);
        }
        return BaseMod.gson.toJson(cardStrings);
    }

    private String[] getGongFaDescriptionEX(String src, String costAttackType,String updateDescription)
    {
        if(src.equals(""))
            return null;
        String[] result = new String[2];
        StringBuilder sb = new StringBuilder();
        sb.append(" 施展 ：");
        String[] attackTypes = costAttackType.split("&");
        for(int i=0;i<attackTypes.length;i++)
        {
            String[] attackType = attackTypes[i].split("\\*");
            for(int j=0;j<Log.getInt(attackType[1]);j++)
            {
                sb.append(" ");
                sb.append(attackType[0]);
            }
        }
        sb.append(" NL ");
        String[] tokens = src.split(" ");
        for(String token:tokens)
        {
            for(String dv:dynamicV)
            {
                if (token.equals(dv))
                {
                    token = "!" + token + "!";
                    break;
                }
            }
            sb.append(token);
            sb.append(' ');
        }
        result[0] = sb.substring(0,sb.length()-2);
        if(updateDescription.equals(""))
            result[1] = result[0];
        else
        {
            sb = new StringBuilder();
            sb.append(" 施展 ：");
            for(int i=0;i<attackTypes.length;i++)
            {
                String[] attackType = attackTypes[i].split("\\*");
                for(int j=0;j<Log.getInt(attackType[1]);j++)
                {
                    sb.append(" ");
                    sb.append(attackType[0]);
                }
            }
            sb.append(" NL ");
            tokens = updateDescription.split(" ");
            for(String token:tokens)
            {
                for(String dv:dynamicV)
                {
                    if (token.equals(dv))
                    {
                        token = "!" + token + "!";
                        break;
                    }
                }
                sb.append(token);
                sb.append(' ');
            }
            result[1] = sb.substring(0,sb.length()-2);
        }
        return result;
    }

    private String getGongFaDescription(String src,String getAttackType,String costAttackType)
    {
        String[] tokens = src.split(" ");
        StringBuilder sb = new StringBuilder();
        if(!getAttackType.equals(""))
        {
            sb.append("获得：");
            String[] attackTypes = getAttackType.split("&");
            for(int i=0;i<attackTypes.length;i++)
            {
                String[] attackType = attackTypes[i].split("\\*");
                for(int j=0;j<Log.getInt(attackType[1]);j++)
                {
                    sb.append(" ");
                    sb.append(attackType[0]);
                }

            }
            sb.append(" NL ");
        }
        for(String token:tokens)
        {
            for(String dv:dynamicV)
            {
                if (token.equals(dv))
                {
                    token = "!" + token + "!";
                    break;
                }
            }
            sb.append(token);
            sb.append(' ');
        }
        if(costAttackType.equals(""))
        {
            return sb.toString().trim();
        }
        else
        {
            sb.append("NL ");
            sb.append("施展 ：");
            String[] attackTypes = costAttackType.split("&");
            for(int i=0;i<attackTypes.length;i++)
            {
                String[] attackType = attackTypes[i].split("\\*");
                for(int j=0;j<Log.getInt(attackType[1]);j++)
                {
                    sb.append(" ");
                    sb.append(attackType[0]);
                }
            }
            sb.append(" ");
            return sb.toString();
        }
    }

    @Override
    public void receiveEditKeywords()
    {
        AttackType[] attackTypes = AttackType.values();
        for (AttackType attackType : attackTypes)
            if(!attackType.toString().equals("移"))
                BaseMod.addKeyword(new String[]{attackType.toString()}, "式的一种。式是用来施展武学的资源，消耗式施展的武学比一般使用更加强劲。");
            else
                BaseMod.addKeyword(new String[]{attackType.toString()}, "式的一种。式是用来施展武学的资源，消耗式施展的武学比一般使用更加强劲。每层移动点可以使受到的所有攻击伤害下降1点");
        BaseMod.addKeyword(new String[]{"施展"},"施展武学便是消耗一定数量的式来释放全新的强化武学效果。按住Left-Ctrl键以查看施展的效果。");
        BaseMod.addKeyword(new String[]{"卸力"},"每八层 #y卸力 会转化成一层 #y缓冲 。");
    }
}
