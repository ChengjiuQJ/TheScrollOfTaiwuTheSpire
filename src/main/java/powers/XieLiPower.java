package powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.BufferPower;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/17 0:25
 */
public class XieLiPower extends AbstractTaiwuPower
{
    public XieLiPower(String id, AbstractCreature owner)
    {
        super(id, owner);
        isTurnBased = true;
        updateDescription();
    }
    @Override
    public void updateDescription()
    {
        description =  DESCRIPTIONS[0];
    }

    @Override
    public void stackPower(int stackAmount)
    {
        super.stackPower(stackAmount);
        if(amount>=8)
        {
            int amt = amount/8;
            amount%=8;
            addToTop(new ApplyPowerAction(owner,owner,new BufferPower(owner,amt)));
            if(amount==0)
                addToTop(new RemoveSpecificPowerAction(owner,owner,this));
        }
    }
}
