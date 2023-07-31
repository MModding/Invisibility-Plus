package com.mmodding.invisibility_plus;

import com.mmodding.mmodding_lib.library.base.MModdingModContainer;
import com.mmodding.mmodding_lib.library.base.MModdingModInitializer;
import com.mmodding.mmodding_lib.library.config.Config;
import com.mmodding.mmodding_lib.library.config.ConfigObject;
import com.mmodding.mmodding_lib.library.initializers.ElementsInitializer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;

import java.util.ArrayList;
import java.util.List;

public class InvisibilityPlus implements MModdingModInitializer {

	public static MModdingModContainer mod;

	public static Config config;

	public static ConfigObject staticConfig;

	public static ConfigObject serverConfig;

	@Nullable
	@Override
	public Config getConfig() {
		return new InvisibilityPlusConfig();
	}

	@Override
	public List<ElementsInitializer> getElementsInitializers() {
		List<ElementsInitializer> elementsInitializers = new ArrayList<>();
		elementsInitializers.add(new Events());
		return elementsInitializers;
	}

	@Override
	public void onInitialize(ModContainer mod) {
		MModdingModInitializer.super.onInitialize(mod);

		InvisibilityPlus.mod = MModdingModContainer.from(mod);

		assert this.getConfig() != null;
		InvisibilityPlus.config = this.getConfig();
		InvisibilityPlus.staticConfig = this.getConfig().getContent().copy();
	}

	public static String id() {
		return "invisibility_plus";
	}

	public static Identifier createId(String path) {
		return new Identifier(InvisibilityPlus.id(), path);
	}
}
