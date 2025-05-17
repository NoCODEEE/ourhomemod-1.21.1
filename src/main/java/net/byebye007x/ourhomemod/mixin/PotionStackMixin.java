package net.byebye007x.ourhomemod.mixin;

import net.byebye007x.ourhomemod.ModConfig;
import net.minecraft.item.Item;
import net.minecraft.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PotionItem.class)
public abstract class PotionStackMixin extends Item {
    public PotionStackMixin(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxCount() {
        return ModConfig.potionStackSize;
    }
}
