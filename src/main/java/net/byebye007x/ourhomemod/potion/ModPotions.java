package net.byebye007x.ourhomemod.potion;

import net.byebye007x.ourhomemod.OurHomeMod;
import net.byebye007x.ourhomemod.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotions {

    public static final RegistryEntry<Potion> FLIGHT_POTION = registerPotion(
            new Potion(new StatusEffectInstance(ModEffects.FLIGHT, 6000, 0)));

    private static RegistryEntry<Potion> registerPotion(Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(OurHomeMod.MOD_ID, "flight_potion"), potion);
    }

    public static void registerPotions() {
        OurHomeMod.LOGGER.info("Registering Mod Potions for" + OurHomeMod.MOD_ID);
    }
}
