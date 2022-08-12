package com.mmodding.invisibility_plus.mixin;

import com.mmodding.invisibility_plus.Utils;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin {

	@Inject(method = "tickStatusEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"), cancellable = true)
	private void conditionalParticles(CallbackInfo ci) {
		Utils.checkInvisibilityAmplifierAndRun((LivingEntity) ((Object) this), 2, ci::cancel);
	}

	@Inject(method = "getArmorVisibility", at = @At(value = "RETURN"), cancellable = true)
	private void armorVisibility(CallbackInfoReturnable<Float> cir) {
		Utils.checkInvisibilityAmplifierAndRun((LivingEntity) ((Object) this), 3, () -> cir.setReturnValue(0.1F));
	}
}
