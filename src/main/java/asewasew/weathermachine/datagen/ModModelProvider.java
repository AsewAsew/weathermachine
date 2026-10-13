package asewasew.weathermachine.datagen;

import asewasew.weathermachine.WeatherMachine;
import asewasew.weathermachine.item.ModItems;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        Identifier itemModelId = ModelIds.getItemModelId(ModItems.WEATHER_MACHINE);

        List<String> modes = List.of("clear", "rain", "thunder");

        itemModelGenerator.writer.accept(itemModelId, () -> {
            JsonObject json = new JsonObject();
            json.addProperty("parent", "minecraft:item/generated");

            JsonObject textures = new JsonObject();
            textures.addProperty("layer0", itemModelId.toString());
            json.add("textures", textures);

            JsonArray overrides = new JsonArray();
            for(int i = 0; i <= 1; i++) {
                float pre1 = ((float) i);
                for(int j = 0; j <= 2; j++) {
                    if(i == 0 && j == 0) { continue; }
                    float pre2 = ((float) j) / 2f;

                    JsonObject overrideCase = new JsonObject();
                    JsonObject predicate = new JsonObject();
                    if(i > 0) { predicate.addProperty(WeatherMachine.id("match").toString(), pre1); }
                    if(j > 0) { predicate.addProperty(WeatherMachine.id("weather_mode").toString(), pre2); }

                    overrideCase.add("predicate", predicate);
                    overrideCase.addProperty("model", itemModelId.withSuffixedPath(
                            (i > 0 ? "_match" : "") + (j > 0 ? "_" + modes.get(j) : "")).toString());

                    overrides.add(overrideCase);
                }
            }

            json.add("overrides", overrides);
            return json;
        });

        for(int i = 0; i <= 1; i++) {
            for(int j = 0; j <= 2; j++) {
                if(i == 0 && j == 0) { continue; }
                Identifier newId = itemModelId.withSuffixedPath((i > 0 ? "_match" : "") + (j > 0 ? "_" + modes.get(j) : ""));

                itemModelGenerator.writer.accept(newId, () -> {
                    JsonObject json = new JsonObject();
                    json.addProperty("parent", itemModelId.toString());

                    JsonObject textures = new JsonObject();
                    textures.addProperty("layer0", newId.toString());

                    json.add("textures", textures);
                    return json;
                });
            }
        }
    }
}
