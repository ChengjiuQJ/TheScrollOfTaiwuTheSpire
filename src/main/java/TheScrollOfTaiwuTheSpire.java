import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import cards.DaZhuoShou;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

@SpireInitializer
public class TheScrollOfTaiwuTheSpire implements EditCardsSubscriber
{

    private static TheScrollOfTaiwuTheSpire instance;

    public static TheScrollOfTaiwuTheSpire getInstance()
    {
        return instance;
    }
    public TheScrollOfTaiwuTheSpire()
    {
        BaseMod.subscribe(this);
    }

    /**
     * 被mod框架直接调用的初始化方法
     */
    public static void initialize()
    {
        instance = new TheScrollOfTaiwuTheSpire();
    }

    @Override
    public void receiveEditCards()
    {
        BaseMod.addCard(new DaZhuoShou());
        UnlockTracker.seenPref.putInteger("DaZhuoShou",1);//设置为可见
    }

}
