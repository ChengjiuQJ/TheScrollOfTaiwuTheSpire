package UI;

import cards.AttackType;
import com.badlogic.gdx.graphics.Texture;
import org.jetbrains.annotations.Nullable;
import reina.yui.Yui;
import reina.yui.YuiClickableObject;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/8 16:23
 */
public class Shi extends YuiClickableObject
{

    public AttackType attackType;
    public ShiPlaceHolder des;

    public Shi(@Nullable Texture texture, float x, float y,AttackType type)
    {
        super(texture, x, y);
        attackType = type;
        this.des = null;
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
