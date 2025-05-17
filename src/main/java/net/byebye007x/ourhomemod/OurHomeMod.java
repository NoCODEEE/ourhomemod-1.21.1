package net.byebye007x.ourhomemod;

import net.byebye007x.ourhomemod.effect.ModEffects;
import net.byebye007x.ourhomemod.potion.ModPotions;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OurHomeMod implements ModInitializer {
	public static final String MOD_ID = "ourhomemod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModConfig.load();

		ModEffects.registerEffects();
		EventCheck.FlightEffectCheck();

		ModPotions.registerPotions();

		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
					builder.registerPotionRecipe(Potions.SLOW_FALLING, Registries.ITEM.get(ModConfig.flightIngredient), ModPotions.FLIGHT_POTION);
				});
	}
}