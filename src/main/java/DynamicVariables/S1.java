package DynamicVariables;

import basemod.abstracts.DynamicVariable;
import cards.AbstractTaiwuCard;
import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/15 22:00
 */
public class S1 extends DynamicVariable
{
    @Override
    public String key()
    {
        return "S1";
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
        return card.getD(1);
    }

    @Override
    public int baseValue(AbstractCard abstractCard)
    {
        AbstractTaiwuCard card = (AbstractTaiwuCard)abstractCard;
        return card.getD(1);
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard)
    {
        return abstractCard.upgraded;
    }
}
