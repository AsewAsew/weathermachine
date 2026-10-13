package asewasew.weathermachine.item;

import asewasew.weathermachine.WeatherMachine;
import asewasew.weathermachine.components.ModDataComponentTypes;
import asewasew.weathermachine.item.custom.WeatherMachineItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {
    public static final Item WEATHER_MACHINE = registerItem(
            new WeatherMachineItem(new Item.Settings().maxCount(1).component(ModDataComponentTypes.WEATHER_MODE, 0)
                    .component(ModDataComponentTypes.AVAILABLE, false)
                    .component(ModDataComponentTypes.COOLDOWN, 0).maxDamage(32)));

    private static Item registerItem(Item item) {
        return Registry.register(Registries.ITEM, WeatherMachine.id("weather_machine"), item);
    }

    public static void registerModItems() {
        WeatherMachine.LOGGER.info("Registering Items for " + WeatherMachine.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries ->
            entries.addAfter(Items.CLOCK, WEATHER_MACHINE)
        );
    }
}
