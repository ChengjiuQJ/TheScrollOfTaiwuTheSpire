package actions;

import cards.AbstractTaiwuCard;
import characters.Taiwu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Event;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.ArrayList;
import java.util.concurrent.Delayed;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/9 0:09
 */
public class PlayerAnimation extends AbstractGameAction
{

    AbstractPlayer player;
    AbstractMonster monster;
    boolean isCalled;
    String[] animationString;
    AttackEffect attackEffect;
    ArrayList<Integer> damages;
    int index = 0;
    boolean hasMoved;
    boolean hasPreAttacked;
    boolean hasAttacked;
    boolean isBack;
    float Timer;
    float desX;
    float desY;
    boolean brokenBlock;
    AnimationState.AnimationStateListener listener;
    public PlayerAnimation(AbstractPlayer p, AbstractMonster m, String[] animationString, AttackEffect attackEffect, ArrayList<Integer> damages)
    {
        isCalled = false;
        player = p;
        monster = m;
        this.attackEffect = attackEffect;
        this.animationString = animationString;
        this.damages = damages;
    }
    @Override
    public void update()
    {
        if(!isCalled)
        {
            AnimationState.TrackEntry e = player.state.setAnimation(0,"C_003",false);
            Timer = 0F;
            desX = monster.drawX-player.drawX-100F;
            duration = e.getEndTime();
            hasMoved = false;
            isCalled = true;
        }
        if(!hasMoved)
        {
            Timer+=Gdx.graphics.getDeltaTime();
            player.animX = MathUtils.lerp(player.animX,desX,Timer/duration);
            if(Timer>duration){
                Timer = 0F;
                hasMoved = true;
                hasPreAttacked = false;
                duration = player.state.setAnimation(0,animationString[0]+"_0",false).getEndTime();
                duration += player.state.addAnimation(0,animationString[0]+"_1_1",false,0F).getEndTime();
            }
        }
        else if(!hasPreAttacked)
        {
            Timer+=Gdx.graphics.getDeltaTime();
            if(Timer>duration){
                // TODO: 2021/9/16 添加太吾音效
                Timer = 0F;
                hasPreAttacked = true;
                hasAttacked = false;
                brokenBlock = false;
                duration = player.state.setAnimation(0,animationString[1],false).getEndTime();
                player.state.addListener(listener=new AnimationState.AnimationStateAdapter()
                {
                    @Override
                    public void event(int trackIndex, Event event)
                    {
                        super.event(trackIndex, event);
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(monster.hb.cX, monster.hb.cY, attackEffect, false));
                        monster.damage(new DamageInfo(player, damages.get(index)));
                        if(monster.lastDamageTaken>0)
                            brokenBlock = false;
                        index++;
                        if(monster.isDead)
                        {
                            hasAttacked = true;
                            brokenBlock = true;
                            Timer = 0F;
                            isBack =false;
                            player.state.removeListener(listener);
                            duration = player.state.setAnimation(0,"C_004",false).getEndTime();
                        }
                    }
                });
            }
        }
        else if(!hasAttacked)
        {
            Timer+= Gdx.graphics.getDeltaTime();
            if(Timer>duration)
            {
                Timer = 0F;
                hasAttacked = true;
                isBack =false;
                player.state.removeListener(listener);
                duration = player.state.setAnimation(0,"C_004",false).getEndTime();
            }
        }
        else if(!isBack)
        {
            Timer+=Gdx.graphics.getDeltaTime();
            player.animX = MathUtils.lerp(player.animX,0F,Timer/duration);
            if(Timer>duration){
                Timer = 0F;
                isBack = true;
                player.state.setAnimation(0,"C_000",true);
                Taiwu p = (Taiwu)player;
                p.resetFootRotation();
                AbstractTaiwuCard card = (AbstractTaiwuCard) player.cardInUse;
                if(!brokenBlock)
                {
                    card.onDamageAllBeBlocked(player,monster);
                }
                card.onAnimationDone();
                isDone = true;
            }
        }
    }
}
