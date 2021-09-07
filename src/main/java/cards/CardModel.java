package cards;

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
 * Create by 2021/9/7 15:18
 */
public class CardModel extends CustomCard
{
    // TODO: need to edit
    public static final String ID = "大拙手";

    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/"+ID+".png";
    public static AbstractCard.CardColor cardColor = cards.CardColor.TAIWU_COLOR;


    private static final int COST = 1;//消耗的能量
    private static final int ATTACK_DMG = 5;//基础攻击数值
    private static final int UPGRADE_PLUS_DMG = 3;//升级提升的攻击数值
    private static final CardType cardType = CardType.ATTACK;
    private static final CardRarity CARD_RARITY = CardRarity.SPECIAL;
    private static final CardTarget CARD_TARGET = CardTarget.NONE;
    private static final AttackType[] GET_SHI_TYPE = new AttackType[]{AttackType.BENG};//获得式的种类
    private static final int[] GET_SHI_COUNT=new int[]{1};//获得的式的数量
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;

    public CardModel()
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
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        BattleController.instance.gainShi(GET_SHI_TYPE,GET_SHI_COUNT);
    }

    // TODO: need to edit
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
