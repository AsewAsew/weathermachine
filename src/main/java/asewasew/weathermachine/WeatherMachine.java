package asewasew.weathermachine;

import asewasew.weathermachine.components.ModDataComponentTypes;
import asewasew.weathermachine.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherMachine implements ModInitializer {

	public static final String MOD_ID = "weathermachine";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModDataComponentTypes.registerModDataComponentTypes();
		ModItems.registerModItems();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
