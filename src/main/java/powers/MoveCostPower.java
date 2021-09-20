package powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/16 23:28
 */
public class MoveCostPower extends AbstractTaiwuPower
{
    public MoveCostPower(String id, AbstractCreature owner)
    {
        super(id, owner);
        isTurnBased = false;
        autoDecreaseBeforeTurn = true;
    }

    public MoveCostPower(String id, AbstractCreature owner,int amt)
    {
        super(id, owner);
        isTurnBased = false;
        autoDecreaseBeforeTurn = true;
        amount = amt;
    }

    @Override
    public void updateDescription()
    {
        description =  powerStrings.DESCRIPTIONS[0]+amount+powerStrings.DESCRIPTIONS[1];
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType)
    {
        if(damageType== DamageInfo.DamageType.NORMAL)
        {
            return Math.max(damage-amount,0);
        }
        return damage;
    }
}
