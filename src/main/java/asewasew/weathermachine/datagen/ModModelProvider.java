package asewasew.weathermachine.datagen;

import asewasew.weathermachine.item.ModItems;
import asewasew.weathermachine.util.ModNumericProperty;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.model.*;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        ItemModel.Unbaked clear = ItemModels.basic(itemModelGenerator.upload(ModItems.WEATHER_MACHINE, Models.GENERATED));
        ItemModel.Unbaked clearMatch = ItemModels.basic(itemModelGenerator.registerSubModel(ModItems.WEATHER_MACHINE, "_match", Models.GENERATED));
        ItemModel.Unbaked rain = ItemModels.basic(itemModelGenerator.registerSubModel(ModItems.WEATHER_MACHINE, "_rain", Models.GENERATED));
        ItemModel.Unbaked rainMatch = ItemModels.basic(itemModelGenerator.registerSubModel(ModItems.WEATHER_MACHINE, "_match_rain", Models.GENERATED));
        ItemModel.Unbaked thunder = ItemModels.basic(itemModelGenerator.registerSubModel(ModItems.WEATHER_MACHINE, "_thunder", Models.GENERATED));
        ItemModel.Unbaked thunderMatch = ItemModels.basic(itemModelGenerator.registerSubModel(ModItems.WEATHER_MACHINE, "_match_thunder", Models.GENERATED));

        itemModelGenerator.output.accept(ModItems.WEATHER_MACHINE, ItemModels.rangeDispatch(
                new ModNumericProperty(), clear,
                new RangeDispatchItemModel.Entry(0, clear),
                new RangeDispatchItemModel.Entry(1, rain),
                new RangeDispatchItemModel.Entry(2, thunder),
                new RangeDispatchItemModel.Entry(3, clearMatch),
                new RangeDispatchItemModel.Entry(4, rainMatch),
                new RangeDispatchItemModel.Entry(5, thunderMatch)
        ));
    }
}
