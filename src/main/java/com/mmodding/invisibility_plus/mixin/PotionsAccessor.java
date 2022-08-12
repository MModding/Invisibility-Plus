package com.mmodding.invisibility_plus.mixin;

import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Potions.class)
public interface PotionsAccessor {
	@Invoker("register")
	static Potion invokeRegister(String name, Potion potion) {
		throw new AssertionError();
	}
}
