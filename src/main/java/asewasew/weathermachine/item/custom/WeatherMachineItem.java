package asewasew.weathermachine.item.custom;

import asewasew.weathermachine.components.ModDataComponentTypes;
import asewasew.weathermachine.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WeatherMachineItem extends Item {


    public WeatherMachineItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stackInHand = user.getStackInHand(hand);
        if(!world.isClient() && stackInHand.isOf(ModItems.WEATHER_MACHINE)) {
            ServerWorld serverWorld = (ServerWorld) world;
            int weatherMode = stackInHand.getOrDefault(ModDataComponentTypes.WEATHER_MODE, 0);
            int currentWeather = (serverWorld.isRaining() ? 1 : 0) * (serverWorld.isThundering() ? 2 : 1);
            int cooldown = stackInHand.getOrDefault(ModDataComponentTypes.COOLDOWN, 0);
            if(user.isSneaking()) {
                weatherMode = (weatherMode + 1) % 3;
                stackInHand.set(ModDataComponentTypes.WEATHER_MODE, weatherMode);
                return TypedActionResult.success(stackInHand);
            }
            if(cooldown > 0) {
                user.sendMessage(Text.translatable("item.weathermachine.weather_machine.warn.cooldown"));
                return TypedActionResult.fail(stackInHand);
            }
            if(!world.isSkyVisible(user.getBlockPos())) {
                user.sendMessage(Text.translatable("item.weathermachine.weather_machine.warn.sky_access"));
                return TypedActionResult.fail(stackInHand);
            }
            if(weatherMode == currentWeather) {
                Text matchingWeatherText = switch (weatherMode) {
                    case 0 -> Text.translatable("item.weathermachine.weather_machine.warn.is_clear");
                    case 1 -> Text.translatable("item.weathermachine.weather_machine.warn.is_rain");
                    case 2 -> Text.translatable("item.weathermachine.weather_machine.warn.is_thunder");
                    default -> Text.literal("ERROR DISPLAYING ERROR MESSAGE!");
                };
                user.sendMessage(matchingWeatherText);
                return TypedActionResult.fail(stackInHand);
            }
            boolean rain = weatherMode > 0, thunder = weatherMode > 1;
            serverWorld.setWeather(!rain ? 12000 : 0, rain ? 12000 : 0, rain, thunder);
            stackInHand.set(ModDataComponentTypes.COOLDOWN, 100);
            if(!user.isCreative()) {
                stackInHand.damage(1, serverWorld, (ServerPlayerEntity) user,
                        item -> user.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));
            }
            return TypedActionResult.success(stackInHand);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if(!world.isClient() && stack.isOf(ModItems.WEATHER_MACHINE)) {
            int weatherMode = stack.getOrDefault(ModDataComponentTypes.WEATHER_MODE, 0);
            int currentWeather = (world.isRaining() ? 1 : 0) * (world.isThundering() ? 2 : 1);
            int cooldown = stack.getOrDefault(ModDataComponentTypes.COOLDOWN, 0);

            stack.set(ModDataComponentTypes.AVAILABLE, cooldown > 0 || weatherMode == currentWeather);
            if(cooldown > 0) {
                stack.set(ModDataComponentTypes.COOLDOWN, cooldown - 1);
            }
        }
    }

    @Override
    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }
}
