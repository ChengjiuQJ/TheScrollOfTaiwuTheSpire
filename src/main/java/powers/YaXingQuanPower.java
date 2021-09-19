package powers;

import cards.AttackType;
import com.megacrit.cardcrawl.core.AbstractCreature;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/16 22:29
 */
public class YaXingQuanPower extends AbstractTaiwuPower
{
    public YaXingQuanPower(String id, AbstractCreature owner)
    {
        super(id, owner);
        type = PowerType.BUFF;
        amount=1;
        autoDecreaseAfterTurn = true;
    }

    @Override
    public void updateDescription()
    {
        description =  DESCRIPTIONS[0];
    }

    @Override
    public AttackType onAttackTypeGet(AttackType attackType)
    {
        if(attackType==AttackType.崩||attackType==AttackType.点)
            return AttackType.拿;
        return attackType;
    }

}
