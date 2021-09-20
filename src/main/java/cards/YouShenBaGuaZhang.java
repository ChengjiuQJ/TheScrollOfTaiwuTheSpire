package cards;

import actions.CostShiAction;
import actions.GainSHIAction;
import actions.PlayerAnimation;
import actions.ReleaseDescriptionAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.function.Predicate;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/17 12:26
 */
public class YouShenBaGuaZhang extends AbstractTaiwuCard
{
    public YouShenBaGuaZhang(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target)
    {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        super.use(p,m);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), basicAttackEffect));
        AbstractDungeon.actionManager.addToBottom(new GainSHIAction(p,getShiTyp,getShiCount));
        AbstractDungeon.actionManager.addToBottom(new ReleaseDescriptionAction());
    }

    @Override
    public void onXieLiToBuffer()
    {
        AbstractTaiwuCard card = this;
        AbstractDungeon.actionManager.addToBottom(new FetchAction(AbstractDungeon.player.discardPile, new Predicate<AbstractCard>()
        {
            @Override
            public boolean test(AbstractCard abstractCard)
            {
                return abstractCard==card;
            }
        }));
    }
}
