package asewasew.weathermachine;

import asewasew.weathermachine.util.ModNumericProperty;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.item.property.numeric.NumericProperties;

public class WeatherMachineClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NumericProperties.ID_MAPPER.put(
                WeatherMachine.id("weather_mode"),
                ModNumericProperty.CODEC
        );
    }
}
