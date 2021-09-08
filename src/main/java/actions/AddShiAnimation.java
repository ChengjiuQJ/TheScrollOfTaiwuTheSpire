package actions;

import UI.Shi;
import cards.AttackType;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import controller.BattleController;
import controller.TheScrollOfTaiwuTheSpire;
import reina.yui.Yui;

import java.util.ArrayList;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/8 17:56
 */
public class AddShiAnimation extends AbstractGameAction
{
    boolean isCalled;
    AttackType type;
    Shi shi;
    public AddShiAnimation(AttackType attackType)
    {
        type = attackType;
        isCalled = false;
        duration = 1F;
    }
    @Override
    public void update()
    {
        TheScrollOfTaiwuTheSpire.logger.info("addShiAnimation start");
        if(!isCalled)
        {
            shi = BattleController.instance.addShi(type);
            isCalled = true;
        }
        if(Math.abs(shi.getX()-shi.des.x)<5f&&Math.abs(shi.getY()-shi.des.y)<5f)
            isDone = true;
        else if(!isDone)
        {
            ArrayList<Shi> shis =BattleController.instance.getAllShi();
            for(int i=0;i<shis.size();i++)
            {
                Shi shi = shis.get(i);
                if(Math.abs(shi.getX()-shi.des.x)<5F&&Math.abs(shi.getY()-shi.des.y)<5F)
                    continue;
                else
                {
                    float setX = MathUtils.lerp(shi.getX(),shi.des.x,(1F-duration)/duration);
                    TheScrollOfTaiwuTheSpire.logger.info("setX = "+setX);
                    shi.setX(setX);
                    float setY = MathUtils.lerp(shi.getY(),shi.des.y,(1F-duration)/duration);
                    shi.setY(setY);
                    TheScrollOfTaiwuTheSpire.logger.info("shi "+shi.attackType.toString()+" position=("+shi.getX()+","+shi.getY()+"),des=("+shi.des.x+","+shi.des.y+")");
                }
            }
        }
        tickDuration();
    }
}
