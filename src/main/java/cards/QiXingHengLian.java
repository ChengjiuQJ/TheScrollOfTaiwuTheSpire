package cards;

import Utils.Log;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import powers.AbstractTaiwuPower;
import powers.QiXingPower;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/14 10:57
 */
public class QiXingHengLian extends AbstractTaiwuCard
{
    public QiXingHengLian(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target)
    {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        isEthereal = true;
    }

    @Override
    public void upgrade()
    {
        super.upgrade();
        isEthereal = false;
    }
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster)
    {
        AbstractPower power = AbstractTaiwuPower.initPower("QiXing",abstractPlayer);
        Log.log(power.name);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer,abstractPlayer,power ));
    }
}
