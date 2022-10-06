package com.mmodding.invisibility_plus;

import com.mmodding.invisibility_plus.init.InvisibilityPotions;
import com.mmodding.mmodding_lib.library.base.MModdingModContainer;
import com.mmodding.mmodding_lib.library.base.MModdingModInitializer;
import com.mmodding.mmodding_lib.library.config.Config;
import com.mmodding.mmodding_lib.library.config.ConfigObject;
import com.mmodding.mmodding_lib.library.initializers.ElementsInitializer;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;

import java.util.ArrayList;
import java.util.List;

public class InvisibilityPlus implements MModdingModInitializer {

	public static MModdingModContainer mod;

	public static ConfigObject config;

	@Override
	public List<ElementsInitializer> getElementsInitializers() {
		return new ArrayList<>();
	}

	@Nullable
	@Override
	public Config getConfig() {
		return new InvisibilityPlusConfig();
	}

	@Override
	public void onInitialize(ModContainer mod) {
		MModdingModInitializer.super.onInitialize(mod);

		InvisibilityPlus.mod = MModdingModContainer.from(mod);

		assert this.getConfig() != null;
		InvisibilityPlus.config = this.getConfig().getContent();
	}
}
