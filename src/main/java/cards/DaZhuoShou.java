package cards;

import actions.CostShiAction;
import actions.GainSHIAction;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import controller.BattleController;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/5 23:30
 */
public class DaZhuoShou extends CustomCard
{
    public static final String ID = "大拙手";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    public static AbstractCard.CardColor cardColor = cards.CardColor.TAIWU_COLOR;

    private static final int COST = 2;
    private static final int ATTACK_DMG = 9;
    private static final int UPGRADE_PLUS_DMG = 3;//升级提升的攻击数值
    public static AbstractCard.CardType cardType = CardType.ATTACK;
    private static CardRarity CARD_RARITY = CardRarity.RARE;
    private static CardTarget CARD_TARGET = CardTarget.ENEMY;
    private static final AttackType[] GET_SHI_TYPE = new AttackType[]{AttackType.BENG};//获得式的种类
    private static final int[] GET_SHI_COUNT=new int[]{1};//获得的式的数量
    private static final AttackType[] COST_SHI_TYPE = new AttackType[]{AttackType.BENG};//获得式的种类
    private static final int[] COST_SHI_COUNT=new int[]{2};//获得的式的数量
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;

    public DaZhuoShou()
    {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                cardType, cardColor,
                CARD_RARITY, CARD_TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
        this.damage=this.baseDamage = ATTACK_DMG;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new CostShiAction(COST_SHI_TYPE,COST_SHI_COUNT));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DaZhuoShou();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
        }
    }
}
