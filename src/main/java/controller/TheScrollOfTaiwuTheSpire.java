package controller;

import UI.TaiwuPanel;
import Utils.Log;
import basemod.BaseMod;
import basemod.interfaces.*;
import cards.*;
import characters.Taiwu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.ReflectionHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.apache.logging.log4j.LogManager;
import reina.yui.Yui;
import relics.FuYuJianBing;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Logger;

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
    public TheScrollOfTaiwuTheSpire()
    {
        BaseMod.subscribe(this);
        BattleController battleController = new BattleController();
        CardColor.initalize();
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
            String[] tokens = data[20].split(" ");
            StringBuilder sb = new StringBuilder();
            if(!data[19].equals(""))
            {
                sb.append("获得：");
                String[] attackTypes = data[19].split("&");
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
                if (token.charAt(0) == 'D')
                {
                    token = "!" + token + "!";
                }
                else if (token.charAt(0) == 'B')
                {
                    token = "!B!";
                }
                else if (token.charAt(0) == 'M')
                {
                    token = "!M!";
                }
                else if (token.equals("C1"))
                    token = "!C1!";
                else if (token.equals("C2"))
                    token = "!C2!";
                sb.append(token);
                sb.append(' ');
            }
            temp.DESCRIPTION = sb.toString();
            cardStrings.put(id,temp);
        }
        String json = BaseMod.gson.toJson(cardStrings);
        return json;
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
        //BaseMod.loadCustomStringsFile(CardStrings.class,"taiwuLocalization/zhs/taiwuCards.json");
    }

    @Override
    public void receiveEditKeywords()
    {
        String[] keywords =new String[]{
                "崩", "拿", "点",
                "劈", "刺", "撩",
                "掷", "御", "弹",
                "扫", "缠", "音",
        };
        BaseMod.addKeyword(keywords,"式的一种。式是用来施展武学的资源，消耗式施展的武学比一般使用更加强劲。");
    }
}
