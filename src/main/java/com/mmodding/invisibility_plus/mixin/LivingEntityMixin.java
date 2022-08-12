package com.mmodding.invisibility_plus.mixin;

import com.mmodding.invisibility_plus.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin {

	@Inject(method = "tickStatusEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), cancellable = true)
	private void conditionalEffectParticles(CallbackInfo ci) {
		Utils.checkInvisibilityAmplifierAndRun((LivingEntity) (Object) this, 2, ci::cancel);
	}

	@Inject(method = "fall", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;spawnParticles(Lnet/minecraft/particle/ParticleEffect;DDDIDDDD)I"), cancellable = true)
	private void conditionalFallParticles(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition, CallbackInfo ci) {
		Utils.checkInvisibilityAmplifierAndRun((LivingEntity) (Object) this, 2, ci::cancel);
	}

	@Inject(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), cancellable = true)
	private void conditionalDrownParticles(CallbackInfo ci) {
		Utils.checkInvisibilityAmplifierAndRun((LivingEntity) (Object) this, 2, ci::cancel);
	}

	@Inject(method = "displaySoulSpeedEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), cancellable = true)
	private void conditionalSoulSpeedParticles(CallbackInfo ci) {
		Utils.checkInvisibilityAmplifierAndRun((LivingEntity) (Object) this, 2, ci::cancel);
	}

	@Inject(method = "getArmorVisibility", at = @At(value = "RETURN"), cancellable = true)
	private void armorVisibility(CallbackInfoReturnable<Float> cir) {
		Utils.checkInvisibilityAmplifierAndRun((LivingEntity) (Object) this, 3, () -> cir.setReturnValue(0.1F));
	}

	@Inject(method = "spawnItemParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), cancellable = true)
	private void conditionalItemParticles(ItemStack stack, int count, CallbackInfo ci) {
		Utils.checkInvisibilityAmplifierAndRun((LivingEntity) (Object) this, 4, ci::cancel);
	}
}
