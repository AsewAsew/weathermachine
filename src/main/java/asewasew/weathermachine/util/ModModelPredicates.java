package asewasew.weathermachine.util;

import asewasew.weathermachine.WeatherMachine;
import asewasew.weathermachine.components.ModDataComponentTypes;
import asewasew.weathermachine.item.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;

public class ModModelPredicates {
    public static void registerModModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.WEATHER_MACHINE, WeatherMachine.id("weather_mode"),
                (stack, world, entity, seed) -> Float.valueOf(stack.getOrDefault(ModDataComponentTypes.WEATHER_MODE, 0)) / 2f);
        ModelPredicateProviderRegistry.register(ModItems.WEATHER_MACHINE, WeatherMachine.id("match"),
                (stack, world, entity, seed) -> stack.getOrDefault(ModDataComponentTypes.AVAILABLE, false) ? 1f : 0f);
    }
}
