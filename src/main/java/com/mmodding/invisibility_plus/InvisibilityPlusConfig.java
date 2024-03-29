package com.mmodding.invisibility_plus;

import com.mmodding.mmodding_lib.library.config.Config;
import com.mmodding.mmodding_lib.library.config.ConfigObject;
import com.mmodding.mmodding_lib.library.config.ConfigOptions;
import com.mmodding.mmodding_lib.library.utils.TextureLocation;
import net.minecraft.text.Text;

public class InvisibilityPlusConfig implements Config {

	@Override
	public String getQualifier() {
		return "invisibility_plus";
	}

	@Override
	public String getFilePath() {
		return "invisibility_plus/common";
	}

	@Override
	public ConfigObject defaultConfig() {
		return new ConfigObject.Builder()
				.addBooleanParameter("inv2effects", true)
				.addBooleanParameter("inv3effects", true)
				.addBooleanParameter("inv4effects", true)
				.addBooleanParameter("inv5effects", true)
				.build();
	}

	@Override
	public ConfigOptions getConfigOptions() {
		return new ConfigOptions(Text.of("Invisibility Plus Config"), new TextureLocation.Block("iron_block"));
	}

	@Override
	public NetworkingState getNetworkingSate() {
		return NetworkingState.LOCAL_CACHES;
	}
}
