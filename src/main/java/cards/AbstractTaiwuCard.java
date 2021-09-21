package cards;

import Utils.Log;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.StringBuilder;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import controller.BattleController;
import controller.TheScrollOfTaiwuTheSpire;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/9 16:49
 */
public abstract class AbstractTaiwuCard extends CustomCard
{
    protected boolean alwaysEx;
    protected boolean fixedDescription;
    protected String id;
    protected String[] data;
    protected CardStrings cardStrings;
    protected String name;
    protected String className;
    protected int cost;
    protected int costUpdateValue;
    protected String description;
    protected String imgPath;
    protected CardColor cardColor;
    protected CardType cardType;
    protected CardRarity cardRarity;
    protected CardTarget cardTarget;
    protected int damageUpdateValue;
    protected int blockUpdateValue;
    protected int magicUpdateValue;
    protected int baseCustomValue1;
    protected int customValue1;
    protected int customValue1Updated;
    public boolean isCustomValue1Modified;
    protected int BaseCustomValue2;
    protected int customValue2;
    protected int customValue2Updated;
    public boolean isCustomValue2Modified;
    protected AttackType[] getShiTyp;//获得式的种类
    protected int[] getShiCount;//获得的式的数量
    protected AbstractGameAction.AttackEffect basicAttackEffect;
    protected AttackType[] costShiTyp;//获得式的种类
    protected int[] costShiCount;//获得的式的数量
    protected int[] damageSection;
    protected int[] damageHeavy;
    protected int[] baseDamageHeavy;
    protected AbstractGameAction.AttackEffect updatedAttackEffect;
    protected String[] animationString;
    public static boolean showExDescription;
    protected boolean changed;

    protected static final StringBuilder sbuilder = new StringBuilder();
    protected static final GlyphLayout gl = new GlyphLayout();
    protected static final float CN_DESC_BOX_WIDTH = Settings.BIG_TEXT_MODE ? IMG_WIDTH * 0.87F : IMG_WIDTH * 0.72F;
    protected static final float CARD_ENERGY_IMG_WIDTH = 24.0F * Settings.scale;
    protected static final float MAGIC_NUM_W = 20.0F * Settings.scale;
    public AbstractTaiwuCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target)
    {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.id = id;
        this.name = name;
        cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        this.cost = cost;
        imgPath = img;
        description = rawDescription;
        cardColor = color;
        cardType = type;
        cardRarity = rarity;
        cardTarget = target;
        changed =false;
        fixedDescription = false;
        alwaysEx = false;
        isCustomValue1Modified = false;
        isCustomValue2Modified = false;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        fixedDescription = true;
    }



    protected void changeDescription(boolean ex)
    {
        if(ex||alwaysEx)
        {
            if(cardStrings.EXTENDED_DESCRIPTION==null)
                return;
            Log.log("show exDescription");
            if(upgraded)
                rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
            else
                rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        }
        else
        {
            Log.log("show normalDescription");
            if(upgraded)
                rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            else
                rawDescription = cardStrings.DESCRIPTION;
        }
        Log.log("source text:\n"+rawDescription);
        initializeDescription();
        Log.log("worked text:");
        for(DescriptionLine line : super.description)
        {
            Log.log(line.text);
        }
    }

    @Override
    public void update()
    {
        super.update();
        showExDescription = isCtrlPressed();
        if(changed!=showExDescription&&!fixedDescription)
        {
            changed = showExDescription;
            changeDescription(showExDescription);
        }
    }


    public static AbstractTaiwuCard initCard(String id)
    {
        String[] data = TheScrollOfTaiwuTheSpire.instance.cardsData.getOrDefault(id,null);
        if(data==null)
            return null;
        else
            return initCard(id,data);
    }
    public static AbstractTaiwuCard initCard(String id,String[] data)
    {
        try
        {
            CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
            String name = cardStrings.NAME;
            int cost = Integer.parseInt(data[3]);
            int costUpgrade = getInt(data[4]);
            String description = cardStrings.DESCRIPTION;
            String imgPath = "img/cards/"+id+".png";
            CardColor cardColor = Enum.valueOf(CardColor.class,data[5]);
            CardType cardType = Enum.valueOf(CardType.class,data[6]);
            CardRarity cardRarity = Enum.valueOf(CardRarity.class,data[7]);
            CardTarget cardTarget = Enum.valueOf(CardTarget.class,data[8]);
            Class classType = Class.forName("cards."+data[1]);
            Class[] paramTypes = new Class[]{String.class,String.class,String.class,int.class,String.class,CardType.class, CardColor.class,CardRarity.class,CardTarget.class};
            Object[] params = new Object[]{id,name,imgPath,cost,description,cardType,cardColor,cardRarity,cardTarget};
            AbstractTaiwuCard card = (AbstractTaiwuCard) classType.getConstructor(paramTypes).newInstance(params);
            card.className = data[1];
            card.costUpdateValue = costUpgrade;
            card.data = data;
            card.baseDamage = card.damage = getInt(data[9]);
            card.damageUpdateValue = getInt(data[10]);
            card.baseBlock = card.block = getInt(data[11]);
            card.blockUpdateValue = getInt(data[12]);
            card.baseMagicNumber = card.magicNumber = getInt(data[13]);
            card.magicUpdateValue = getInt(data[14]);
            card.baseCustomValue1 = card.customValue1 = getInt(data[15]);
            card.customValue1Updated = getInt(data[16]);
            card.BaseCustomValue2 = card.customValue2 = getInt(data[17]);
            card.customValue2Updated = getInt(data[18]);
            if(data[19].equals(""))
            {
                card.getShiTyp = new AttackType[0];
                card.getShiCount = new int[0];
            }
            else
            {
                String[] tokens = data[19].split("&");
                card.getShiTyp = new AttackType[tokens.length];
                card.getShiCount = new int[tokens.length];
                for(int i=0;i<tokens.length;i++)
                {
                    String[] token = tokens[i].split("\\*");

                    card.getShiTyp[i]=Enum.valueOf(AttackType.class,token[0]);
                    card.getShiCount[i] = getInt(token[1]);
                }
            }
            if(!data[21].equals(""))
                card.basicAttackEffect = Enum.valueOf(AbstractGameAction.AttackEffect.class,data[21]);
            else
                card.basicAttackEffect = AbstractGameAction.AttackEffect.NONE;

            if(data[22].equals(""))
            {
                card.costShiTyp = null;
                card.costShiCount = null;
            }
            else
            {
                String[] tokens = data[22].split("&");
                card.costShiTyp = new AttackType[tokens.length];
                card.costShiCount = new int[tokens.length];
                for(int i=0;i<tokens.length;i++)
                {
                    String[] token = tokens[i].split("\\*");

                    card.costShiTyp[i]=Enum.valueOf(AttackType.class,token[0]);
                    card.costShiCount[i] = getInt(token[1]);
                }
            }
            if(data[24].equals(""))
                card.damageSection = null;
            else
            {
                String[] values = data[24].split("\\|");
                card.damageSection = new int[4];
                for(int i=0;i<4;++i)
                    card.damageSection[i] = getInt(values[i])*5;
                card.baseDamageHeavy = card.damageHeavy = integerAllocationAlgorithm(card.damage, card.damageSection);
            }
            if(data[25].equals(""))
                card.updatedAttackEffect = AbstractGameAction.AttackEffect.NONE;
            else
                card.updatedAttackEffect = Enum.valueOf(AbstractGameAction.AttackEffect.class,data[25]);
            if(data[26].equals(""))
                card.animationString = null;
            card.animationString = data[26].split("&");
            return card;
        } catch (Exception e)
        {
            Log.log("sorry, the card "+id+" is not complete");
        }
        return null;
    }

    protected static int[] integerAllocationAlgorithm (Integer sum, int[] percent) {
        int s4;
        if(sum%2==0)
            s4=sum/2;
        else
            s4=sum/2+1;
        int rest = sum-s4;
        float temp=0;
        int[] result = new int[4];
        for(int i=0;i<3;i++)
        {
            float accuracy = rest*percent[i]/50f;
            result[i] = MathUtils.floor(temp+accuracy);
            temp = temp + accuracy-result[i];
        }
        result[3]=s4;
        return result;
    }

    @Override
    public AbstractCard makeStatEquivalentCopy()
    {
        AbstractTaiwuCard card = (AbstractTaiwuCard) super.makeStatEquivalentCopy();
        card.customValue1 = customValue1;
        card.customValue2 = customValue2;
        return card;
    }

    @Override
    public AbstractCard makeCopy() {
        return initCard(id,data);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            if(damageUpdateValue!=0)
                this.upgradeDamage(damageUpdateValue);
            if(blockUpdateValue!=0)
                this.upgradeBlock(blockUpdateValue);
            if(magicUpdateValue!=0)
                this.upgradeMagicNumber(magicUpdateValue);
            if(customValue1Updated!=0)
                this.upgradeCustomValue1(customValue1Updated);
            if(customValue2Updated!=0)
                this.upgradeCustomValue2(customValue2Updated);
            this.upgradeDescription();
            if(costUpdateValue!=0)
                this.upgradeBaseCost(Math.max(cost + costUpdateValue, 0));
        }
    }

    public void upgradeCustomValue1(int customValue1Updated)
    {
        baseCustomValue1 += customValue1Updated;
    }

    public void upgradeCustomValue2(int customValue2Updated)
    {
        baseCustomValue1 += customValue1Updated;
    }

    public void upgradeDescription()
    {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescriptionCN();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    {
        boolean result = super.canUse(p,m);
        if(alwaysEx)
            return result;
        if(isCtrlPressed())
        {
            return result&&hasEnoughShi(p,costShiTyp,costShiCount);
        }
        return result;
    }


    public boolean isCtrlPressed()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
        {
            return true;
        }
        return false;
    }
    private boolean hasEnoughShi(AbstractPlayer p, AttackType[] costShiType, int[] costShiCount)
    {
        if(costShiType==null)
            return true;
        ArrayList<AttackType> needShiCollection = new ArrayList<>();
        for(int i=0;i<costShiType.length;i++)
            for(int j=0;j<costShiCount[i];j++)
                needShiCollection.add(costShiType[i]);
        Iterator<AttackType> i = needShiCollection.iterator();
        ArrayList<AttackType> allShi = BattleController.instance.getAllAttackType();
        while (i.hasNext())
        {
            AttackType e = i.next();
            if(allShi.contains(e))
            {
                allShi.remove(e);
            }
            else
            {
                cantUseMessage = "没有足够的式来施展这张牌！";
                return false;
            }
        }
        return true;

    }

    @Override
    public void initializeDescriptionCN()
    {
        super.initializeDescriptionCN();
        super.description.clear();
        int numLines = 1;
        sbuilder.setLength(0);
        float currentWidth = 0.0F;
        for (String word : this.rawDescription.split(" "))
        {
            word = word.trim();
            if (Settings.manualLineBreak || Settings.manualAndAutoLineBreak || !word.contains("NL"))
            {
                if ((word.equals("NL") && sbuilder.length() == 0) || word.isEmpty())
                {
                    currentWidth = 0.0F;
                }
                else
                {
                    String keywordTmp = word;
                    if (GameDictionary.keywords.containsKey(keywordTmp))
                    {
                        if (!this.keywords.contains(keywordTmp))
                            this.keywords.add(keywordTmp);
                        gl.setText(FontHelper.cardDescFont_N, word);
                        if (currentWidth + gl.width > CN_DESC_BOX_WIDTH)
                        {
                            numLines++;
                            super.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                            sbuilder.setLength(0);
                            currentWidth = gl.width;
                            sbuilder.append(" *").append(word).append(" ");
                        }
                        else
                        {
                            sbuilder.append(" *").append(word).append(" ");
                            currentWidth += gl.width;
                        }
                    }
                    else if (!word.isEmpty() && word.charAt(0) == '[')
                    {
                        switch (this.color)
                        {
                            case RED:
                                if (!this.keywords.contains("[R]"))
                                    this.keywords.add("[R]");
                                break;
                            case GREEN:
                                if (!this.keywords.contains("[G]"))
                                    this.keywords.add("[G]");
                                break;
                            case BLUE:
                                if (!this.keywords.contains("[B]"))
                                    this.keywords.add("[B]");
                                break;
                            case PURPLE:
                            case COLORLESS:
                                if (!this.keywords.contains("[W]"))
                                    this.keywords.add("[W]");
                                break;
                            default:
                                Log.log("ERROR: Tried to display an invalid energy type: " + this.color.name());
                                break;
                        }
                        if (currentWidth + CARD_ENERGY_IMG_WIDTH > CN_DESC_BOX_WIDTH)
                        {
                            numLines++;
                            super.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                            sbuilder.setLength(0);
                            currentWidth = CARD_ENERGY_IMG_WIDTH;
                            sbuilder.append(" ").append(word).append(" ");
                        }
                        else
                        {
                            sbuilder.append(" ").append(word).append(" ");
                            currentWidth += CARD_ENERGY_IMG_WIDTH;
                        }
                    }
                    else if (word.equals("!D!"))
                    {
                        if (currentWidth + MAGIC_NUM_W > CN_DESC_BOX_WIDTH)
                        {
                            numLines++;
                            super.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                            sbuilder.setLength(0);
                            currentWidth = MAGIC_NUM_W;
                            sbuilder.append(" D ");
                        }
                        else
                        {
                            sbuilder.append(" D ");
                            currentWidth += MAGIC_NUM_W;
                        }
                    }
                    else if (word.equals("!B!"))
                    {
                        if (currentWidth + MAGIC_NUM_W > CN_DESC_BOX_WIDTH)
                        {
                            numLines++;
                            super.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                            sbuilder.setLength(0);
                            currentWidth = MAGIC_NUM_W;
                            sbuilder.append(" ").append(word).append("! ");
                        }
                        else
                        {
                            sbuilder.append(" ").append(word).append("! ");
                            currentWidth += MAGIC_NUM_W;
                        }
                    }
                    else if (word.equals("!M!"))
                    {
                        if (currentWidth + MAGIC_NUM_W > CN_DESC_BOX_WIDTH)
                        {
                            numLines++;
                            super.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                            sbuilder.setLength(0);
                            currentWidth = MAGIC_NUM_W;
                            sbuilder.append(" ").append(word).append("! ");
                        }
                        else
                        {
                            sbuilder.append(" ").append(word).append("! ");
                            currentWidth += MAGIC_NUM_W;
                        }
                    }
                    else if (TheScrollOfTaiwuTheSpire.isDynamicV(word,true))
                    {
                        if (currentWidth + MAGIC_NUM_W > CN_DESC_BOX_WIDTH)
                        {
                            numLines++;
                            super.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                            sbuilder.setLength(0);
                            currentWidth = MAGIC_NUM_W;
                            sbuilder.append(" $").append(word).append("$$ ");
                        }
                        else
                        {
                            sbuilder.append(" $").append(word).append("$$ ");
                            currentWidth += MAGIC_NUM_W;
                        }
                    }
                    else if ((Settings.manualLineBreak || Settings.manualAndAutoLineBreak) && word.equals("NL") && sbuilder
                            .length() != 0)
                    {
                        gl.width = 0.0F;
                        word = "";
                        super.description.add(new DescriptionLine(sbuilder.toString().trim(), currentWidth));
                        currentWidth = 0.0F;
                        numLines++;
                        sbuilder.setLength(0);
                    }
                    else if (word.charAt(0) == '*')
                    {
                        gl.setText(FontHelper.cardDescFont_N, word.substring(1));
                        if (currentWidth + gl.width > CN_DESC_BOX_WIDTH)
                        {
                            numLines++;
                            super.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                            sbuilder.setLength(0);
                            currentWidth = gl.width;
                            sbuilder.append(" ").append(word).append(" ");
                        }
                        else
                        {
                            sbuilder.append(" ").append(word).append(" ");
                            currentWidth += gl.width;
                        }
                    }
                    else
                    {
                        word = word.trim();
                        for (char c : word.toCharArray())
                        {
                            gl.setText(FontHelper.cardDescFont_N, String.valueOf(c));
                            if (!Settings.manualLineBreak)
                                if (currentWidth + gl.width > CN_DESC_BOX_WIDTH)
                                {
                                    numLines++;
                                    super.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
                                    sbuilder.setLength(0);
                                    currentWidth = gl.width;
                                    sbuilder.append(c);
                                }
                                else
                                {
                                    currentWidth += gl.width;
                                    sbuilder.append(c);
                                }
                        }
                    }
                }
            }
        }
        if (sbuilder.length() != 0)
            super.description.add(new DescriptionLine(sbuilder.toString(), currentWidth));
        int removeLine = -1;
        for (int i = 0; i < super.description.size(); i++) {
            if (super.description.get(i).text.equals(LocalizedStrings.PERIOD)) {
                super.description.get(i - 1).text += LocalizedStrings.PERIOD;
                removeLine = i;
            }
        }
        if (removeLine != -1)
            super.description.remove(removeLine);
    }

    @Override
    public void applyPowers()
    {
        super.applyPowers();
        if(damageSection!=null)
            damageHeavy = integerAllocationAlgorithm(damage,damageSection);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);
        if(damageSection!=null)
            damageHeavy = integerAllocationAlgorithm(damage,damageSection);
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
    public int getD(int index)
    {
        Log.log(id);
        Log.log("get D"+index);
        Log.log("return "+damageHeavy[--index]);
        if(index>=0&&index<damageHeavy.length)
            return damageHeavy[index];
        return 0;
    }

    public int getCustomValue(int index)
    {
        switch (index)
        {
            case 1:
                return customValue1;
            case 2:
                return customValue2;
            default:
                return 0;
        }
    }

    public void onDamageAllBeBlocked(AbstractPlayer p,AbstractMonster m){}
    public void onAnimationDone()
    {
        fixedDescription = false;
    }

    public void releaseDescription()
    {
        fixedDescription = false;
    }
    public void onXieLiToBuffer(){}
}
