package com.mmodding.invisibility_plus.client;

import com.mmodding.invisibility_plus.InvisibilityPlus;
import com.mmodding.mmodding_lib.library.events.client.ClientConfigNetworkingEvents;
import com.mmodding.mmodding_lib.library.initializers.ClientElementsInitializer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.networking.api.client.ClientPlayConnectionEvents;

@ClientOnly
public class ClientEvents implements ClientElementsInitializer {

	@Override
	public void registerClient() {

		ClientConfigNetworkingEvents.AFTER.register(config -> {
			if (config.getConfigName().equals("invisibility_plus")) {
				InvisibilityPlus.serverConfig = config.getContent().copy();
			}
		});

		ClientPlayConnectionEvents.DISCONNECT.register(((handler, client) -> InvisibilityPlus.serverConfig = null));
	}
}
