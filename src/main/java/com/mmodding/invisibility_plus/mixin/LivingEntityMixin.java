package com.mmodding.invisibility_plus.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@Shadow
	public abstract boolean hasStatusEffect(StatusEffect effect);

	@Shadow
	public abstract @Nullable StatusEffectInstance getStatusEffect(StatusEffect effect);

	@Redirect(method = "getArmorVisibility", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z"))
	private boolean armorVisibility(ItemStack itemStack) {
		if (this.hasStatusEffect(StatusEffects.INVISIBILITY)) {
			StatusEffectInstance invisibility = this.getStatusEffect(StatusEffects.INVISIBILITY);
			assert invisibility != null;
			return itemStack.isEmpty() && invisibility.getAmplifier() >= 4;
		}
		else {
			return itemStack.isEmpty();
		}
	}
}
