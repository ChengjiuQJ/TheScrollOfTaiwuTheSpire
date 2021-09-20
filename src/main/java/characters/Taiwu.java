package characters;

import Utils.Log;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpineAnimation;
import cards.AbstractTaiwuCard;
import cards.CardColor;
import cards.DaZhuoShou;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.*;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.MeshAttachment;
import com.esotericsoftware.spine.attachments.RegionAttachment;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import controller.TheScrollOfTaiwuTheSpire;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/6 9:07
 */
public class Taiwu extends CustomPlayer
{
    public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
    public static final String TAIWU_SHOULDER_2 = "img/char/衣以候.png"; // campfire pose
    public static final String TAIWU_SHOULDER_1 = "img/char/衣以候.png"; // another campfire pose
    public static final String MY_CHARACTER_CORPSE = "img/char/死亡2.png"; // dead corpse
    public static final String MY_CHARACTER_SKELETON_ATLAS = "img/char/spine2/skeleton.atlas"; // spine animation atlas
    public static final String MY_CHARACTER_SKELETON_JSON = "img/char/spine2/skeleton.json"; // spine animation json
    public static final String[] ORB_TEXTURES = new String[]{"img/orbs/orb.png"}; //
    public static final String ORB_VFX = "img/orbs/orb_vfx.png"; //

    public Taiwu (String name) {
        super(name, TheScrollOfTaiwuTheSpire.TAIWU_CLASS,ORB_TEXTURES,ORB_VFX,new SpineAnimation(MY_CHARACTER_SKELETON_ATLAS,MY_CHARACTER_SKELETON_JSON,0.3f));

        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(null, TAIWU_SHOULDER_2, // required call to load textures and setup energy/loadout
                TAIWU_SHOULDER_1,
                MY_CHARACTER_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 4F); // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines
        skeleton.setSkin("doll");
        Log.log("foot_l rotation="+String.valueOf(skeleton.findBone("foot_l").getRotation()));
        Log.log("foot_r rotation="+String.valueOf(skeleton.findBone("foot_r").getRotation()));
        skeleton.findBone("foot_l").setRotation(60F);
        skeleton.findBone("foot_r").setRotation(75F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "C_000", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    public void resetFootRotation()
    {
        skeleton.findBone("foot_l").setRotation(60F);
        skeleton.findBone("foot_r").setRotation(75F);
    }


    public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
        ArrayList<String> retVal = new ArrayList<>();
        /*retVal.add("太祖长拳崩");
        retVal.add("太祖长拳崩");
        retVal.add("太祖长拳点");
        retVal.add("太祖长拳点");
        retVal.add("太祖长拳拿");
        retVal.add("太祖长拳拿");
        retVal.add("小纵跃功");
        retVal.add("小纵跃功");

         */
        retVal.add("小纵跃功");
        retVal.add("小纵跃功");
        /*
        retVal.add("大拙手");
        retVal.add("心无定意");
        retVal.add("五花八门");
        retVal.add("七星横链");
        retVal.add("鸭形拳");

         */
        retVal.add("八卦步");
        retVal.add("游身八卦掌");
        retVal.add("三华聚顶");
        retVal.add("壁虎游墙功");
        retVal.add("疯狗拳");
        retVal.add("锁喉阴手");
        return retVal;
    }

    public ArrayList<String> getStartingRelics() { // starting relics - also simple
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("FuYuJianBing");
        UnlockTracker.markRelicAsSeen("FuYuJianBing");
        return retVal;
    }

    public static final int STARTING_HP = 79;
    public static final int MAX_HP = 79;
    private static final int ORB_SLOTS = 0;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;

    /**
     * 选人界面时画面上的各种信息
     * @return 选人界面展示的信息
     */
    public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo("太吾传人", "「相枢」者，万相之相，祸之核枢也…… NL 此物非神非鬼，杀不死，灭不去，思之则生，念绝则亡。",
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, HAND_SIZE,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    /**
     * 获取显示的文字
     * @param playerClass 职业类别的枚举
     * @return 职业名称
     */
    @Override
    public String getTitle(PlayerClass playerClass)
    {
        return "太吾";
    }

    /**
     * 获取当前职业的卡牌种类(颜色)
     * @return 当前职业的卡牌颜色的枚举
     */
    @Override
    public AbstractCard.CardColor getCardColor()
    {
        return CardColor.QUANZHANG;
    }

    @Override
    public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> abstractCards)
    {
        ArrayList<AbstractCard> cards= super.getCardPool(abstractCards);
        cards = getTaiwuCardPool(cards,CardColor.TONGYONG);
        return cards;
    }

    public ArrayList<AbstractCard> getTaiwuCardPool(ArrayList<AbstractCard> tmpPool, AbstractCard.CardColor color)
    {
        Iterator var3 = CardLibrary.cards.entrySet().iterator();

        while(true) {
            Map.Entry c;
            AbstractCard card;
            do {
                do {
                    do {
                        if (!var3.hasNext()) {
                            return tmpPool;
                        }

                        c = (Map.Entry)var3.next();
                        card = (AbstractCard)c.getValue();
                    } while(!card.color.equals(color));
                } while(card.rarity == AbstractCard.CardRarity.BASIC);
            } while(UnlockTracker.isCardLocked((String)c.getKey()) && !Settings.isDailyRun);

            tmpPool.add(card);
        }
    }

    /**
     * 获取卡牌渲染颜色
     * @return 颜色(R,G,B)
     */
    @Override
    public Color getCardRenderColor()
    {
        return Color.BROWN;
    }

    /**
     * 当发生格林对对碰事件时,获得哪张卡牌
     * @return 获得的卡牌类
     */
    @Override
    public AbstractCard getStartCardForEvent()
    {
        return AbstractTaiwuCard.initCard("大拙手");
    }

    /**
     * 获得卡牌移动轨迹的颜色
     * @return 颜色(RGB)
     */
    @Override
    public Color getCardTrailColor()
    {
        return Color.RED;
    }

    /**
     * 高难度时职业减少的最大生命值数量
     * @return 减少生命值
     */
    @Override
    public int getAscensionMaxHPLoss()
    {
        return 5;
    }

    /**
     * 自定义能量槽上的字体
     * @return 字体对象
     */
    @Override
    public BitmapFont getEnergyNumFont()
    {
        return FontHelper.energyNumFontRed;
    }

    /**
     * 人物选择界面时选择人物后的效果,可以播放音效之类的
     */
    @Override
    public void doCharSelectScreenSelectEffect()
    {
        // TODO: 2021/9/7 来点太吾语音
    }

    /**
     * 选择界面播放音效的key,存储音效的 hashMap 为 CardCrawlGame.sound.map
     * @return 音效的键
     */
    @Override
    public String getCustomModeCharacterButtonSoundKey()
    {
        return null;
    }

    /**
     * 出现的历史记录里的名字
     * @return 名字
     */
    @Override
    public String getLocalizedCharacterName()
    {
        return "太吾传人";
    }

    /**
     * 返回一个新实例对象
     * @return 以当前 name 为参数构造的新实例对象
     */
    @Override
    public AbstractPlayer newInstance()
    {
        return new Taiwu(name);
    }

    /**
     * 攻击心脏时显示的字符串
     * @return 需要显示的字符串
     */
    @Override
    public String getSpireHeartText()
    {
        return "相枢受死吧!!!!!!!吔我万华十四剑啦!!!!!!!";
    }

    /**
     * 攻击心脏时的屏幕调色效果
     * @return 需要调整的颜色
     */
    @Override
    public Color getSlashAttackColor()
    {
        return Color.WHITE;
    }

    /**
     * 对心脏的攻击效果组合
     * @return 大于零的数组被视为有效
     */
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect()
    {
        return new AbstractGameAction.AttackEffect[0];
    }

    /**
     * 吸血鬼的文字
     * @return
     */
    @Override
    public String getVampireText()
    {
        return null;
    }

}
