package net.byebye007x.ourhomemod.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.network.ServerPlayerEntity;

public class FlightEffect extends StatusEffect {
    public FlightEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayerEntity player) {
            if (player.hasStatusEffect(ModEffects.FLIGHT)) {
                // Always ensure flight is enabled
                player.getAbilities().allowFlying = true;
                player.sendAbilitiesUpdate();
            } else {
                // Effect expired → disable flight
                if (!player.isCreative() && !player.isSpectator()) {
                    player.getAbilities().flying = false;         // stop flying
                    player.getAbilities().allowFlying = false;    // remove flight permission
                    player.sendAbilitiesUpdate();
                }
            }
        }
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onEntityRemoval(LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        if (entity instanceof ServerPlayerEntity player && Entity.RemovalReason.KILLED.shouldDestroy()) {
            player.getAbilities().allowFlying = false;
            player.sendAbilitiesUpdate();
        }
        super.onEntityRemoval(entity, amplifier, reason);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
