package com.mmodding.invisibility_plus.mixin;

import com.mmodding.invisibility_plus.Utils;
import com.mmodding.invisibility_plus.init.InvisibilityPotions;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Potions.class)
public abstract class PotionsMixin {

	@Inject(method = "register", at = @At(value = "TAIL"))
	private static void registerInvisibilityPotions(String name, Potion potion, CallbackInfoReturnable<Potion> cir) {
		if (name.equals("invisibility")) {
			InvisibilityPotions.EFFICIENT_INVISIBILITY = PotionsAccessor.invokeRegister("efficient_invisibility", Utils.createInvisibilityPotion(1));
			InvisibilityPotions.VERY_EFFICIENT_INVISIBILITY = PotionsAccessor.invokeRegister("very_efficient_invisibility", Utils.createInvisibilityPotion(2));
			InvisibilityPotions.STRONG_INVISIBILITY = PotionsAccessor.invokeRegister("strong_invisibility", Utils.createInvisibilityPotion(3));
			InvisibilityPotions.VERY_STRONG_INVISIBILITY = PotionsAccessor.invokeRegister("very_strong_invisibility", Utils.createInvisibilityPotion(4));
		}
	}
}
