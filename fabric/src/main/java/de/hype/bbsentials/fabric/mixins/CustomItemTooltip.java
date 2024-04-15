package de.hype.bbsentials.fabric.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import de.hype.bbsentials.client.common.api.Formatting;
import de.hype.bbsentials.client.common.client.BBsentials;
import de.hype.bbsentials.fabric.Utils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtElement;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Mixin(HandledScreen.class)
public abstract class CustomItemTooltip<T extends ScreenHandler> extends Screen implements ScreenHandlerProvider<T> {

    @Shadow
    @Final
    protected T handler;
    @Shadow
    @Nullable
    protected Slot focusedSlot;
    @Shadow
    @Final
    protected Text playerInventoryTitle;

    protected CustomItemTooltip(Text title) {
        super(title);
    }

    @Shadow
    protected abstract List<Text> getTooltipFromItem(ItemStack stack);

    @ModifyExpressionValue(method = "drawMouseoverTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;getStack()Lnet/minecraft/item/ItemStack;"))
    private ItemStack modfiedItemStack(ItemStack original) {
        ItemStack stack = original.copy();
        String stackItemName = stack.getName().getString();

        if (stack.getItem() == Items.EMERALD_BLOCK || stack.getItem() == Items.IRON_BLOCK || stack.getItem() == Items.PAPER) {
            Utils.doBingoRankManipulations(stack);
        }
        if (BBsentials.developerConfig.hypixelItemInfo) {
            Utils.addDebugInfoToRender(stack);
        }
        if (BBsentials.funConfig.hub17To29Troll) {
            if (stackItemName.equals("SkyBlock Hub #17")) {
                stack.setCustomName(Text.translatable("§aSkyBlock Hub #29"));
            }
        }
        if (BBsentials.funConfig.hub29Troll) {
            if (stackItemName.startsWith("SkyBlock Hub #")) {
                stack.setCustomName(Text.translatable("§aSkyBlock Hub #29 (" + stackItemName.replaceAll("\\D", "") + ")"));
            }
        }
        stack.setCustomName(Text.translatable("§aSkyBlock Hub #29 (" + stackItemName.replaceAll("\\D", "") + ")"));
        return stack;
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onConstructor(ScreenHandler handler, PlayerInventory inventory, Text title, CallbackInfo ci) {
        if (title.getString().equals("SkyBlock Hub Selector")) {
            BBsentials.executionService.schedule(() -> {
                int lowestYetAmount = Integer.MAX_VALUE;
                String lowestYetHubName = "";
                for (Slot slot : handler.slots) {
                    ItemStack stack = slot.getStack();
                    if (stack.getItem() == Items.BLACK_STAINED_GLASS_PANE) continue;
                    try {
                        String data = stack.getNbt().getCompound("display").getList("Lore", NbtElement.STRING_TYPE).get(0).asString();
                        int amount = Integer.parseInt(Text.Serialization.fromJson(data).getString().replace("Players:", "").split("/")[0].trim());
                        if (amount < lowestYetAmount) {
                            lowestYetAmount = amount;
                            lowestYetHubName = (stack.getName().getString());
                        }
                    } catch (Exception e) {
                    }
                }
                BBsentials.splashConfig.smallestHubName = lowestYetHubName;
            }, 30, TimeUnit.MILLISECONDS);
        }
    }


    @Unique
    private Text getSmallestHubText() {
        return Text.of(Formatting.DARK_PURPLE + "Least Player Hub: Hub #" + BBsentials.splashConfig.smallestHubName.replaceAll("\\D", ""));
    }

    @Inject(method = "close", at = @At("HEAD"))
    private void onClose(CallbackInfo ci) {
        BBsentials.splashConfig.smallestHubName = null;
    }
}
