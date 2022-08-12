package com.mmodding.invisibility_plus.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin {

	@Shadow
	public abstract boolean hasStatusEffect(StatusEffect effect);

	@Shadow
	public abstract @Nullable StatusEffectInstance getStatusEffect(StatusEffect effect);

	@Inject(method = "getArmorVisibility", at = @At(value = "RETURN"), cancellable = true)
	private void armorVisibility(CallbackInfoReturnable<Float> cir) {
		if (this.hasStatusEffect(StatusEffects.INVISIBILITY)) {
			StatusEffectInstance invisibility = this.getStatusEffect(StatusEffects.INVISIBILITY);
			assert invisibility != null;
			if (invisibility.getAmplifier() >= 4) {
				cir.setReturnValue(0.1F);
			}
		}
	}
}
