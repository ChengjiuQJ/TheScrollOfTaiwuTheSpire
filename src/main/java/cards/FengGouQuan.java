package cards;

import actions.CostShiAction;
import actions.GainSHIAction;
import actions.PlayerAnimation;
import actions.ReleaseDescriptionAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/20 23:10
 */
public class FengGouQuan extends AbstractTaiwuCard
{
    public FengGouQuan(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target)
    {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        super.use(p,m);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), basicAttackEffect));
        AbstractDungeon.actionManager.addToBottom(new GainSHIAction(p,getShiTyp,getShiCount));
        int rnd = AbstractDungeon.cardRandomRng.random(customValue1,customValue2);
        if(rnd!=0)
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new StrengthPower(p,rnd)));
        addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), 1));
        AbstractDungeon.actionManager.addToBottom(new ReleaseDescriptionAction());
    }
}
