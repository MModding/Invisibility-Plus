package com.mmodding.invisibility_plus.bootstrap;

import com.mmodding.invisibility_plus.bootstrap.init.InvisibilityPlusBrewingRecipes;
import com.mmodding.mmodding_lib.library.base.AdvancedModContainer;
import com.mmodding.mmodding_lib.library.base.MModdingBootstrapInitializer;
import com.mmodding.mmodding_lib.library.initializers.BootstrapElementsInitializer;

import java.util.ArrayList;
import java.util.List;

public class InvisibilityPlusBootstrap implements MModdingBootstrapInitializer {

	@Override
	public List<BootstrapElementsInitializer> getBootstrapElementsInitializers() {
		List<BootstrapElementsInitializer> bootstrapElementsInitializers = new ArrayList<>();
		bootstrapElementsInitializers.add(new InvisibilityPlusBrewingRecipes());
		return bootstrapElementsInitializers;
	}

	@Override
	public void onInitializeBootstrap(AdvancedModContainer mod) {}
}
