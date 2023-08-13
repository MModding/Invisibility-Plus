package com.mmodding.invisibility_plus;

import com.mmodding.mmodding_lib.library.base.AdvancedModContainer;
import com.mmodding.mmodding_lib.library.base.MModdingModInitializer;
import com.mmodding.mmodding_lib.library.config.Config;
import com.mmodding.mmodding_lib.library.initializers.ElementsInitializer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InvisibilityPlus implements MModdingModInitializer {

	public static AdvancedModContainer mod;

	@Nullable
	@Override
	public Config getConfig() {
		return new InvisibilityPlusConfig();
	}

	@Override
	public List<ElementsInitializer> getElementsInitializers() {
		return new ArrayList<>();
	}

	@Override
	public void onInitialize(AdvancedModContainer mod) {
		InvisibilityPlus.mod = mod;
	}

	public static String id() {
		return "invisibility_plus";
	}

	public static Identifier createId(String path) {
		return new Identifier(InvisibilityPlus.id(), path);
	}
}
