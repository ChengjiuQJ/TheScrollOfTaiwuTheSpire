package cards;

import actions.CostShiAction;
import actions.GainSHIAction;
import actions.PlayerAnimation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/12 23:56
 */
public class XiaoZongYueGong extends AbstractTaiwuCard
{
    public XiaoZongYueGong(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target)
    {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,block));
    }
}
