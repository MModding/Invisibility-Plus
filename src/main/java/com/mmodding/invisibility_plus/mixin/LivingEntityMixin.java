package com.mmodding.invisibility_plus.mixin;

import com.mmodding.invisibility_plus.Utils;
import com.mmodding.invisibility_plus.accessors.EntityAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin {

	@Inject(method = "updatePotionVisibility", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setInvisible(Z)V", ordinal = 0))
	private void removeInvisibility(CallbackInfo ci) {
		((EntityAccessor) this.getObject()).setInvisibilityAmplifier(0);
	}

	@Inject(method = "updatePotionVisibility", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setInvisible(Z)V", ordinal = 1))
	private void addInvisibility(CallbackInfo ci) {
		if (this.getObject().hasStatusEffect(StatusEffects.INVISIBILITY)) {
			StatusEffectInstance instance = this.getObject().getStatusEffect(StatusEffects.INVISIBILITY);
			if (instance != null && instance.getAmplifier() != 0) {
				((EntityAccessor) this.getObject()).setInvisibilityAmplifier(instance.getAmplifier());
			}
		}
	}

	@Inject(method = "tickStatusEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), cancellable = true)
	private void conditionalEffectParticles(CallbackInfo ci) {
		Utils.checkInvisibilityAmplifierAndRun(this.getObject(), 2, ci::cancel);
	}

	@Inject(method = "fall", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;spawnParticles(Lnet/minecraft/particle/ParticleEffect;DDDIDDDD)I"), cancellable = true)
	private void conditionalFallParticles(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition, CallbackInfo ci) {
		super.fall(heightDifference, onGround, landedState, landedPosition);
		Utils.checkInvisibilityAmplifierAndRun(this.getObject(), 2, ci::cancel);
	}

	@Redirect(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"))
	private void conditionalDrownParticles(World world, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
		AtomicBoolean bool = new AtomicBoolean(false);
		Utils.checkInvisibilityAmplifierAndRun(this.getObject(), 2, () -> bool.set(true));
		if (bool.get()) return;
		world.addParticle(parameters, x, y, z, velocityX, velocityY, velocityZ);
	}

	@Inject(method = "displaySoulSpeedEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), cancellable = true)
	private void conditionalSoulSpeedParticles(CallbackInfo ci) {
		Utils.checkInvisibilityAmplifierAndRun(this.getObject(), 2, ci::cancel);
	}

	@Inject(method = "displaySoulSpeedEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V"), cancellable = true)
	private void conditionalSoulSpeedSound(CallbackInfo ci) {
		Utils.checkInvisibilityAmplifierAndRun(this.getObject(), 1, ci::cancel);
	}

	@Inject(method = "getArmorVisibility", at = @At(value = "RETURN"), cancellable = true)
	private void armorVisibility(CallbackInfoReturnable<Float> cir) {
		Utils.checkInvisibilityAmplifierAndRun(this.getObject(), 3, () -> cir.setReturnValue(0.1F));
	}

	@Inject(method = "spawnItemParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), cancellable = true)
	private void conditionalItemParticles(ItemStack stack, int count, CallbackInfo ci) {
		Utils.checkInvisibilityAmplifierAndRun(this.getObject(), 4, ci::cancel);
	}

	@Unique
	private LivingEntity getObject() {
		return (LivingEntity) (Object) this;
	}
}
