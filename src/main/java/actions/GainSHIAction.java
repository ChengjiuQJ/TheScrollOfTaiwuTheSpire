package actions;

import cards.AttackType;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import controller.BattleController;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/7 23:20
 */
public class GainSHIAction extends AbstractGameAction
{
    boolean isCalled;
    AbstractPlayer target;
    AttackType[] types;
    int[] amount;
    public GainSHIAction(AbstractPlayer player,AttackType[] types,int[] amount)
    {
        target = player;
        this.types = types;
        this.amount = amount;
        isCalled = false;
    }
    @Override
    public void update()
    {
        for(int i=0;i<types.length;i++)
            for (int j=0;j<amount[i];j++)
                AbstractDungeon.actionManager.addToBottom(new AddShiAnimation(types[i]));
        isDone = true;
    }
}
