package UI;

import Utils.TextureLoader;
import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/7 15:03
 */
public class TaiwuPanel extends TopPanelItem
{
    public static final float SHOW_X = 0f;
    public static final float SHOW_Y = 0f;
    public static final float HIDE_X = 0f;
    public static final float HIDE_Y = 0f;
    public static final Texture TEXTURE = TextureLoader.getTexture("img/ui/panel.png");
    public static final String ID = "taiwuPanel";

    public TaiwuPanel()
    {
        super(TEXTURE,ID);
    }

    @Override
    protected void onClick()
    {

    }
}
