package DynamicVariables;

import basemod.abstracts.DynamicVariable;
import cards.AbstractTaiwuCard;
import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/20 23:33
 */
public class C1 extends DynamicVariable
{
    @Override
    public String key()
    {
        return "C1";
    }

    @Override
    public boolean isModified(AbstractCard abstractCard)
    {
        return false;
    }

    @Override
    public int value(AbstractCard abstractCard)
    {
        AbstractTaiwuCard card = (AbstractTaiwuCard)abstractCard;
        return card.getCustomValue(1);
    }

    @Override
    public int baseValue(AbstractCard abstractCard)
    {
        AbstractTaiwuCard card = (AbstractTaiwuCard)abstractCard;
        return card.getCustomValue(1);
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard)
    {
        return abstractCard.upgraded;
    }
}
