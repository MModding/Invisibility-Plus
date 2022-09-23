package com.mmodding.invisibility_plus;

import com.mmodding.mmodding_lib.library.config.Config;
import com.mmodding.mmodding_lib.library.config.ConfigObject;
import com.mmodding.mmodding_lib.library.config.ConfigScreen;
import com.mmodding.mmodding_lib.library.config.ConfigScreenOptions;
import net.minecraft.text.Text;

public class InvisibilityPlusConfig implements Config {

	@Override
	public String getFileName() {
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
	public ConfigScreenOptions getConfigOptions() {
		return new ConfigScreenOptions(Text.of("Invisibility Plus Config"), new ConfigScreen.BlockTextureLocation("iron_block.png"));
	}
}
