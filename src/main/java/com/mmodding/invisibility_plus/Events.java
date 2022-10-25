package com.mmodding.invisibility_plus;

import com.mmodding.mmodding_lib.MModdingLib;
import com.mmodding.mmodding_lib.library.initializers.ElementsInitializer;
import org.quiltmc.qsl.networking.api.ServerPlayConnectionEvents;

public class Events implements ElementsInitializer {

	public void register() {
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			if (server.isDedicated()) {
				MModdingLib.configs.get(InvisibilityPlus.config.getConfigName()).sendServerConfigToClient(handler.getPlayer());
			}
		});
	}
}
