package com.mmodding.invisibility_plus.mixin;

import com.mmodding.invisibility_plus.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {

	@Inject(method = "playStepSound", at = @At(value = "HEAD"), cancellable = true)
	private void conditionalStepSound(BlockPos pos, BlockState state, CallbackInfo ci) {
		if ((Entity) (Object) this instanceof LivingEntity livingEntity) {
			Utils.checkInvisibilityAmplifierAndRun(livingEntity, 1, ci::cancel);
		}
	}

	@Inject(method = "playSwimSound", at = @At(value = "HEAD"), cancellable = true)
	private void conditionalSwimSound(float volume, CallbackInfo ci) {
		if ((Entity) (Object) this instanceof LivingEntity livingEntity) {
			Utils.checkInvisibilityAmplifierAndRun(livingEntity, 1, ci::cancel);
		}
	}
}
