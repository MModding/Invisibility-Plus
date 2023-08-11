package com.mmodding.invisibility_plus.mixin;

import com.mmodding.invisibility_plus.init.InvisibilityPotions;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Potions.class)
public abstract class PotionsMixin {

    @Inject(method = "register", at = @At(value = "TAIL"))
    private static void registerInvisibilityPotions(String name, Potion potion, CallbackInfoReturnable<Potion> cir) {
        if (name.equals("invisibility")) {
            InvisibilityPotions.EFFICIENT_INVISIBILITY.register("efficient_invisibility");
            InvisibilityPotions.VERY_EFFICIENT_INVISIBILITY.register("very_efficient_invisibility");
            InvisibilityPotions.STRONG_INVISIBILITY.register("strong_invisibility");
            InvisibilityPotions.VERY_STRONG_INVISIBILITY.register("very_strong_invisibility");
        }
    }
}
