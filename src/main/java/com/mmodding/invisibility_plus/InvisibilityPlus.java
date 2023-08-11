package com.mmodding.invisibility_plus;

import com.mmodding.mmodding_lib.library.base.MModdingModContainer;
import com.mmodding.mmodding_lib.library.base.MModdingModInitializer;
import com.mmodding.mmodding_lib.library.config.Config;
import com.mmodding.mmodding_lib.library.initializers.ElementsInitializer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;

import java.util.ArrayList;
import java.util.List;

public class InvisibilityPlus implements MModdingModInitializer {

	public static MModdingModContainer mod;

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
	public void onInitialize(ModContainer mod) {
		MModdingModInitializer.super.onInitialize(mod);

		InvisibilityPlus.mod = MModdingModContainer.from(mod);
	}

	public static String id() {
		return "invisibility_plus";
	}

	public static Identifier createId(String path) {
		return new Identifier(InvisibilityPlus.id(), path);
	}
}
