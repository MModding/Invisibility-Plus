package com.mmodding.invisibility_plus;

import com.mmodding.invisibility_plus.ducks.EntityDuckInterface;
import com.mmodding.mmodding_lib.library.config.Config;
import com.mmodding.mmodding_lib.library.potions.CustomPotion;
import com.mmodding.mmodding_lib.library.utils.ConfigUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class Utils {

	public static CustomPotion createInvisibilityPotion(int amplifier) {
		return new CustomPotion("invisibility", new StatusEffectInstance(StatusEffects.INVISIBILITY, 3600, amplifier));
	}

	public static boolean amplifierHasEnabledEffects(int amplifier) {
		Config config = ConfigUtils.getConfig("invisibility_plus");

		if (config == null) {
			return false;
		}

		return switch (amplifier) {
			case 1 -> config.getContent().getBoolean("inv2effects");
			case 2 -> config.getContent().getBoolean("inv3effects");
			case 3 -> config.getContent().getBoolean("inv4effects");
			case 4 -> config.getContent().getBoolean("inv5effects");
			default -> false;
		};
	}

	public static void checkInvisibilityAmplifierAndRun(LivingEntity livingEntity, int amplifier, Runnable runnable) {
		if (Utils.amplifierHasEnabledEffects(amplifier)) {
			if (((EntityDuckInterface) livingEntity).invisibility_plus$getInvisibilityAmplifier() >= amplifier) {
				runnable.run();
			}
		}
	}
}
