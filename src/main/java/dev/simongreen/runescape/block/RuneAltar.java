package dev.simongreen.runescape.block;

import dev.simongreen.runescape.RuneScapeMod;
import dev.simongreen.runescape.common.RuneType;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class RuneAltar extends HorizontalFacingBlock {
    private final RuneType runeType;
    protected static final VoxelShape SE_SHAPE = Block.createCuboidShape(0, 0, 0, 14, 15, 14);
    protected static final VoxelShape SW_SHAPE = Block.createCuboidShape(2, 0, 0, 16, 15, 14);
    protected static final VoxelShape NE_SHAPE = Block.createCuboidShape(0, 0, 2, 14, 15, 16);
    protected static final VoxelShape NW_SHAPE = Block.createCuboidShape(2, 0, 2, 16, 15, 16);

    // public static
    public RuneAltar(Settings settings, RuneType runeType) {
        super(settings);
        this.runeType = runeType;
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    public final RuneType getRuneType() {
        return runeType;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            default -> NW_SHAPE;
            case SOUTH -> SE_SHAPE;
            case WEST -> SW_SHAPE;
            case EAST -> NE_SHAPE;
        };
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        var others = getOtherPositions(state.get(FACING), pos);
        super.onBroken(world, pos, state);
        for (var other : others.others) {
            world.breakBlock(other, false);
        }
    }

    // @Override
    // public String getTranslationKey() {
    //// return "item." + RuneScapeMod.NAMESPACE + "." + rune_type.asString() +
    // "_altar";
    // return super.getTranslationKey();
    // }

    // @Override
    // public VoxelShape getCollisionShape(BlockState state, BlockView world,
    // BlockPos pos, ShapeContext context) {
    // return VoxelShapes.cuboid(0,0,0,16,14,16);
    // }
    public static OtherPositions getOtherPositions(Direction dir, BlockPos pos) {
        switch (dir) {
            case NORTH -> {
                return new OtherPositions(
                        new Direction[] { Direction.EAST, Direction.WEST, Direction.SOUTH },
                        new BlockPos[] { pos.add(1, 0, 0), pos.add(0, 0, 1), pos.add(1, 0, 1) });
            }
            case SOUTH -> {
                return new OtherPositions(
                        new Direction[] { Direction.WEST, Direction.EAST, Direction.NORTH },
                        new BlockPos[] { pos.add(-1, 0, 0), pos.add(0, 0, -1), pos.add(-1, 0, -1) });
            }
            case WEST -> {
                return new OtherPositions(
                        new Direction[] { Direction.SOUTH, Direction.NORTH, Direction.EAST },
                        new BlockPos[] { pos.add(1, 0, 0), pos.add(0, 0, -1), pos.add(1, 0, -1) });
            }
            case EAST -> {
                return new OtherPositions(
                        new Direction[] { Direction.NORTH, Direction.SOUTH, Direction.WEST },
                        new BlockPos[] { pos.add(-1, 0, 0), pos.add(0, 0, 1), pos.add(-1, 0, 1) });
            }
            default -> throw new IllegalStateException("Unexpected value: " + dir);
        }
    }

    public static class OtherPositions {
        public Direction[] direcs;
        public BlockPos[] others;

        OtherPositions(Direction[] direcs, BlockPos[] others) {
            this.direcs = direcs;
            this.others = others;
        }
    }
}
