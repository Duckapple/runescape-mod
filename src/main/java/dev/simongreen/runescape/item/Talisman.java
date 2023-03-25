package dev.simongreen.runescape.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Talisman extends Item {
    public Talisman(Settings settings) {
        super(settings);
        settings.maxCount(1);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient())
            user.sendMessage(new LiteralText("It's getting hot"), false);
        return TypedActionResult.success(user.getStackInHand(hand),  world.isClient());
    }

//    @Override
//    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
//        return super.use(world, user, hand);
//    }


//    @Override
//    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
//        if (user instanceof PlayerEntity) {
//            ((PlayerEntity) user).sendMessage(new LiteralText("It's getting hot"), false);
//        }
//        return stack;
//    }
}
