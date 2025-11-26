package net.ShadowStorm7894.CustomLootCrates.item.custom;

import net.ShadowStorm7894.CustomLootCrates.block.ModCubes;
import net.ShadowStorm7894.CustomLootCrates.tags.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    //adds right click functionality
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        //not client side only
        if(!pContext.getLevel().isClientSide()){
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundblock = false;
            //from blocks starting at selected then going down
            for(int i = 0; i <= positionClicked.getY() + 64; i++){
                BlockPos blockPos = positionClicked.below(i);
                BlockState state = pContext.getLevel().getBlockState(blockPos);

                if(isValuableBlock(state)){
                    outputValuableCoordinates(blockPos, player, state.getBlock());
                    foundblock = true;
                    break;
                }
            }
                if(!foundblock) {
                player.sendSystemMessage(Component.literal("No valuables found!"));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));


        return InteractionResult.SUCCESS;
    }

    //send message to player
    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found" + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }

    //list of valuable blocks
    private boolean isValuableBlock(BlockState state) {
        //return state.is(ModCubes.SAPPHIRE_BLOCK.get());
        return state.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }
}
