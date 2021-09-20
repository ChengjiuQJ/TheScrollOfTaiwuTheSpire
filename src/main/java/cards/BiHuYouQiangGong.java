package cards;

import actions.CostShiAction;
import actions.GainSHIAction;
import actions.PlayerAnimation;
import actions.ReleaseDescriptionAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/20 22:09
 */
public class BiHuYouQiangGong extends AbstractTaiwuCard
{
    public BiHuYouQiangGong(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target)
    {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        super.use(p,m);
        for(int i=0;i<magicNumber;i++)
        {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,p,block));
        }

        AbstractDungeon.actionManager.addToBottom(new ReleaseDescriptionAction());
    }
}
