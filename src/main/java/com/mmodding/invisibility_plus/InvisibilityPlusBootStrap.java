package com.mmodding.invisibility_plus;

import com.mmodding.invisibility_plus.init.InvisibilityPotions;
import com.mmodding.mmodding_lib.library.base.MModdingBootStrapInitializer;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import org.quiltmc.loader.api.ModContainer;

public class InvisibilityPlusBootStrap implements MModdingBootStrapInitializer {

	@Override
	public void onInitializeBootStrap(ModContainer mod) {
		InvisibilityPotions.EFFICIENT_INVISIBILITY.addBrewingRecipe(Potions.INVISIBILITY, Items.GLOWSTONE_DUST);
		InvisibilityPotions.VERY_EFFICIENT_INVISIBILITY.addBrewingRecipe(InvisibilityPotions.EFFICIENT_INVISIBILITY, Items.QUARTZ);
		InvisibilityPotions.STRONG_INVISIBILITY.addBrewingRecipe(InvisibilityPotions.VERY_EFFICIENT_INVISIBILITY, Items.GLOWSTONE);
		InvisibilityPotions.VERY_STRONG_INVISIBILITY.addBrewingRecipe(InvisibilityPotions.STRONG_INVISIBILITY, Items.QUARTZ_BLOCK);
	}
}
