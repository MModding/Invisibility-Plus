package com.mmodding.invisibility_plus.client;

import com.mmodding.invisibility_plus.InvisibilityPlus;
import com.mmodding.mmodding_lib.library.client.events.ClientConfigNetworkingEvents;
import com.mmodding.mmodding_lib.library.initializers.ClientElementsInitializer;

public class ClientEvents implements ClientElementsInitializer {

	@Override
	public void registerClient() {
		ClientConfigNetworkingEvents.AFTER.register((config -> {
			if (config.getConfigName().equals("invisibility_plus")) {
				InvisibilityPlus.serverConfig = config.getContent();
			}
		}));
	}
}
