package com.mmodding.invisibility_plus.mixin;

import com.mmodding.invisibility_plus.Utils;
import com.mmodding.invisibility_plus.ducks.EntityDuckInterface;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityDuckInterface {

	@Unique
	private static final TrackedData<Integer> INVISIBILITY_AMPLIFIER = DataTracker.registerData(Entity.class, TrackedDataHandlerRegistry.INTEGER);

	@Shadow
	public abstract void emitGameEvent(GameEvent event);

	@Shadow
	public abstract DataTracker getDataTracker();

	@Shadow
	protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {}

	@Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;initDataTracker()V", shift = At.Shift.BEFORE))
	private void init(EntityType<?> entityType, World world, CallbackInfo ci) {
		this.getDataTracker().startTracking(INVISIBILITY_AMPLIFIER, 0);
	}

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

	@ModifyArgs(method = "onSwimmingStart", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V"))
	private void conditionalSplashSound(Args args) {
		if ((Entity) (Object) this instanceof LivingEntity livingEntity) {
			Utils.checkInvisibilityAmplifierAndRun(livingEntity, 1, () -> args.set(1, 0.0F));
		}
	}

	@Inject(method = "onSwimmingStart", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;floor(D)I"), cancellable = true)
	private void conditionalSwimParticles(CallbackInfo ci) {
		if ((Entity) (Object) this instanceof LivingEntity livingEntity) {
			Utils.checkInvisibilityAmplifierAndRun(livingEntity, 2, () -> {
				this.emitGameEvent(GameEvent.SPLASH);
				ci.cancel();
			});
		}
	}

	@Inject(method = "shouldSpawnSprintingParticles", at = @At(value = "HEAD"), cancellable = true)
	private void conditionalSprintingParticles(CallbackInfoReturnable<Boolean> cir) {
		if ((Entity) (Object) this instanceof LivingEntity livingEntity) {
			Utils.checkInvisibilityAmplifierAndRun(livingEntity, 2, () -> cir.setReturnValue(false));
		}
	}

	@Override
	public int invisibility_plus$getInvisibilityAmplifier() {
		return this.getDataTracker().get(INVISIBILITY_AMPLIFIER);
	}

	@Override
	public void invisibility_plus$setInvisibilityAmplifier(int amplifier) {
		this.getDataTracker().set(INVISIBILITY_AMPLIFIER, amplifier);
	}
}
