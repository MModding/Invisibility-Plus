package com.mmodding.invisibility_plus;

import com.mmodding.invisibility_plus.accessors.EntityAccessor;
import com.mmodding.mmodding_lib.library.potions.CustomPotion;
import com.mmodding.mmodding_lib.library.utils.EnvironmentUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class Utils {

	public static CustomPotion createInvisibilityPotion(int amplifier) {
		return new CustomPotion("invisibility", new StatusEffectInstance(StatusEffects.INVISIBILITY, 3600, amplifier));
	}

	public static boolean amplifierHasEnabledEffects(int amplifier) {
		if (EnvironmentUtils.isClient()) {
			if (!EnvironmentUtils.isInSinglePlayer() || InvisibilityPlus.serverConfig != null) {
				return switch (amplifier) {
					case 1 -> InvisibilityPlus.serverConfig.getBoolean("inv2effects");
					case 2 -> InvisibilityPlus.serverConfig.getBoolean("inv3effects");
					case 3 -> InvisibilityPlus.serverConfig.getBoolean("inv4effects");
					case 4 -> InvisibilityPlus.serverConfig.getBoolean("inv5effects");
					default -> false;
				};
			}
		}
		return switch (amplifier) {
			case 1 -> InvisibilityPlus.staticConfig.getBoolean("inv2effects");
			case 2 -> InvisibilityPlus.staticConfig.getBoolean("inv3effects");
			case 3 -> InvisibilityPlus.staticConfig.getBoolean("inv4effects");
			case 4 -> InvisibilityPlus.staticConfig.getBoolean("inv5effects");
			default -> false;
		};
	}

	public static void checkInvisibilityAmplifierAndRun(LivingEntity livingEntity, int amplifier, Runnable runnable) {
		if (Utils.amplifierHasEnabledEffects(amplifier)) {
			if (((EntityAccessor) livingEntity).getInvisibilityAmplifier() >= amplifier) {
				runnable.run();
			}
		}
	}
}
