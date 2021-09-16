package actions;

import cards.AbstractTaiwuCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/16 22:17
 */
public class ReleaseDescriptionAction extends AbstractGameAction
{
    @Override
    public void update()
    {
        AbstractTaiwuCard card =(AbstractTaiwuCard) AbstractDungeon.player.cardInUse;
        card.releaseDescription();
        isDone = true;
    }
}
