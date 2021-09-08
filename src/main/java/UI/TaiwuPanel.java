package UI;

import Utils.TextureLoader;
import cards.AttackType;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.Settings;
import org.jetbrains.annotations.Nullable;
import reina.yui.Yui;
import reina.yui.YuiClickableObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/7 15:03
 */
public class TaiwuPanel extends YuiClickableObject
{
    public static final float SHOW_X = 30f;
    public static final float SHOW_Y = 600;
    public static final float HIDE_X = 0f;
    public static final float HIDE_Y = 0f;
    public static final Texture PANEL = TextureLoader.getTexture("img/ui/panel.png");

    public static final String ID = "taiwuPanel";

    public TaiwuPanel(@Nullable Texture texture, float x, float y)
    {
        super(texture, x, y);
    }

    public TaiwuPanel()
    {
        this(PANEL,SHOW_X,SHOW_Y);
    }


    @Override
    protected void onUnhover()
    {

    }

    @Override
    protected void onClick()
    {

    }
}
