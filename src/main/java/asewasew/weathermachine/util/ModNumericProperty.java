package asewasew.weathermachine.util;

import asewasew.weathermachine.components.ModDataComponentTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.render.item.property.numeric.NumericProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

public class ModNumericProperty implements NumericProperty {
    public static final MapCodec<ModNumericProperty> CODEC = MapCodec.unit(new ModNumericProperty());

    @Override
    public float getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity holder, int seed) {
        int mode = MathHelper.clamp(stack.getOrDefault(ModDataComponentTypes.WEATHER_MODE, 0), 0, 2);
        boolean match = stack.getOrDefault(ModDataComponentTypes.AVAILABLE, false);
        return mode + (match ? 3 : 0);
    }

    @Override
    public MapCodec<? extends NumericProperty> getCodec() {
        return CODEC;
    }
}
