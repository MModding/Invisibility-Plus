package com.mmodding.invisibility_plus;

import com.mmodding.mmodding_lib.library.potions.CustomPotion;
import net.fabricmc.api.EnvType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.minecraft.MinecraftQuiltLoader;

public class Utils {

	public static final String modIdentifier = "invisibility_plus";

	public static Identifier newIdentifier(String path) {
		return new Identifier(Utils.modIdentifier, path);
	}

	public static CustomPotion createInvisibilityPotion(int amplifier) {
		return new CustomPotion("invisibility", new StatusEffectInstance(StatusEffects.INVISIBILITY, 3600, amplifier));
	}

	public static boolean amplifierHasEnabledEffects(int amplifier) {
		if (MinecraftQuiltLoader.getEnvironmentType() == EnvType.CLIENT) {
			if (!MinecraftClient.getInstance().isInSingleplayer() || InvisibilityPlus.serverConfig != null) {
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
		if (amplifierHasEnabledEffects(amplifier)) {
			if (livingEntity.hasStatusEffect(StatusEffects.INVISIBILITY)) {
				StatusEffectInstance invisibility = livingEntity.getStatusEffect(StatusEffects.INVISIBILITY);
				assert invisibility != null;
				if (invisibility.getAmplifier() >= amplifier) runnable.run();
			}
		}
	}
}
