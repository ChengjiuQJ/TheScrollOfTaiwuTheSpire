package Utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/6 23:31
 */
public class TextureLoader
{
    private static HashMap<String, Texture> textures = new HashMap<>();

    public static final Logger logger = LogManager.getLogger(TextureLoader.class.getName());

    public TextureLoader(String s) {}

    public static Texture getTexture(String textureString) {
        if (textures.get(textureString) == null)
            try {
                loadTexture(textureString);
            } catch (GdxRuntimeException e) {
                logger.error("Could not find texture: " + textureString);
                return getTexture("theDefaultResources/images/ui/missing_texture.png");
            }
        return textures.get(textureString);
    }

    private static void loadTexture(String textureString) throws GdxRuntimeException {
        logger.info("AtS | Loading Texture: " + textureString);
        Texture texture = new Texture(textureString);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textures.put(textureString, texture);
    }
}
