package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import controller.BattleController;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/7 19:38
 */
public class BiHuYouQiangGong extends CustomCard
{
    public static final String ID = "壁虎游墙功";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    public static AbstractCard.CardColor cardColor = cards.CardColor.TAIWU_COLOR;


    private static final int COST = 1;//消耗的能量
    private static final int DATA_1 = 5;//基础数值
    private static final int UP_DATA_1_PLUS = 3;
    public static AbstractCard.CardType cardType = AbstractCard.CardType.ATTACK;
    private static AbstractCard.CardRarity CARD_RARITY = CardRarity.BASIC;
    private static AbstractCard.CardTarget CARD_TARGET = CardTarget.SELF;

    public BiHuYouQiangGong()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                cardType, cardColor,
                CARD_RARITY, CARD_TARGET);
        block = baseBlock = DATA_1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,this.block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BiHuYouQiangGong();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeBlock(UP_DATA_1_PLUS);
        }
    }
}
