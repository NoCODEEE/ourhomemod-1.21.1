package net.byebye007x.ourhomemod.mixin;

import net.byebye007x.ourhomemod.ModConfig;
import net.minecraft.item.Item;
import net.minecraft.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PotionItem.class)
public abstract class PotionItemMixin {
    @ModifyArg(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;<init>(Lnet/minecraft/item/Item$Settings;)V"),
            index = 0
    )
    private static Item.Settings modifySettings(Item.Settings original) {
        return original.maxCount(ModConfig.potionStackSize);
    }
}
