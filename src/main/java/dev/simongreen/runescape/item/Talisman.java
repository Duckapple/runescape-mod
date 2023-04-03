package dev.simongreen.runescape.item;

import dev.simongreen.runescape.block.RuneAltar;
import dev.simongreen.runescape.common.RuneType;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Talisman extends Item {
    private final RuneType runeType;
    public Talisman(Settings settings, RuneType runeType) {
        super(settings);
        this.runeType = runeType;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient())
            user.sendMessage(new LiteralText("It's getting hot"), false);
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Block block = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
        if (!(block instanceof RuneAltar ra)) {
            return super.useOnBlock(context);
        }
        var rt = ra.getRuneType();
        if (!(rt == runeType)) {
            var player = context.getPlayer();
            if (context.getWorld().isClient() && player != null)
                player.sendMessage(new LiteralText("The altar does not respond to your talisman"), false);
            return ActionResult.SUCCESS;
        }
        var player = context.getPlayer();
        if (context.getWorld().isClient() && player != null)
            player.sendMessage(new LiteralText("Yummy altar"), false);
        return ActionResult.SUCCESS;
    }

    // @Override
    // public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand
    // hand) {
    // return super.use(world, user, hand);
    // }

    // @Override
    // public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user)
    // {
    // if (user instanceof PlayerEntity) {
    // ((PlayerEntity) user).sendMessage(new LiteralText("It's getting hot"),
    // false);
    // }
    // return stack;
    // }
}
