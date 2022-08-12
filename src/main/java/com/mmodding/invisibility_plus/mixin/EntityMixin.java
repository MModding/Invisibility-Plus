package com.mmodding.invisibility_plus.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {

	@Inject(method = "playStepSound", at = @At(value = "HEAD"), cancellable = true)
	private void removeStepSound(BlockPos pos, BlockState state, CallbackInfo ci) {
		if (((Entity) ((Object) this)) instanceof LivingEntity livingEntity) {
			if (livingEntity.hasStatusEffect(StatusEffects.INVISIBILITY)) {
				StatusEffectInstance invisibility = livingEntity.getStatusEffect(StatusEffects.INVISIBILITY);
				assert invisibility != null;
				if (invisibility.getAmplifier() >= 2) ci.cancel();
			}
		}
	}
}
