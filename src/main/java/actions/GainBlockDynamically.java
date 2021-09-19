package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/19 23:42
 */
public class GainBlockDynamically extends AbstractGameAction
{

    AbstractPlayer player;
    Supplier<Integer> supplier;
    public GainBlockDynamically(AbstractPlayer p, Supplier<Integer> callback)
    {
        player = p;
        supplier = callback;
    }
    @Override
    public void update()
    {
        addToTop(new GainBlockAction(player,player,supplier.get()));
        isDone = true;
    }
}
