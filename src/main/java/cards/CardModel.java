package cards;

import actions.CostShiAction;
import actions.GainSHIAction;
import actions.PlayerAnimation;
import actions.ReleaseDescriptionAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/7 15:18
 */
public class CardModel extends AbstractTaiwuCard
{

    public CardModel(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target)
    {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        super.use(p,m);
        if(isCtrlPressed()||alwaysEx)
        {
            AbstractDungeon.actionManager.addToBottom(new CostShiAction(costShiTyp,costShiCount));
            AbstractDungeon.actionManager.addToBottom(new PlayerAnimation(p,m, animationString,updatedAttackEffect,damageHeavy));
        }
        else
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), basicAttackEffect));
            AbstractDungeon.actionManager.addToBottom(new GainSHIAction(p,getShiTyp,getShiCount));
        }
        AbstractDungeon.actionManager.addToBottom(new ReleaseDescriptionAction());
    }
}
