package asewasew.weathermachine.components;

import asewasew.weathermachine.WeatherMachine;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<Integer> WEATHER_MODE = register("weather_mode", builder -> builder.codec(Codec.INT));
    public static final ComponentType<Boolean> AVAILABLE = register("available", builder -> builder.codec(Codec.BOOL));
    public static final ComponentType<Integer> COOLDOWN = register("cooldown", builder -> builder.codec(Codec.INT));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(WeatherMachine.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerModDataComponentTypes() {
        WeatherMachine.LOGGER.info("Registering Mod Data Component Types for " + WeatherMachine.MOD_ID);
    }
}
