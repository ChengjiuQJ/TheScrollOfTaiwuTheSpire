package cards;

import actions.GainBlockDynamically;
import actions.GainSHIAction;
import actions.ReleaseDescriptionAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import powers.AbstractTaiwuPower;
import powers.XieLiPower;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/17 0:15
 */
public class BaGuaBu extends AbstractTaiwuCard
{
    public BaGuaBu(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target)
    {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        super.use(p,m);
        AbstractDungeon.actionManager.addToBottom(new GainSHIAction(p,getShiTyp,getShiCount));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, AbstractTaiwuPower.initPower("XieLi",p,magicNumber)));
        AbstractDungeon.actionManager.addToBottom(new GainBlockDynamically(p,()->{
            AbstractPower power = p.getPower("XieLi");
            if(power==null)
                return 0;
            else
                return  Math.max(power.amount,0);
        }));
        AbstractDungeon.actionManager.addToBottom(new ReleaseDescriptionAction());
    }
}
