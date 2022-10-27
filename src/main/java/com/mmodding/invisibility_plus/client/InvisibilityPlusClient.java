package com.mmodding.invisibility_plus.client;

import com.mmodding.mmodding_lib.library.base.MModdingClientModInitializer;
import com.mmodding.mmodding_lib.library.config.Config;
import com.mmodding.mmodding_lib.library.initializers.ClientElementsInitializer;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.minecraft.ClientOnly;

import java.util.ArrayList;
import java.util.List;

@ClientOnly
public class InvisibilityPlusClient implements MModdingClientModInitializer {

	@Nullable
	@Override
	public Config getClientConfig() {
		return null;
	}

	@Override
	public List<ClientElementsInitializer> getClientElementsInitializers() {
		List<ClientElementsInitializer> clientElementsInitializers = new ArrayList<>();
		clientElementsInitializers.add(new ClientEvents());
		return clientElementsInitializers;
	}
}
