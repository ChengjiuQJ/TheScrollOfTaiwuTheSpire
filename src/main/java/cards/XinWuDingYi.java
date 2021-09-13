package cards;

import actions.GainSHIAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import controller.BattleController;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/13 23:17
 */
public class XinWuDingYi extends AbstractTaiwuCard
{
    public XinWuDingYi(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target)
    {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster)
    {
        AttackType[] attackTypes;
        if(BattleController.instance.getRecentAttackType()!=null)
            attackTypes = new AttackType[]{BattleController.instance.getRecentAttackType()};
        else
            attackTypes = new AttackType[0];
        AbstractDungeon.actionManager.addToBottom(new GainSHIAction(abstractPlayer,attackTypes,new int[]{magicNumber} ));
    }
}
