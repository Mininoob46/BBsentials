package de.hype.bbsentials.fabric.mixins.mixinaccessinterfaces;

import net.minecraft.text.Text;

import java.util.List;

public interface ICusomItemDataAccess {
    List<Text> BBsentialsAll$getItemRenderTooltip();

    String BBsentialsAll$getCount();

    String BBsentialsAll$getCustomItemTexture();
}
