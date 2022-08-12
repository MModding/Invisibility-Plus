package com.mmodding.invisibility_plus;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;

public class Utils {

	public static final String modIdentifier = "invisibility_plus";

	public static Identifier newIdentifier(String path) {
		return new Identifier(Utils.modIdentifier, path);
	}

	public static Potion createInvisibilityPotion(int amplifier) {
		return new Potion("invisibility", new StatusEffectInstance(StatusEffects.INVISIBILITY, 3600, amplifier));
	}

	public static void checkInvisibilityAmplifierAndRun(LivingEntity livingEntity, int amplifier, Runnable runnable) {
		if (livingEntity.hasStatusEffect(StatusEffects.INVISIBILITY)) {
			StatusEffectInstance invisibility = livingEntity.getStatusEffect(StatusEffects.INVISIBILITY);
			assert invisibility != null;
			if (invisibility.getAmplifier() >= amplifier) runnable.run();
		}
	};
}
