package patches;

import cards.AbstractTaiwuCard;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/21 15:38
 */
@SpirePatch(
        clz = SingleCardViewPopup.class,
        method = "update"
)
public class SingleCardViewPopupPatch
{
    public static void Postfix(SingleCardViewPopup __instance, AbstractCard ___card)
    {
        if(___card instanceof AbstractTaiwuCard)
            ___card.update();
    }
}
