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

	public static boolean amplifierHasEnabledEffects(int amplifier) {
		return switch (amplifier) {
			case 1 -> InvisibilityPlus.config.getBoolean("inv2effects");
			case 2 -> InvisibilityPlus.config.getBoolean("inv3effects");
			case 3 -> InvisibilityPlus.config.getBoolean("inv4effects");
			case 4 -> InvisibilityPlus.config.getBoolean("inv5effects");
			default -> false;
		};
	}

	public static void checkInvisibilityAmplifierAndRun(LivingEntity livingEntity, int amplifier, Runnable runnable) {
		if (amplifierHasEnabledEffects(amplifier)) {
			if (livingEntity.hasStatusEffect(StatusEffects.INVISIBILITY)) {
				StatusEffectInstance invisibility = livingEntity.getStatusEffect(StatusEffects.INVISIBILITY);
				assert invisibility != null;
				if (invisibility.getAmplifier() >= amplifier) runnable.run();
			}
		}
	}
}
