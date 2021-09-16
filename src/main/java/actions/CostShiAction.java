package actions;

import cards.AttackType;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/8 22:50
 */
public class CostShiAction extends AbstractGameAction
{
    AttackType[] attackTypes;
    int[] amounts;
    public CostShiAction(AttackType[] types,int[] amounts)
    {
        attackTypes = types;
        this.amounts = amounts;
    }
    @Override
    public void update()
    {
        for(int i=0;i<attackTypes.length;i++)
            for (int j=0;j<amounts[i];j++)
                AbstractDungeon.actionManager.addToTop(new DecreaseShiAnimation(attackTypes[i]));
        isDone = true;
    }
}
