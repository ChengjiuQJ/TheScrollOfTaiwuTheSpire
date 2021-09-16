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
    }

    @Override
    public void updateDescription()
    {
        description =  DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
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
