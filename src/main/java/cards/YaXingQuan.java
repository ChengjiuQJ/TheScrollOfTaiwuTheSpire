package cards;

import actions.CostShiAction;
import actions.GainSHIAction;
import actions.PlayerAnimation;
import actions.ReleaseDescriptionAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.AbstractTaiwuPower;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/16 22:22
 */
public class YaXingQuan extends AbstractTaiwuCard
{
    public YaXingQuan(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target)
    {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        super.use(p,m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, AbstractTaiwuPower.initPower(className,p)));
        AbstractDungeon.actionManager.addToBottom(new ReleaseDescriptionAction());

    }
}
