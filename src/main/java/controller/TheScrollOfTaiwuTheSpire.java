package controller;

import UI.TaiwuPanel;
import basemod.BaseMod;
import basemod.interfaces.*;
import cards.*;
import characters.Taiwu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import reina.yui.Yui;
import relics.FuYuJianBing;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@SpireInitializer
public class TheScrollOfTaiwuTheSpire implements EditCardsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber,EditStringsSubscriber, EditKeywordsSubscriber
{

    private static TheScrollOfTaiwuTheSpire instance;

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


    /**
     * 被mod框架直接调用的初始化方法
     */
    public static void initialize()
    {
        instance = new TheScrollOfTaiwuTheSpire();
    }

    @Override
    public void receiveEditCards()
    {
        logger.info("start adding cards");
        BaseMod.addCard(new DaZhuoShou());
        BaseMod.addCard(new TaiZuChangQuan());
        BaseMod.addCard(new BiHuYouQiangGong());
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
        BaseMod.addRelicToCustomPool(new FuYuJianBing(),CardColor.TAIWU_COLOR);
        logger.info("adding relics done");
    }

    @Override
    public void receiveEditStrings()
    {
        BaseMod.loadCustomStringsFile(RelicStrings.class,"taiwuLocalization/zhs/taiwuRelics.json");
        BaseMod.loadCustomStringsFile(CardStrings.class,"taiwuLocalization/zhs/taiwuCards.json");
    }

    @Override
    public void receiveEditKeywords()
    {
        String[] keywords =new String[]{
                "崩",
                "劈",
                "射",
                "扫",
                "音"
        };
        BaseMod.addKeyword(keywords,"式的一种。式是用来施展武学的资源，消耗式施展的武学比一般使用更加强劲。");
    }
}
