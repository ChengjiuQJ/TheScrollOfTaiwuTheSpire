package actions;

import UI.Shi;
import Utils.Log;
import cards.AttackType;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import controller.BattleController;
import controller.TheScrollOfTaiwuTheSpire;

import java.util.ArrayList;


/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/8 23:31
 */
public class DecreaseShiAnimation extends AbstractGameAction
{
    AttackType attackType;
    boolean isCalled;
    public DecreaseShiAnimation(AttackType attackType)
    {
        this.attackType = attackType;
        isCalled = false;
        duration=0.5F;
    }
    @Override
    public void update()
    {
        Log.log("decrease shi start");
        if(!isCalled)
        {
            BattleController.instance.costShi(attackType);
            isCalled = true;
        }
        ArrayList<Shi> shis =BattleController.instance.getAllShi();
        for(int i=0;i<shis.size();i++)
        {
            Shi shi = shis.get(i);
            if(Math.abs(shi.getX()-shi.des.x)<5F&&Math.abs(shi.getY()-shi.des.y)<5F)
                continue;
            else
            {
                float setX = MathUtils.lerp(shi.getX(),shi.des.x,(0.5F-duration)/duration);
                Log.log("setX = "+setX);
                shi.setX(setX);
                float setY = MathUtils.lerp(shi.getY(),shi.des.y,(0.5F-duration)/duration);
                shi.setY(setY);
                Log.log("shi "+shi.attackType.toString()+" position=("+shi.getX()+","+shi.getY()+"),des=("+shi.des.x+","+shi.des.y+")");
            }
        }
        tickDuration();
    }
}
