package DynamicVariables;

import basemod.abstracts.DynamicVariable;
import cards.AbstractTaiwuCard;
import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/15 22:29
 */
public class S4 extends DynamicVariable
{
    @Override
    public String key()
    {
        return "S4";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard)
    {
        return abstractCard.isDamageModified;
    }

    @Override
    public int value(AbstractCard abstractCard)
    {
        AbstractTaiwuCard card = (AbstractTaiwuCard)abstractCard;
        return card.getD(4);
    }

    @Override
    public int baseValue(AbstractCard abstractCard)
    {
        AbstractTaiwuCard card = (AbstractTaiwuCard)abstractCard;
        return card.getD(4);
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard)
    {
        return abstractCard.upgraded;
    }
}
