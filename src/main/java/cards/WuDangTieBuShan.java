package cards;

import actions.CostShiAction;
import actions.GainSHIAction;
import actions.PlayerAnimation;
import actions.ReleaseDescriptionAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.AbstractTaiwuPower;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/21 17:32
 */
public class WuDangTieBuShan extends AbstractTaiwuCard
{
    public WuDangTieBuShan(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target)
    {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        super.use(p,m);
        addToBot(new GainBlockAction(p,block));
        addToBot(new ApplyPowerAction(p,p, AbstractTaiwuPower.initPower("XieLi",p,magicNumber)));
        AbstractDungeon.actionManager.addToBottom(new ReleaseDescriptionAction());
    }
}
