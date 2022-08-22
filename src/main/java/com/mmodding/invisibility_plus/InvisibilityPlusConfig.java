package com.mmodding.invisibility_plus;

import com.mmodding.mmodding_lib.library.config.Config;
import com.mmodding.mmodding_lib.library.config.ConfigBuilder;

public class InvisibilityPlusConfig implements Config {

	@Override
	public String getFileName() {
		return "invisibility_plus/common";
	}

	@Override
	public ConfigBuilder defaultConfig() {
		return new ConfigBuilder()
				.addBooleanParameter("inv2effects", true)
				.addBooleanParameter("inv3effects", true)
				.addBooleanParameter("inv4effects", true)
				.addBooleanParameter("inv5effects", true);
	}
}
