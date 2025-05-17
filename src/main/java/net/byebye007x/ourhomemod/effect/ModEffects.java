package net.byebye007x.ourhomemod.effect;

import net.byebye007x.ourhomemod.OurHomeMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> FLIGHT = registerStatusEffect(
            new FlightEffect(StatusEffectCategory.BENEFICIAL, 0x4fde33));

    private static RegistryEntry<StatusEffect> registerStatusEffect(StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(OurHomeMod.MOD_ID, "flight"), statusEffect);
    }

    public static void registerEffects() {
        OurHomeMod.LOGGER.info("Registering mod Effects for " + OurHomeMod.MOD_ID);
    }
}
