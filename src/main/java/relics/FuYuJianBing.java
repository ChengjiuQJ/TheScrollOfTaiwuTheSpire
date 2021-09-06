package relics;

import Utils.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/6 21:39
 */
public class FuYuJianBing extends CustomRelic
{
    public static final String ID = "FuYuJianBing";
    private static final int HP_PER_CARD = 1;
    private static final Texture IMG = TextureLoader.getTexture("img/relics/伏虞剑柄.png");
    public FuYuJianBing() {
        super(ID, IMG, // you could create the texture in this class if you wanted too
                RelicTier.UNCOMMON, LandingSound.MAGICAL); // this relic is uncommon and sounds magic when you click it

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; //+ DESCRIPTIONS[1]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onEquip() {

    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new FuYuJianBing();
    }
}
