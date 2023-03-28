package dev.simongreen.runescape.item;

import dev.simongreen.runescape.block.RuneAltar;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class RuneAltarItem extends BlockItem {

    public RuneAltarItem(Settings settings, RuneAltar altar) {
        super(altar, settings);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        var state = super.getPlacementState(ctx);
        if (state == null)
            return null;
        var player = ctx.getPlayer();
        if (player == null)
            return state;
        var yaw = player.getYaw() % 360;
        if (yaw < 0)
            yaw += 360;

        var dir = Direction.NORTH;
        if (yaw < 90)
            dir = Direction.EAST;
        else if (yaw < 180)
            dir = Direction.SOUTH;
        else if (yaw < 270)
            dir = Direction.WEST;

        var world = ctx.getWorld();
        var pos = ctx.getBlockPos();
        var other = RuneAltar.getOtherPositions(dir, pos);

        for (int i = 0; i < 3; i++) {
            var s = world.getBlockState(other.others[i]);
            if (!s.canReplace(ctx)) {
                return null;
            }
        }
        for (int i = 0; i < 3; i++) {
            world.setBlockState(other.others[i], state.with(RuneAltar.FACING, other.direcs[i]));
        }
        return state.with(RuneAltar.FACING, dir);
    }
}
