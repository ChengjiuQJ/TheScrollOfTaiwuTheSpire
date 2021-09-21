package cards;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/6 13:53
 */
public class CardColor
{

    @SpireEnum
    public static AbstractCard.CardColor QUANZHANG;//自定义卡牌枚举
    @SpireEnum
    public static AbstractCard.CardColor DAOJIAN;
    @SpireEnum
    public static AbstractCard.CardColor YVSHE;
    @SpireEnum
    public static AbstractCard.CardColor QIMENG;
    @SpireEnum
    public static AbstractCard.CardColor TONGYONG;


    public static final Color EVERYTHING_COLOR = Color.WHITE;

    /**
     * 手牌中卡牌显示的图片 512*512px
     */
    public static final String ATTACK_BG_PATH = "img/cards/512/bg_attack_default_gray.png";
    public static final String SKILL_BG_PATH = "img/cards/512/bg_skill_default_gray.png";
    public static final String POWER_BG_PATH = "img/cards/512/bg_power_default_gray.png";
    public static final String ORB_PATH = "img/orbs/512/card_default_gray_orb.png";

    /**
     * 大图查看时显示的图片 1024*1024px
     */
    public static final String ATTACK_BG_ISP_PATH = "img/cards//1024/bg_attack_default_gray.png";
    public static final String SKILL_BG_ISP_PATH = "img/cards//1024/bg_skill_default_gray.png";
    public static final String POWER_BG_ISP_PATH = "img/cards/1024/bg_power_default_gray.png";
    public static final String ORB_ISP_PATH = "img/cards/1024/card_default_gray_orb.png";

    /**
     * 暂时未知
     */
    public static final String ORB_CARD_PATH = "img/cards/512/card_small_orb.png";


    public static void initalize()
    {
        BaseMod.addColor(CardColor.QUANZHANG,EVERYTHING_COLOR,ATTACK_BG_PATH,SKILL_BG_PATH,POWER_BG_PATH,ORB_PATH,ATTACK_BG_ISP_PATH,SKILL_BG_ISP_PATH,POWER_BG_ISP_PATH,ORB_ISP_PATH,ORB_CARD_PATH);
        BaseMod.addColor(CardColor.YVSHE,EVERYTHING_COLOR,ATTACK_BG_PATH,SKILL_BG_PATH,POWER_BG_PATH,ORB_PATH,ATTACK_BG_ISP_PATH,SKILL_BG_ISP_PATH,POWER_BG_ISP_PATH,ORB_ISP_PATH,ORB_CARD_PATH);
        BaseMod.addColor(CardColor.DAOJIAN,EVERYTHING_COLOR,ATTACK_BG_PATH,SKILL_BG_PATH,POWER_BG_PATH,ORB_PATH,ATTACK_BG_ISP_PATH,SKILL_BG_ISP_PATH,POWER_BG_ISP_PATH,ORB_ISP_PATH,ORB_CARD_PATH);
        BaseMod.addColor(CardColor.QIMENG,EVERYTHING_COLOR,ATTACK_BG_PATH,SKILL_BG_PATH,POWER_BG_PATH,ORB_PATH,ATTACK_BG_ISP_PATH,SKILL_BG_ISP_PATH,POWER_BG_ISP_PATH,ORB_ISP_PATH,ORB_CARD_PATH);
        BaseMod.addColor(CardColor.TONGYONG,EVERYTHING_COLOR,ATTACK_BG_PATH,SKILL_BG_PATH,POWER_BG_PATH,ORB_PATH,ATTACK_BG_ISP_PATH,SKILL_BG_ISP_PATH,POWER_BG_ISP_PATH,ORB_ISP_PATH,ORB_CARD_PATH);
    }
}
