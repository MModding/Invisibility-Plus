package com.mmodding.invisibility_plus.bootstrap.init;

import com.mmodding.invisibility_plus.init.InvisibilityPotions;
import com.mmodding.mmodding_lib.library.initializers.BootstrapElementsInitializer;
import com.mmodding.mmodding_lib.library.initializers.invokepoints.BootstrapInvokePoint;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;

public class InvisibilityPlusBrewingRecipes implements BootstrapElementsInitializer {

	@Override
	public BootstrapInvokePoint getInvokePoint() {
		return BootstrapInvokePoint.before(BootstrapInvokePoint.Type.BREWING_RECIPES);
	}

	@Override
	public void registerBootstrap() {
		InvisibilityPotions.EFFICIENT_INVISIBILITY.addBrewingRecipe(Potions.INVISIBILITY, Items.GLOWSTONE_DUST);
		InvisibilityPotions.VERY_EFFICIENT_INVISIBILITY.addBrewingRecipe(InvisibilityPotions.EFFICIENT_INVISIBILITY, Items.QUARTZ);
		InvisibilityPotions.STRONG_INVISIBILITY.addBrewingRecipe(InvisibilityPotions.VERY_EFFICIENT_INVISIBILITY, Items.GLOWSTONE);
		InvisibilityPotions.VERY_STRONG_INVISIBILITY.addBrewingRecipe(InvisibilityPotions.STRONG_INVISIBILITY, Items.QUARTZ_BLOCK);
	}
}
