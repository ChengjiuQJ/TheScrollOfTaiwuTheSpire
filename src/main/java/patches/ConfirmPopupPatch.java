package patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.options.ConfirmPopup;
import controller.BattleController;

/**
 * @author 57680
 * @version 1.0
 * Create by 2021/9/21 16:08
 */
@SpirePatch(
        clz=ConfirmPopup.class,
        method = "yesButtonEffect"
)
public class ConfirmPopupPatch
{
    public static void Postfix(ConfirmPopup __instance)
    {
        if(__instance.type == ConfirmPopup.ConfirmType.ABANDON_MID_RUN || __instance.type == ConfirmPopup.ConfirmType.ABANDON_MAIN_MENU
                ||__instance.type== ConfirmPopup.ConfirmType.EXIT)
            BattleController.instance.HidePanel();
    }
}
