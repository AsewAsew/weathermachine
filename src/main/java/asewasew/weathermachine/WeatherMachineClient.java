package asewasew.weathermachine;

import asewasew.weathermachine.util.ModModelPredicates;
import net.fabricmc.api.ClientModInitializer;

public class WeatherMachineClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModModelPredicates();
    }
}
