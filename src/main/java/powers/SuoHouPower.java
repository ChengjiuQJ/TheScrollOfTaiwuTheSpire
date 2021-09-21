package powers;

import cards.AbstractTaiwuCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/20 23:48
 */
public class SuoHouPower extends AbstractTaiwuPower
{
    public SuoHouPower(String id, AbstractCreature owner, int amt)
    {
        super(id, owner, amt);
        type = PowerType.DEBUFF;
    }

    @Override
    public void updateDescription()
    {
        description =   powerStrings.DESCRIPTIONS[0]+amount+powerStrings.DESCRIPTIONS[1];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        this.flash();
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new CleaveEffect()));
        } else {
            this.addToBot(new VFXAction(this.owner, new CleaveEffect(), 0.2F));
        }
        this.addToBot(new DamageAction(owner,new DamageInfo(owner, Math.max(0,amount), DamageInfo.DamageType.HP_LOSS)));
    }


    @Override
    public void atEndOfTurn(boolean isPlayer)
    {
        addToTop(new RemoveSpecificPowerAction(owner,owner,"SuoHou"));
    }
}
