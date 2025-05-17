package net.byebye007x.ourhomemod;

import net.byebye007x.ourhomemod.effect.ModEffects;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class EventCheck {

    public static void FlightEffectCheck() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                if (!player.hasStatusEffect(ModEffects.FLIGHT)) {
                    if (!player.isCreative() && !player.isSpectator()) {
                        if (player.getAbilities().allowFlying) {
                            player.getAbilities().flying = false;
                            player.getAbilities().allowFlying = false;
                            player.sendAbilitiesUpdate();
                        }
                    }
                }
            }
        });
    }
}
