package cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import controller.BattleController;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/13 22:52
 */
public class WuHuaBaMeng extends AbstractTaiwuCard
{
    public WuHuaBaMeng(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target)
    {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int count = BattleController.instance.getDiffrentAttackType() * magicNumber;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,m,new DexterityPower(p, count),count));
    }
}
