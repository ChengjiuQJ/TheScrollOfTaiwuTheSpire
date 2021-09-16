package powers;

import characters.Taiwu;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import controller.BattleController;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/15 23:45
 */
public class QiXingPower extends AbstractTaiwuPower
{
    public QiXingPower(String id, AbstractCreature owner)
    {
        super(id, owner);
        amount = 3;
        type = PowerType.BUFF;
        updateDescription();
        isTurnBased=true;
    }
    @Override
    public void updateDescription()
    {
        description =  DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn(){
        if(BattleController.instance.getAllAttackType().size()==6)
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(amount));
    }
}
